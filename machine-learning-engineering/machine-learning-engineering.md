# MACHINE LEARNING ENGINEERING

---

**Optimizations**

---

- GPU Warm-Up (so that CUDA initializes kernels, allocators, and caches)
```python
with torch.no_grad():
    for _ in range(10):
        _ = model(dummy)
torch.cuda.synchronize()
```
- `torch.compile` is a replacement for `torch.jit`; it fuses ops, eliminates overhead, can use third-party backends
- Automatic Mixed Precision (`torch.amp`) - runs eligible ops in FP16/BF16 while keeping numerically sensitive ops in FP32 (alternative - `.half()`, no `autocast` context needed); also during training
- CUDA Graphs - captures a fixed sequence of GPU operations and replays them without CPU involvement
- TensorRT via ONNX (`torch.onnx.export` & build TRT engine, or `torch_tensorrt.compile`)
- Training specific: gradient checkpointing (`torch.utils.checkpoint`) - trades compute for memory by recomputing activations during backward instead of storing them (lets you train larger models)

Combinations: Warm-up + AMP + `torch.compile`, AMP + CUDA Graphs, TensorRT

---

**torch.compile modes & backends**

---

Modes:

- `default` - balanced
- `reduce-overhead` - minimizes launch overhead via CUDA graphs
- `max-autotune` - tries many kernel variants, slow compile but fastest runtime

Built-in:

- `inductor` (default) - generates optimized C++/Triton kernels; rewrites your model's operations into faster kernels. It fuses multiple ops into single Triton kernels, eliminates redundant memory traffic, optimizes memory layout, and generates new GPU code that's fundamentally more efficient than the original ops. It changes what runs on the GPU.
- `eager` (debugging) - runs normal PyTorch eager mode
- `aot_eager` (debugging) - runs ahead-of-time tracing but executes eagerly

Third-party:

- `tensorrt` - via TensorRT
- `ipex` - Intel Extension for PyTorch
- `onnxrt` - routes through ONNX Runtime for inference
- `tvm` - Apache TVM, an open-source ML compiler; broad hardware support
- `cudagraphs` - captures and replays CUDA graphs to reduce kernel launch overhead; doesn't touch the kernels at all.
  It records the exact sequence of existing GPU operations once, then replays that recording without CPU involvement.
  It changes how the GPU is told to run - eliminating the per-kernel launch overhead from the CPU.
  The kernels themselves are identical to eager mode; use if `inductor` fails to compile your model

---

**TensorRT**

---

`TensorRT` can be a backend for `torch.compile` (recommended way for `TensorRT` with `PyTorch`, over `ONNX` export and compilation):
```python
model = torch.compile(model, backend='tensorrt')
```

- Need to be done on the target architecture (benchmarks multiple kernel implementations and picking the fastest one for your specific GPU), but to avoid the overhead the (compiled) engine can be cached.
- `CudaGraph` - captures a sequence of GPU operations and replays them as a single unit, rather than launching kernels one by one (eliminating also CPU overhead); input/output shapes must stay fixed between replays; built into `inductor` & `cudagraphs` but also applied by `TensorRT`.

---

**TensorRT Optimizations**

---

- Layer & Tensor Fusion - merges sequences of operations into single kernels, eliminating intermediate memory reads/writes and kernel launch overhead.
- Precision Calibration - beyond just FP16, TensorRT supports INT8 quantization, runs a calibration dataset through your network to determine per-tensor dynamic ranges, then quantizes weights and activations to 8-bit integers.
- Kernel Auto-Tuning - for each fused operation, TensorRT has a library of kernel implementations (different tiling strategies, memory access patterns, etc.), it benchmarks all candidates on your specific GPU and selects the fastest.
- Memory Optimization - analyzes the entire graph's lifetime of tensors and reuses memory buffers aggressively (tensors that are never alive at the same time share the same allocation)
- Tensor Layout Optimization - it may reorder data internally if faster for the selected kernels (it's transparent).
- Dynamic Shape Handling - with optimization profiles, it precomputes strategies for a range of input shapes
- Dead Code & No-Op Elimination - unreachable layers, identity operations, and redundant reformats get stripped from the graph.

---

**TensorRT vs. `torch.compile`**

---

- TensorRT for highly optimized outcome, but for a specific GPU, specific input shapes, and specific precision.
- `torch.compile` for more flexible outcome, dynamic shapes, less operational complexity.

Questions to answer:

- How stable are your input shapes?
- What's your deployment target?
- Do you need training + serving in one codebase?

---

**DeepSpeed vs TensorRT**

---

- `DeepSpeed` - training and inference optimization library for large-scale distributed deep learning; ZeRO memory optimization, pipeline parallelism (sharding optimizer states, gradients, and parameters)
- `TensorRT` - inference-only; compiles and optimizes trained models for NVIDIA GPUs; Kernel fusion, quantization, layer fusion

---

**PyTorch parallelization strategies**

---

Progressively:

- DP (native) - single-process, multi-GPU; splits batches across GPUs but uses one process with a GIL bottleneck; legacy option, quick prototyping on a single machine to avoid multi-process setup
- Distributed Data Parallel (DDP; native) - default starting point; use when model fits in one GPU memory and you want to train faster across multiple GPUs (under ~1-2B parameters).
- Fully Sharded Data Parallel (FSDP; native) - shards model parameters, gradients, and optimizer states across GPUs (inspired by ZeRO-3); use for models which don't fit on one GPU (OOM with DDP; 1B–30B params). Also useful when you want mixed precision + activation checkpointing with minimal code.
- Tensor Parallel (native) - use when individual layers are too large to fit on one GPU even with FSDP (e.g. massive hidden dimensions), usually within a single node where GPUs with far NVLink interconnect.
- Pipeline Parallel (native) - splits the model by layers across GPUs, with micro-batching to keep GPUs busy; use when you have many layers and multiple nodes but inter-node bandwidth is limited.
- 3D Parallelism - use when nothing else is enough; TP within a node (fast NVLink), PP across nodes, FSDP/DDP across replica groups.
- DeepSpeed (Lightning) - alternative to FSDP when training large models on limited GPU memory or middle ground between DDP and full sharding.

---

**p99**

---

**99th percentile**

It is a statistical measure used to describe the value below which 99% of observations fall (p95 corresponds to 95%, p999 to 99.9%).

---

Function of `torch.no_grad()` and `model.eval()`

---

- `model.eval()` switches certain layers into evaluation mode (layers like `BatchNorm` and `Dropout` behave differently during training vs. inference).
- `torch.no_grad()` is a context manager that disables gradient tracking. Autograd, PyTorch's automatic differentiation engine would otherwise build the autograd graph (traversed it in reverse in `.backward()` to compute gradients)

---

`torch.cuda.amp` (Automatic Mixed Precision)

---

AMP runs parts of your model in `float16` (or `bfloat16`) while keeping numerically sensitive ops in `float32`.

```python
optimizer.zero_grad()
outputs = model(x_batch)
metric = criterion(outputs.squeeze(), y_batch)
metric.backward()
optimizer.step()
```

becomes:

```python
optimizer.zero_grad()
scaler = torch.cuda.amp.GradScaler()
with torch.autocast(device_type='cuda', dtype=torch.float16):
    output = model(input)
    loss = criterion(output, target)
scaler.scale(loss).backward()
scaler.step(optimizer)
scaler.update()
```

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

GPU Warmup

---

Run small/synthetic inference to go through e.g.:

- CUDA context initialization
- Kernel JIT/`torch.compile` compilation (or use cached engine with TensorRT)
- Model weight loading
- cuDNN algorithm selection (cuDNN benchmarks several convolution algorithms)

---

What if a model suddenly silently degrades in production?

---

Silently means: no crash/time-out/500/out of latency SLA - it's about predictive performance of the model.

Common causes:

- Data drift (input)
- Feature pipeline drift (e.g. different normalization) or dependency version skew
- Numerical issues from quantization or precision changes (model recompiled/reexported or hardware-induced nondeterminism on a different inference instance)

Detection strategies:

- Output distribution monitoring
- Input distribution monitoring
- Shadow scoring - selective run of the new/suspect model with the production one
- Scientific (domain-specific) canary evaluation (on hold-out set)

---

Troubleshooting model working well offline but poorly in production

---

These two differ in three ways: data, code path, and environment.

Process of ellimination:

1. Plug production model into offline eval pipeline and dataset → if poor then model corrupted, incorrectly serialized, model or dependency version is skewed, precision is mismatched or numerical instability crept in
2. Log production input and plug into offline pipeline and model → if good then production pipeline; if bad then it's distribution shift (new patterns, new edge cases)
3. Check the eval methodology (harder for me to speculate - eval dataset not representative, label leakage, metric mismatch)
4. Last resort - shadow (model) mode & **log everything**

---

Challenges of running Foundation Model in Production

---

1. Get it running - size matters: basics (tracking provenance, versioning, packaging, Docker limitations), multiple GPUs & thus parallelism, batching strategies / latency / pipelining
2. Keep it correct - account for silent degradation (I/O distribution, scientific canaries), but may be hard to run evan at scale
3. Keep it efficient - ETL (parallelized), JIT→quantize→`torch.compile`→compilation backends→CUDAGraph, pick instance and autoscaling
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

Gradient checkpointing

---

When you are running into OOM during training and cannot reduce batch size further - instead of storing all intermediate activations for the backward pass, recompute them on the fly during backprop.
