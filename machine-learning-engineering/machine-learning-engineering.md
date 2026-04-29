# MACHINE LEARNING ENGINEERING

---

**PyTorch Model Optimization (Inference)**

---

1. Inference mode: `torch.no_grad()` or even `torch.inference_mode()`, `model.eval()`
2. Basic serialization & optimization: `torch.jit` (being replaced by `torch.export` or `torch.onnx.export`)
3. Deep optimization: `torch.compile` or `torch_tensorrt.compile`
4. Runtime optimization: GPU Warm-Up, caching of compiled model engine
5. Joint training & inference optimization: Automatic Mixed Precision (`torch.amp`) or `.half()`, model size compression, tiling / chunking

---

**PyTorch inference mode**

---

- `model.eval()` switches certain layers into evaluation mode. Layers like `BatchNorm` and `Dropout` behave differently during training vs. inference.
- `torch.no_grad()` - context manager which disables gradient tracking. Autograd (PyTorch's automatic differentiation engine) otherwise builds the autograd graph (traversed in reverse in `.backward()` to compute gradients).
- `torch.inference_mode()` - stronger version of `torch.no_grad()`. Tensors created inside cannot be used in autograd later.

---

**Basic serialization & optimization - `torch.jit`**

---

- Lets you serialize and optimize PyTorch models into a format that can run independently of Python.
- Just-In-Time (JIT) compilation system - converts to TorchScript IR
- `torch.jit.trace` (earlier, simpler approach), replaced by `torch.jit.script`
- Optimizations: simple (dead code eliminstion, constant folding, common subexpression elimination), graph level (limited operator fusion into joint kernels), control flow optimization (function call inlining, loop unrolling for constant loop bounds)

---

**Deep optimization - `torch.compile` or `torch_tensorrt.compile`**

---

Backends:

- `inductor` (built-in) - for more flexible outcome, dynamic shapes, less operational complexity
- `tensorrt` (third-party) - TensorRT, for highly optimized outcome, but for a specific GPU, specific input shapes, and specific precision
- Others: `tvm` (Apache TVM - open-source ML compiler with broad hardware support), `ipex` (Intel), `onnxrt` (ONNX), `cudagraphs` (captures CUDA Graphs but no ops/kernel fusion, no autotuning)

Needs to be done on the target architecture since the output is not portable.

Questions to answer: How stable are your input shapes? What's your deployment target? Do you need training + serving in one codebase?

---

**Optimizations with `model = torch.compile(model, backend='inductor')`**

---

Optimizations:

- generates optimized C++/Triton kernels (deep operator and kernel fusion, kernel autotuning - benchmarks multiple kernel implementations and picks the fastest one for the specific GPU)
- captures CUDA Graphs
- eliminates redundant memory traffic, optimizes memory layout

Modes: `default` (balanced), `reduce-overhead` (minimizes launch overhead via CUDA graphs), `max-autotune` (tries many kernel variants, slow compile but fastest runtime)

---

**Optimizations with `model = torch_tensorrt.compile(model, ...)`**

---

- Layer & Operator Fusion - merges sequences of operations into single kernels, eliminating intermediate memory reads/writes and kernel launch overhead.
- Precision Calibration - beyond just FP16, TensorRT supports INT8 quantization, runs a calibration dataset through your network to determine per-tensor dynamic ranges, then quantizes weights and activations to 8-bit integers.
- Kernel Auto-Tuning - for each fused operation, TensorRT has a library of kernel implementations (different tiling strategies, memory access patterns, etc.), it benchmarks all candidates on your specific GPU and selects the fastest.
- Memory Optimization - analyzes the entire graph's lifetime of tensors and reuses memory buffers aggressively (tensors that are never alive at the same time share the same allocation)
- Tensor Layout Optimization - it may reorder data internally if faster for the selected kernels (it's transparent).
- Dynamic Shape Handling - with optimization profiles, it precomputes strategies for a range of input shapes
- Dead Code & No-Op Elimination - unreachable layers, identity operations, and redundant reformats get stripped from the graph.

---

**CUDA Graphs**

---

- Captures a fixed sequence of GPU operations once and replays them without CPU involvement (replay as a single unit, rather than launching kernels one by one eliminating CPU & traffic overhead)
- Limitations: input/output shapes must stay fixed between replays, no if/else flow control, opaque to step-by-step debugging

---

Runtime optimization: GPU Warm-Up

---

Run small/synthetic inference to go through e.g.:

- CUDA context initialization (kernels, allocators, and caches)
- Kernel JIT/`torch.compile` compilation (or use cached engine with TensorRT)
- Model weight loading
- cuDNN algorithm selection (cuDNN benchmarks several convolution algorithms)

```python
with torch.no_grad():
    for _ in range(10):
        _ = model(dummy)
torch.cuda.synchronize()
```

---

**Automatic Mixed Precision - `torch.amp`**

---

Runs eligible ops in `float16`/`bfloat16` while keeping numerically sensitive ops in `float32` (alternative - `.half()`, no `autocast` context needed); used during training but benefits inference indirectly since the model is numerically robust in lower precision.

| **Without AMP:**                     | **With AMP:**                                 |
| `optimizer.zero_grad()`              | `→ optimizer.zero_grad()`                     |
|                                      | `→ scaler = torch.cuda.amp.GradScaler()`      |
|                                      | `→ with torch.autocast(dtype=torch.float16):` |
| `output = model(input)`              | `→     output = model(input)`                 |
| `metric = criterion(output, target)` | `→     metric = criterion(output, target)`    |
| `metric.backward()`                  | `→ scaler.scale(loss).backward()`             |
| `optimizer.step()`                   | `→ scaler.step(optimizer)`                    |
|                                      | `→ scaler.update()`                           |

---

**Model size compression techniques**

---

- Quantisation - See AMP, `.half()`
- Pruning - Removed weights (unstructured pruning) or neurons/heads/layers (structured pruning) which contribute little to the output (the latter not possible as aggressively before accuracy drops)
- Distillation - Training a smaller "student" model to mimic a larger "teacher" model (uses full output distribution rather than ground truth labels).
- Sparsity - Ampere+ GPUs support sparsity where in every block of 4 weights, 2 must be zero. This allows to skip the zeros and roughly double throughput. Compromise between unstructured pruning (compressible but slow) and structured pruning (fast but coarse).

---

**PyTorch Model Optimization (Training)**

---

- Gradient checkpointing: `torch.utils.checkpoint`
- Other: model sharding & generally data/model parallelism

---

**Gradient checkpointing - `torch.utils.checkpoint`**

---

When you are running into OOM during training and cannot reduce batch size further - trades compute for memory; instead of storing all intermediate activations for the backward pass it recompute them on the fly during backprop.

---

**Data parallelism vs Model parallelism**

---

- Data parallelism - replicate the entire model on every GPU, split the input batch across GPUs, and aggregate gradients after the backward pass.
- Model parallelism - splits the model itself across multiple GPUs (tensor parallelism, pipeline parallelism)

---

**PyTorch parallelization strategies**

---

Progressively:

- Data Parallel (native) - single-process, multiple-GPUs; splits batches across GPUs but uses one process with a GIL  (global interpreter lock) bottleneck; legacy option, quick prototyping on a single machine to avoid multi-process setup
- Distributed Data Parallel (DDP; native) - default starting point; use when model fits in one GPU memory and you want to train faster across multiple GPUs (under ~1-2B parameters).
- Fully Sharded Data Parallel (FSDP; native) - shards model parameters, gradients, and optimizer states across GPUs (inspired by ZeRO-3); use for models which don't fit on one GPU (OOM with DDP; 1B–30B params). Also useful when you want mixed precision + activation checkpointing with minimal code.
- Tensor Parallel (native) - use when individual layers are too large to fit on one GPU even with FSDP (e.g. massive hidden dimensions), usually within a single node where GPUs with far NVLink interconnect.
- Pipeline Parallel (native) - splits the model by layers across GPUs, with micro-batching to keep GPUs busy; use when you have many layers and multiple nodes but inter-node bandwidth is limited.
- 3D Parallelism - use when nothing else is enough; TP within a node (fast NVLink), PP across nodes, FSDP/DDP across replica groups.
- DeepSpeed (Lightning) - alternative to FSDP when training large models on limited GPU memory or middle ground between DDP and full sharding; ZeRO memory optimization, pipeline parallelism (sharding optimizer states, gradients, and parameters).

---

**p99**

---

**99th percentile**

It is a statistical measure used to describe the value below which 99% of observations fall (p95 corresponds to 95%, p999 to 99.9%).


---

Triton Inference Server vs. TorchServe

---

Triton Inference Server (NVIDIA) is backend-agnostic and performance-obsessed. It serves TensorRT, ONNX, TorchScript, TensorFlow, and even Python models under one roof. Its power features:

- Concurrent model execution: multiple models (or instances of one model) sharing GPU memory and compute simultaneously
- Model pipelines/ensembles: chain preprocessing → model → postprocessing as a DAG, all within the server, avoiding round-trips (it's also why a single Triton process can have multiple model workers)
- Dynamic batching built into the runtime
- CUDA graphs integration for fixed-shape workloads - eliminates kernel launch overhead

TorchServe is PyTorch-native and simpler to onboard. You package a model as a `.mar` archive with a handler. Key traits:

- Native support for `torch.compile`, quantization, and PyTorch-specific workflows
- Per-model versioning and A/B routing built-in
- Easier custom pre/postprocessing via Python handlers
- Less raw performance ceiling than Triton, but much lower operational friction

---

Batching Strategies

---

Trade-off: GPU utilization and throughput vs. latency

- Static batching - fix a batch size, wait until it's full, then run inference
- Dynamic batching - the server accumulates requests for a short window and runs whatever arrived, lepending on load either executes like static batching or immediately.
- Model specific - e.g. continuous batching used with LLMs

---

Load Balancing Across GPU Instances

---

Because the state is large and due to GPU memory specifics strategies like round-robin are not effective. Instead:

- Least-connections / least-outstanding-requests
- Sticky routing for KV-cache reuse (LLM-specific)

GPU utilization alone is a poor autoscaling signal. Instead:

- Queue depth
- p95/p99 latency
- Time-to-first-token

Health checks:

- Run small inference since the GPU instances can be alive while being silently degraded (VRAM fragmented, NCCL in a bad state).

Minimum instance count (to prevent live warm-up), apply predictive pre-warming for predictable traffic pattern.

---

What if a model suddenly silently degrades in production?

---

Silently means: no crash/time-out/500/out-of-SLA latency - it's about predictive performance of the model.

Common causes:

- Data drift (input)
- Feature pipeline drift (e.g. different normalization) or dependency version skew
- Numerical issues from quantization or precision changes (model recompiled/reexported or hardware-induced nondeterminism on a different inference instance)

Detection strategies:

- Output distribution monitoring
- Input distribution monitoring
- Shadow scoring - selective run of the new/suspect model in parallel with the production one
- Scientific (domain-specific) canary evaluation (on hold-out set)

---

Troubleshooting model working well offline but poorly in production

---

These two differ in three ways: data, code path, and environment.

Process of ellimination:

1. Plug production model into offline eval pipeline and dataset → if poor then model corrupted, incorrectly serialized, model or dependency version is skewed, precision is mismatched or numerical instability crept in, memory or compute-bound
2. Log production input and plug into offline pipeline and model → if good then production pipeline (synchronization, loading/ETL); if bad then it would indicate a distribution shift (new patterns, new edge cases)
3. Check the eval methodology (harder for me to speculate - eval dataset not representative, label leakage, metric mismatch)
4. Last resort - shadow (model) mode & **log everything**

---

Challenges of running Foundation Model in Production

---

1. Get it running - size matters: basics (tracking provenance, versioning, packaging, Docker limitations), multiple GPUs & thus parallelism, batching strategies / latency / pipelining
2. Keep it correct - account for silent degradation (I/O distribution, scientific canaries), but may be hard to run eval at scale
3. Keep it efficient - ETL (parallelized), Automatic Mixed Precision, JIT(trace, script)→quantize→`torch.compile`→compilation backends→CUDAGraph, pick instance and autoscaling
4. Keep it trustworthy - know your regulatory requirements, risk model & failure modes, red-teaming (depending on a model)

---

How would you reduce P99 latency by 50%?

---

- Trick question.
- It depends on where the latency for the worst 1% of requests is coming from.
- The first step is always profiling.

Common P99 culprits:

- Queue wait time spikes under bursty load → reduce max batch delay, autoscale faster
- GPU inference time blows up on outlier inputs → input size bucketing, TensorRT or `torch.compile`, CUDA graphs
- Preprocessing is CPU-bound and occasionally slow (garbage collection pause, lock contention) → separate it to CPU instances, eliminate Python GIL contention with multiprocessing, different serialization format
- Cold start on a new instance that just joined the pool → pre-warm instances, TensorRT engine caching
- A single slow request holding up others in a batch → reduce max batch delay
- _Request Hedging_ → send input to two instances and return the quickest one (costly)

---

`torch.export`

---

For portability of the model, to optimize for infertece use e.g. TensorRT.

---

`safetensors`

---

`safetensors` supports `mmap=True`, meaning weights are loaded lazily from disk rather than all at once into RAM

- Faster loading - no pickle deserialization overhead, direct tensor reads - meaningful for startup time.
- Safety - no arbitrary code execution on load unlike pickle-based formats - relevant for supply chain security.

---

Gradient clipping

---

Clips the norm of gradients before the optimizer step. Prevents exploding gradients - a failure mode where gradients grow uncontrollably, causing the optimizer to take a massive step that destroys learned weights. Depends on layers used / model architecture - RNNs almost always need it, CNNs less so.

```python
optimizer.zero_grad()
loss.backward()
torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
optimizer.step()
```

---

PyTorch Profiler with TensorBoard

---

```python
with torch.profiler.profile(
    activities=[
        torch.profiler.ProfilerActivity.CPU,
        torch.profiler.ProfilerActivity.CUDA,
    ],
    on_trace_ready=torch.profiler.tensorboard_trace_handler('./log'),
) as profiler:
    # Profiled code
    profiler.step()
```

```shell
docker run -it --rm \
  -p 6006:6006 \
  -v $PWD/log:/log \
  tensorflow/tensorflow \
  bash -c "pip install torch-tb-profiler && tensorboard --logdir /log --host 0.0.0.0"
```

---

Writing technical specification

---

- Start with external requirements, and break down into internal
- List functional and non-functional
- Specify inference aspects
- Specify infrastructure
- Specify API contract
- Operational requirements
- Verification plan

---

Making cross-functional architectural decisions

---

How I made decisions:

1. Start with the problem, not the solution
2. Write it down before discussing it (for tracability, provenance)
3. Involve the right people at the right time
4. Prototype before committing

How I get buy-in:

1. Transparency about trade-offs
2. Gave credit for dissent (builds trust)
3. Demonstrated follow-through (KPIs, OKRs)
4. Connected decisions to team pain (postmortems)

---

Describe how you'd design a benchmarking pipeline for evaluating a model

---

Core design principles:

1. Reproducibility is non-negotiable
2. Separation of concerns

Architecture:

- Dataset registry
- Task definitions (eval protocol, metrics, baseline)
- Execution engine
- Statistical rigor (not just single value metrics but also confidence intervals)
- Regression monitoring (and trends)
- (Automated) reporting

---

Pipeline Architecture Notes

---

- Preprocessing, as a separate stage
- Stream not materialize
- Prefetching, parallelism

---

How do foundation models for tabular data differ from NLP/vision foundation models?

---

- No natural column order
- Heterogeneous column types
- No universal vocabulary
- Schema varies across tables

---

How do you decide when a research prototype is ready for production?
How do you validate that a model release was safe to ship?

---

Ready:

1. Functional correctness - does it produce the right results? (eval tests/metrics, edge cases, deterministic)
2. Robustness - does it fail gracefully? (malformed input, load, etc.)
3. Performance - does it meet SLAs?
4. Operability - can someone other than the author run and maintain it? (separation of config, self-explanatory code/docs, health checks, observability)
5. Reproducibility - can we recreate this artifact?
6. Documentation of limitations.

Safe:

1. Provenance, regulatory requirements
2. Model packaging
3. Automated (regulatory) acceptance tests
- Ground-truth validation
- Regression tests
- Edge case tests
- Determinism tests
- Performance tests (compute & predictive)
- Scientific canaries (on hold-out dataset)
4. Manual subject matter review on random sample
5. Regulatory documentation
6. Deployment

---

How do you handle disagreements with researchers about engineering constraints vs. model quality trade-offs?

---

1. Make the trade-off concrete and quantified
2. Understand what the stakeholders actually care about
3. Propose alternatives, not just rejections
4. Establish shared ownership of the deployment specification
5. Timebox exploration, then converge
6. Escalate clearly when needed

---

Python Engineering Excellence

---

Automated pre-commit hooks and CI checks:

- Formatting
- Linting
- Type checking
- Import sorting
- Cognitive complexity

Code review culture:

- Production/pipeline code: Full review - correctness, error handling, edge cases, performance implications.
- Research/experiment code: Lighter review - focus on correctness of the core logic and data handling
- Shared libraries: Strictest review

Testing strategy:

- Critical path gets tested
- Experiments don't need unit tests
- Integration tests over unit tests for pipelines

Dependency management:

- Automated updates
- All pinned to at least major

Code structure:

- Separation of model / data / training / evaluation / inference / packaging
- Configuration externalized

---

What is in-context learning?

---

In-context learning is when a language model learns to perform a task from examples provided in the prompt, without any updates to its weights. Comes in a few flavors: zero-shot (just a task description), one-shot (one example), and few-shot (several examples).

---