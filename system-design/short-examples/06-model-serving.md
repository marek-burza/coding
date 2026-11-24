# Model serving

Prompt shape: "Serve one or more ML models (vision or LLM) to product traffic with a latency target, and roll out new versions safely."

## Requirements (ask, then assume)

- Model type and size; GPU needed? (assume a vision model, fits one T4/L4 GPU)
- Traffic and latency SLO? (assume 200 requests/s peak, p99 < 150 ms)
- Online only, or also batch/offline jobs? (assume both; big jobs go async)
- How many models and versions at once? (assume a few, plus per-customer variants)
- On-prem or edge targets? (assume cloud now, CPU export later)

## Numbers (latency budget)

- Inference: 50 ms for a batch of 8 → ~160 requests/s per GPU. 200 requests/s peak → 2 GPUs busy; run 3-4 for headroom and failure.
- p99 budget: network 10 + batching wait ≤ 20 + inference 50 + pre/post-processing 15 ≈ 95 ms, inside 150 ms.

## Design

```text
model registry (versioned, immutable artifacts: weights + preprocessing + config)
  → CI builds a serving image → deploy (Kubernetes/KServe, or SageMaker)
client → gateway (auth, rate limit, routing by version) → inference service (Triton/TorchServe; vLLM for LLMs)
        dynamic batching, N model instances per GPU, autoscale on queue depth and GPU utilisation
large or slow requests → async job path (see 01-long-running-jobs)
```

- **Preprocessing packaged with the model**, so training and serving can't drift apart.
- **GPU efficiency:** dynamic batching, TensorRT compilation, FP16/INT8 quantisation, several model instances per GPU.

## Deep dives (the probes)

- **Safe rollout:** shadow mode first (new version gets a copy of traffic, results not returned), then a canary (5% of traffic) compared on latency, errors and prediction distribution, then ramp up. Rollback = route back.
- **Versioning:** every prediction logs the model version; clients can pin a version for reproducibility.
- **Cold start:** loading weights takes time; keep warm replicas, pre-pull images, load models on startup, not on first request.
- **Quality in production:** input drift and prediction drift monitoring; delayed ground truth joined back when it arrives.
- **Multi-model:** shared GPU pools with per-model quotas, or a multi-model server; low-traffic models scale to zero.

## Failure modes, observability, security

- Metrics: p50/p99 latency per version, GPU utilisation and memory, queue depth, error rate, drift.
- Overload: shed load with 429s or a cheaper fallback model rather than letting latency explode.
- Sampled request/response logging with privacy controls; model artifacts signed and access-controlled.
