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

**Quantization**

---

- `bf16` has worse precision than `fp16` but a much bigger dynamic range (different mantissa exponent split); easier for `fp16` to underflow to zero or overflow to infinity, and precision loss is usually acceptable as gradients are noisy anyway thus `bf16` improves training stability (though even then gradient clipping remains relevant)
- Progressively by impact on quality:
  - `f16`/`bf16` (common, well supported)
  - `fp8` (supported by H100, emerging standard on Blackwell)
  - `int8` (still quite safe - ~98%, well supported)
  - AWQ (Activation-Aware Weight Quantization, 4-bit, 96-99%, beats GPTQ)
  - GPTQ (Generalized Post-Training Quantization, 4-bit, 95-98%)
- For medical/clinical vision models sensitivity to quantization is an issue and going below `fp8` could be risky (`fp8` support was not available couple years back)
- Post-Training Quantization (PTQ, like `.half()` but for a fundamentally lower-precision format, unlike a simple cast it requires calibration to find the best mapping), Quantization-Aware Training (QAT, e.g. AMP)

---

**Automatic Mixed Precision - `torch.amp`**

---

Runs eligible ops in `float16`/`bfloat16` while keeping numerically sensitive ops in `float32` (alternative - `.half()`, no `autocast` context needed); used during training but benefits inference indirectly since the model is numerically robust in lower precision.

| **Without AMP:**                     | **With AMP (specifically for float16):**      |
| ------------------------------------ | --------------------------------------------- |
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

- Quantisation - See: AMP, `.half()`, quantization card
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

Happens because model memory footprint includes weights, gradients, optimizer states.

Progressively:

- Data Parallel (native) - single-process bottleneck, splits batches across multiple-GPUs, aggregates gradients and updates model; legacy option, quick prototyping on a single machine to avoid multi-process setup
- Distributed Data Parallel (DDP; native) - default starting point; use when model fits in one GPU memory and you want to train faster across multiple GPUs with one process per GPU (under ~1-2B parameters).
- Fully Sharded Data Parallel (FSDP; native) - shards model parameters, gradients, and optimizer states across GPUs (inspired by ZeRO-3); use for models which do not fit on one GPU (OOM with DDP; 1B–30B params).
- Tensor Parallel (native) - use when individual layers are too large to fit on one GPU even with FSDP (e.g. massive hidden dimensions), usually within a single node where GPUs with fast NVLink interconnect.
- Pipeline Parallel (native) - splits the model by layers across GPUs; use when you have many layers and multiple nodes but inter-node bandwidth is limited.
- 3D Parallelism - use when nothing else is enough; TP within a node (fast NVLink), PP across nodes, FSDP/DDP across replica groups.
- DeepSpeed (Lightning) - alternative to FSDP when training large models on limited GPU memory or middle ground between DDP and full sharding; ZeRO - the key observation is that during training, each piece of state (optimizer states, gradients, parameters) is only actively needed for a brief window.

Note: Question - What is the bottleneck in large-scale training - compute, memory, or interconnect? Answer - depends (on model architecture, batch and state sizes, applied parallelism), profile first to find out!

---

**Serving models at scale**

---

1. Inference Optimization - see: model size compression (quantization, pruning, distillation), compilation/optimization, stream not materialize, pick batching mechanisms
2. Model packaging - version everything, reproducibility and provenance non-negotiable
3. Serving architecture - disaggregate inference pipeline onto different compute (also mind tensor / pipeline parallelism, inference instance), load balancing, request queuing
  - Also where one would look at inference servers (Triton Inference Server, TorchServe, Ray Serve, other LLM-specialized), application layer API (e.g. FastAPI to handle auth, routing, rate limiting, business logic) and use case (streaming chat vs. offline queued work flow in clinical/medical setting)
  - If the product is a static DAG known at deploy time then Triton could handle it (but no notion of retries, timeouts, or error handling per graph node, otherwise Ray Serve)
4. Resource management - autoscaling, request (size) binning, caching (e.g. embeddings in clinical/medical context)
5. Ops & governance: monitoring (online metrics, drift detection, latency), logging, health checks, scheduled scientific canaries, regulatory (risk model, failure modes, audit acceptance tests), rollout (A/B tests, feature flags)

Note: In case of research breakthrough changing the architecture revisit all.

---

**Challenges of running Foundation Model in Production**

---

1. Get it running - size matters: basics (tracking provenance, versioning, packaging, Docker limitations), multiple GPUs & thus parallelism, batching strategies / latency / pipelining
2. Keep it correct - account for silent degradation (I/O distribution, scientific canaries), but may be hard to run eval at scale
3. Keep it efficient - ETL (parallelized), Automatic Mixed Precision, JIT(trace, script)→quantize→`torch.compile`→compilation backends→CUDAGraph, pick instance and autoscaling
4. Keep it trustworthy - know your regulatory requirements, risk model & failure modes, red-teaming (depending on a model)

---

**Triton Inference Server vs. TorchServe**

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

**Batching Strategies**

---

Trade-off: GPU utilization and throughput vs. latency

- Static batching - fix a batch size, wait until it's full, then run inference
- Dynamic batching - the server accumulates requests for a short window and runs whatever arrived, lepending on load either executes like static batching or immediately.
- Model specific - e.g. continuous batching used with LLMs

---

**Load Balancing Across GPU Instances**

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

**What if a model suddenly silently degrades in production?**

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

**Troubleshooting model working well offline but poorly in production**

---

These two differ in three ways: data, code path, and environment.

Process of ellimination:

1. Plug production model into offline eval pipeline and dataset → if poor then model corrupted, incorrectly serialized, model or dependency version is skewed, precision is mismatched or numerical instability crept in, memory or compute-bound
2. Log production input and plug into offline pipeline and model → if good then production pipeline (synchronization, loading/ETL); if bad then it would indicate a distribution shift (new patterns, new edge cases)
3. Check the eval methodology (harder for me to speculate - eval dataset not representative, label leakage, metric mismatch)
4. Last resort - shadow (model) mode & **log everything**

---

**(Breaking Change) Rollout Phases**

---

- Internal shadow mode
- Opt-in beta via model registry
- Canary on live traffic
- Gradual migration with deprecation date
- Sunset with clear error messages

---

**How would you reduce P99 latency by 50%?**

---

- Trick question.
- It depends on where the latency for the worst 1% of requests is coming from.
- The first step is always profiling.

Common P99 culprits:

- _General lever_ → quantization (reduces model size and memory bandwidth)
- Queue wait time spikes under bursty load → reduce max batch delay, autoscale faster
- GPU inference time blows up on outlier inputs → input size bucketing, TensorRT or `torch.compile`, CUDA graphs
- Preprocessing is CPU-bound and occasionally slow (garbage collection pause, lock contention) → separate it to CPU instances, eliminate Python GIL contention with multiprocessing, different serialization format
- Cold start on a new instance that just joined the pool → pre-warm instances, TensorRT engine caching
- A single slow request holding up others in a batch → reduce max batch delay
- _Request Hedging_ → send input to two instances and return the quickest one (costly)

---

**`torch.export`**

---

For portability of the model, to optimize for infertece use e.g. TensorRT.

---

**`safetensors`**

---

`safetensors` supports `mmap=True`, meaning weights are loaded lazily from disk rather than all at once into RAM

- Faster loading - no pickle deserialization overhead, direct tensor reads - meaningful for startup time.
- Safety - no arbitrary code execution on load unlike pickle-based formats - relevant for supply chain security.

---

**Gradient clipping**

---

Clips the norm of gradients before the optimizer step. Prevents exploding gradients - a failure mode where gradients grow uncontrollably, causing the optimizer to take a massive step that destroys learned weights. Depends on layers used / model architecture - RNNs almost always need it, CNNs less so.

```python
optimizer.zero_grad()
loss.backward()
torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
optimizer.step()
```

---

**PyTorch Profiler with TensorBoard**

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
    profiler.step() # (use if profiled code contains a loop)
```

```shell
docker run -it --rm \
  -p 6006:6006 \
  -v $PWD/log:/log \
  tensorflow/tensorflow \
  bash -c "pip install torch-tb-profiler && tensorboard --logdir /log --host 0.0.0.0"
```

---

**Writing technical specification**

---

- Start with external requirements, and break down into internal
- List functional and non-functional
- Specify inference aspects
- Specify infrastructure
- Specify API contract
- Operational requirements
- Verification plan

---

**Making cross-functional architectural decisions**

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

**Describe how you'd design a benchmarking pipeline for evaluating a model**

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

**How do foundation models for tabular data differ from NLP/vision foundation models?**

---

- No natural column order
- Heterogeneous column types
- No universal vocabulary
- Schema varies across tables

---

- **How do you decide when a research prototype is ready for production?**
- **How do you validate that a model release was safe to ship?**
- **We have a research breakthrough that changes the architecture - how to get it intoproduction?**

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
3. Automated (regulatory) acceptance tests (note: for FM-sized test sets - **tiered validation system**)
- Algorithm performance tests - offline metrics on ground-truth
- Regression tests
- Behavioral testing - sliced evaluation across subgroups, edge case tests, know failure modes
- Determinism tests
- Profiling tests (latency, compute & memory requirements)
- Scientific canaries (on hold-out dataset)
- FM caveat: Foundation model is evaluated on breadth across tasks, task-specific model is evaluated on depth on that particular task
4. Manual subject matter review on random sample
5. Regulatory documentation
6. Deployment

---

**How do you handle disagreements with researchers about engineering constraints vs. model quality trade-offs?**

---

1. Make the trade-off concrete and quantified
2. Understand what the stakeholders actually care about
3. Propose alternatives, not just rejections
4. Document deployment specification AND share its ownership
5. Timebox exploration, then converge
6. Escalate clearly when needed

---

**How does one onboard onto an architecture one didn't design?**

---

1. Start With the data, not the code; Understand the task, look at examples, tests
2. Understand the architecture top-down, timeboxed
3. Run everything locally
4. Find the load-bearing decisions
5. Talk to people strategically and in practical context (here is also where asking about the history of decisions is important)
6. Start small with contributions, connect the contributions to build bigger picture

---

**Python Engineering Excellence**

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

**What is in-context learning?**

---

In-context learning is when a language model learns to perform a task from examples provided in the prompt, without any updates to its weights. Comes in a few flavors: zero-shot (just a task description), one-shot (one example), and few-shot (several examples).

---

**RAG (Retrieval-Augmented Generation)**

---

Combines a retrieval system with a language model to answer questions using external knowledge (and to tap into knowledge past its training cut-off point):

1. Index your data - documents split into chunks, converted into vector embeddings, stored in a vector database
2. Query comes in - query is also converted into an embedding with the same model
3. Retrieve relevant chunks - system searches the vector database for chunks whose embeddings most similar to the query embedding
4. Augment the prompt - retrieved chunks are injected into the LLM's prompt context with the original question
5. Generate the answer - LLM reads both and produces a grounded response

---

**Supervised/semi-supervised/unsupervised learning**

---

- Supervised learning - every training example has a label (correct answer)
- Unsupervised learning - no labels at all, model finds structure (clustering, dimensionality reduction; PCA, autoencoders)
- Semi-Supervised learning - middle ground when labels are expensive or known for small part of the data (training based on confidence of predictions on unlabeled data, or data similarity e.g. in graph-based approach, or where learned structure is coupled with classification like in GANs)

---

**Training, testing, validation data**

---

- Training data - portion of the data that training algorithm uses to learn patterns, i.e. adjust its parameters (weights/biases) such that the error on this set is minimized
- Testing set - not seen by the training algorithm and used to gauge the models performance (used once at the end on the trained model)
- Validation data - portion of data taken out of the training set to run hyperparameter tuning and/or model selection

---

**Bias/Variance Trade-off**

---

- High bias means the model is too simple (underfitting)
- High variance means it is too sensitive to training data (overfitting)

---

**Dropout Layer**

---

- A dropout layer is a regularization technique that randomly sets a fraction of neurons during each training step to zero and they do not contribute to that forward pass
- Forces the network to not rely too heavily on any single neuron or pathway
- Is is a tool for reducing variance (overfitting) when the network is large relative to the dataset
- Network learns more redundant, robust representations, which generalizes better to unseen data; kind of an equivalent to training an ensemble of many networks

Side note: Batching (particularly when shuffled) is also viewed as a regularizer to prevent overfitting.

---

**How do you detect overfitting in large-scale systems?**

---

General rule still true: training loss keeps going down, validation loss stops improving or rises.

Large-scale challenges:

- Evaluation is expensive so you are working with sampled estimates which introduces noise into the signal itself
- The validation set itself can indirectly leak
- Overfitting may look different - you may overfit to train set distribution or the metric (as a proxy for the real thing)

Detection & resolution (examples):

- Held-out test sets you rarely touch
- Monitor not entire validation set but slices of it across different grouping criteria
- Early stopping with a patience budget

---

**How do you evaluate a model offline vs online?**

---

The basic distinction comes from the fact that you do not have the ground truth when evaluating online.

Thus online you either use proxy metrics, or you run A/B testing, shadow mode with two models, validating model trained on failure modes.

---

**How would you choose between: linear models vs tree-based models vs deep learning?**

---

- Mostly driven by data characteristics (dataset size, completeness, linearity, how well enginered/cleaned the features are).
- Start with with simplest and go to more complex when the decision is justified.
- Interpretability or latency can be a factor


---

**Why is feature scaling & normalization important?**

---

Puts features on comparable ranges so gradient-based optimizers converge faster and distance-based algorithms (like SVM) are not dominated by large-magnitude features. Derivatives of the loss on non-normalized features would also be on different scales which would make gradient descent unstable and converge slower.

---

**Classification vs. regression?**

---

Classification predicts discrete labels, regression predicts continuous values. A problem might be both if the continuous values get binned into categories (which may help the model to learn and generalize better, and can also be useful in itself depending on a use case). Choice determines loss function and output layer.

---

**How to fix exploding gradients?**

---

Gradient clipping, batch normalization, lower learning rate, weight regularization, or architectural changes (e.g. less layers, skip connections, LSTM instead of vanilla RNN).

---

**Techniques to address landing in sub-optimal local minima**

---

- Try a variety of initialization techniques - Glorot, He, Xavier
- Stochastic/mini-batch gradient descent - sampling noise helps escape shallow minima
- Momentum-based optimizers - e.g. Adam, SGD+momentum, to push through flat regions and saddle points
- Learning rate scheduling - cosine annealing, warm restarts

---

**AUC (Area Under the Curve)**

---

ROC curve (Receiver Operating Characteristic) in ML contexts. Probability that the model ranks a random positive above a random negative. 0.5 = random, 1.0 = perfect

What the ROC curve plots:

- x-axis: False Positive Rate `F_P_R=FP/(FP+TN)`. Of all the actual negatives, how many did the model incorrectly flag?
- y-axis: True Positive Rate `T_P_R=TP/(TP+FN)`. aka sensitivity, recall. Of all the actual positives, how many did the model correctly catch?

Other (related) metrics:

- Specificity, True Negative Rate `T_N_R=TN/(TN+FP`. Of the real negatives, how many did we correctly clear?
- Precision, Positive Predictive Value `P_P_V=TP/(TP+FP)`. Of the things we flagged, how many were actually positive?
- Accuracy: `(TP+TN)/(TP+TN+FP+FN)`. Of all predictions, how many were correct?

---

**What does "autoregressive" mean?**

---

Autoregressive means the model generates one element at a time, and each new element is conditioned on all previously generated elements.

The word comes from statistics - a regression where the input is the variable's own past values (auto = self).

---

**Dense vector vs embedding vs latent representation**

---

- Dense vector is the format
- Embedding is the process and its result - the act of mapping a discrete or high-dimensional object into a dense vector space such that geometric relationships reflect semantic ones
- Latent (hidden) representation is about what the vector contains - it's the hidden/abstract/compressed state or meaning that a model has inferred from input.

---

**p99**

---

**99th percentile**

It is a statistical measure used to describe the value below which 99% of observations fall (p95 corresponds to 95%, p999 to 99.9%).

---

**Differences between Polars and Pandas**

---

- Pandas is eager and single-threaded (GIL) with a Python/NumPy backend - every operation materializes immediately in memory
- Polars is built on Rust and Arrow with native multithreading, supports both eager and lazy execution, and uses a typed, index-free model (contrary to Pandas) with consistent null handling (Pandas conflates NaN and NULL), expressions are composable and close to SQL feel
- The practical upshot: Polars is a different performance regime, not just a faster Pandas

---

**Differences between Polars and Pandas (alternative)**

---

The fundamental difference is eager versus lazy execution. Pandas materializes every intermediate; Polars lets you build a logical plan with scan_parquet and a chain of operations, and doesn't execute until you call collect.
That lets the query optimizer do predicate pushdown, projection pushdown, and streaming execution — so instead of reading a 500 GB Parquet dataset into memory and then filtering, it reads only the row groups and columns the query actually needs.
Combined with the Rust + Arrow backend and real multithreading, that's a different performance regime from Pandas, not just a faster version of it. The trade-off is a less mature ecosystem and some API instability, especially around categoricals.

---

**Difference between Polars vs Pandas when filtering a DataFrame**

---

Lazy evaluation. With `pl.scan_parquet(...).filter(...).select(...).collect()`, Polars builds a logical plan and runs a query optimizer before executing - doing predicate/projection/slice pushdowns. Pandas executes eagerly, materializing every intermediate, so it has no chance to skip data it doesn't need.

---

**Properties of the Parquet format**

---

- Columnar storage, not row by row (good for selective column reads, strong compression)
- Hierarchical row group → column chunks → page structure (aids as a unit of parallelism and streaming)
- Per-row-group min/max statistics that enable predicate pushdown so engines skip non-matching data
- Typed schema with nested types (lists, structs, maps)
- Per-column encodings (dictionary for categoricals, RLE, delta for numeric, bit packing for small ints)
- Immutable files, append-only

---

**Parquet format (alternative description)**

---

Parquet is a columnar format with hierarchical row group and page structure. The columnar layout gives you selective column reads and better compression.
The row group statistics enable predicate pushdown, so query engines skip data that can't match a filter. It has a rich typed schema including nested types, per-column encodings like dictionary and RLE, and it's immutable, which makes it the natural substrate for partitioned datasets and table formats like Iceberg.
For ML pre-training pipelines, the combination of column pruning, predicate pushdown, and cheap Arrow interop is what makes it dominant.

---

**What are predicate pushdowns?**

---

- Predicate pushdown moves `filter()` conditions down to the scan so non-matching row groups are never read
- Projection pushdown reads only the columns the query actually uses
- Slice pushdown turns `.head(n)` or `.tail(n)` into a partial scan that stops early

---

**Cleaning dirty tabular data (CSV/Excel/Parquet from Common Crawl) for foundation model pre-training**

---

The objective is not cleaning one table for one task - it is heterogeneous tables so a foundation model can learn good priors across them. This means some 'dirtiness' is actually signal we want to preserve (real-world messiness the model should be robust to), and some is noise that will poison pre-training. The filtering criteria should reflect that distinction.

Work in layers:

- structural (encoding, delimiters, header detection, column count consistency, type-coercion)
- per-column (dtype consistency, semantic type detection, unit consistency)
- statistical quality checks (categorical or indexing cardinality, distribution sanity flagging skew or spikes, outliers)
- inter-column relationships (high correlation, near-duplicate rows)
- and table-level (min size, header semantics quality, content diversity, language)
- additional aspects: table idempotency and provenance, sampling for human inspection, hold out a clean eval set early, cheap checks before expensive ones
- do not: aggressively impute missing values (to let the model deal with the noise), normalize numerics globally (to not skew model to your preprocessing), over-filter (to keep distribution diverse)

For pre-training specifically, do not over-clean - preserve realistic messiness and missingness as signal, and prioritize de-duplication and corpus diversity over per-cell perfection.

---

**Tabular data cleaning aspects**

**Correlation-based filtering - computed along which dimension?**

**What does high correlation mean in this context?**

---

- Compute correlation across columns (pairwise between column vectors), not rows
- Column-wise correlation surfaces redundancy, derived columns, and likely duplicates or a unit-conversion artifact - all of which teach the model spurious "everything is correlated" priors and waste capacity. Typically drop one, but it depends on semantics: if both columns are genuinely independent measurements that happen to correlate, that's real signal worth keeping.

---

**[Why do tree-based models still outperform deep learning on tabular data? (Grinsztajn 2022)](https://arxiv.org/pdf/2207.08815)**

---

1. Tree-based models fit functions which are: threshold-based, non-continuous or regime-dependent (feature–target relationships change depending on the global system state); NNs prefer smooth, low-frequency functions.
2. Tree-based models ignore weak features by performing feature selection; NN tend to mix features and more sensitive to accumulating noise.
3. Columns have independent meaning and tree-based models preserve feature identity; NNs mix early which can blur feature identity (it is as if the features were sharing/overlapping identity; possibly also regularization / not relying overly on single neuron/weight can contribute).

---

**TabPFN**

---

TabPFN replaces the usual workflow of learning a single predictive function `f(x)` with a model that is trained to estimate:
"given this dataset, what is the probability distribution over possible functions that could explain it?"

At inference time, you provide a small labeled dataset + test points, and it directly outputs predictions in a single forward pass.

TabPFN v2 improvements: much larger pretraining scale, converting heterogeneous tabular features into unified token representations, went beyond classification (regression and other tabular tasks)

Side note: SAP-RPT-1 went further by going to multi-table / relational input type, going from synthetic to real-world data, encoding data as embeddings

---

**Chinchilla**

---

The core claim: Prior large language models (GPT-3, Gopher, etc.) which scaled parameters far faster than data (following Kaplan et al.) were significantly undertrained.
The law: For compute-optimal training, model size (N) and training tokens (D) should scale in equal proportion. If you double your compute budget, you should roughly double both the number of parameters and the number of training tokens.
The practical rule of thumb: You need approximately 20 tokens of training data per parameter. So a 7B model wants ~140B tokens.
Side note: given a fixed compute budget C (in FLOPs), and knowing that for transformers we have C≈6ND, the D=20N relationship gives N_opt≈√(C/120) and D_opt≈20*√(C/120)

Note: This economy makes sense in data-abundant contexts, in data-scarce setting question gets inverted: D is fixed, so the question becomes "given a fixed dataset, how big a model can we usefully train before we overfit?"

Note: Chinchilla gives you the compute-optimal frontier as a starting point, but e.g. LLaMA deliberately trained smaller models on far more tokens than compute-optimal, to get a better model at a given inference budget. And one would similarly overtrain for medical/clinical vision transformers where you can apply various augmentations (rotation, mirroring, tile crop offset, color map distortion, etc.)

Note: Training data quality (effort into data filtering and curation) can substitute for scale, Chinchilla assume internet-grade quality (recent, smaller models are the embodiment)

---

**Practical Decision Framework - Chinchilla**

---

- Research / capability exploration → large scale model, Chinchilla-compute-optimal
- High-volume production serving → train smaller model longer, then quantize
- Latency-critical (chatbot, autocomplete) → Minimize parameter count
- Data-scarce domain → Bigger model, less data (memorization regime)
- Data-rich, compute-poor → Filter for data quality, smaller model, train longer
- Quality-critical, low volume → Largest model you can afford to serve

---

**Encoder vs decoder vs encoder-decoder for tabular data - what would you pick and why?**

---

- Is your input tabular and output a label/score? → Encoder-only
- Is your task generative (synthesize rows)? → Decoder-only
- Is your task a translation (table → text, NL→SQL)? → Encoder-decoder

- Encoding means compressing structured input into a dense vector that captures meaning - a latent representation.
- Decoding means projecting out of that latent space into some target format.

---

**U-Net vs Autoencoder**

---

- Autoencoder is trained to reproduce its input at the output
- U-Net is trained to produce a different output than its input. The key difference is skip connections. The bottleneck in a U-Net carries global context (what is the overall scene), while skip connections carry local detail (where exactly are the edges).

---

**Attention mechanism**

---

- Words do not have fixed meanings, they shift depending on context. A token like "bank" should be represented differently next to "river" than next to "loan". Attention lets each token update its representation by looking at all other tokens in the sequence and weighting how relevant each one is for interpreting it. That is where the original n² issue comes from - a 2D matrix
- FlashAttention 1 was about reducing storage from n² to n in how the calculations were done over a sequence, and FlashAttention 2 introduced additional parallelism to speed up even more
- Medical/clinical vision models work with tiles on a grid, there is no sequence, attention assigns each tile a scalar relevance score and sums them into one slide vector - interpretable heatmap, but because tiles score in isolation, one loses the O(n²) contextual rewriting plaguing LLMs; applying a perceiver recoveres some of that inter-tile reasoning.

---

**Mixture of Experts (MoE)**

---

- A standard neural network ("dense" model) activates all its parameters for every input token. A Mixture of Experts model instead maintains a collection of specialized sub-networks (the "experts") and routes each token to only a subset of them
- Sparse MoE models decouple parameter count from compute, but you still pay the memory cost of all parameters
- The router is typically a small learned linear layer that sits before the expert layers
- Without intervention, the router collapses - a few popular experts get all traffic - solved e.g. with an auxiliary load-balancing loss that penalizes uneven routing
- Training efficiency - spending compute budget more efficiently because each expert sees a curated distribution of inputs
- Better inference throughput

---