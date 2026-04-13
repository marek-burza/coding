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
- `torch.compile` is a replacement for `torch.jit`; it fuses ops, eliminates overhead, can use 3rd party backends
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
- `cudagraphs` - captures and replays CUDA graphs to reduce kernel launch overhead; doesn't touch the kernels at all. It records the exact sequence of existing GPU operations once, then replays that recording without CPU involvement. It changes how the GPU is told to run - eliminating the per-kernel launch overhead from the CPU. The kernels themselves are identical to eager mode; use if `inductor` fails to compile your model

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
