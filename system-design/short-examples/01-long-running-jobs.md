# Long-running jobs (async report generation)

Prompt shape: "Users request a report that takes ~30 minutes. They check status and download it later."

## Requirements (ask, then assume)

- How long does a job take, and how big is the result? (assume 30 min, a few MB PDF/CSV)
- Done/failed only, or progress too? (assume done/failed, progress optional)
- Poll, or notify (email, WebSocket)? (assume poll, notify as an extension)
- Same request twice: reuse the result? (assume yes, dedup by parameters)
- Volume and peak? (assume 10k jobs/day, 10x peak)

## Numbers

10k/day ≈ 0.12 jobs/s. Concurrency = rate × duration = 0.12 × 1,800 s ≈ 200 jobs running on average, ~2,000 at a 10x peak. So: queue plus autoscaled workers, and a backlog at peak is acceptable if the SLA allows it.

## API and data model

- `POST /reports` `{company_id, format}` with an `Idempotency-Key` header → `202 {job_id}`
- `GET /reports/{job_id}` → `{status, progress?, download_url?}`
- `jobs` table: `id, user_id, params_hash, status (PENDING|RUNNING|SUCCEEDED|FAILED), attempts, created_at, started_at, heartbeat_at, finished_at, result_uri, error`

## Flow

```text
client → API → jobs table (PENDING) + enqueue (outbox) → SQS → worker
worker: status RUNNING → heartbeat → write result to S3 (key = job_id) → status SUCCEEDED
client polls GET → pre-signed S3 URL when SUCCEEDED
```

## Deep dives (the probes)

- **Worker dies mid-job (process killed, OOM, no chance to write an error):**
  - SQS **visibility timeout**: an unacknowledged message reappears and another worker retries.
  - For jobs longer than the timeout, the worker extends visibility (`ChangeMessageVisibility`) as a heartbeat.
  - After `maxReceiveCount` attempts the message goes to a **dead-letter queue**; a DLQ consumer marks the job FAILED.
  - A sweeper marks RUNNING jobs with a stale `heartbeat_at` as failed or re-queues them.
- **Exactly once?** No: at-least-once delivery plus an idempotent worker (deterministic result key, status updates guarded by expected state).
- **Duplicate requests:** `params_hash` lookup returns an existing or finished job.
- **Status lives in the database, not in "does the S3 object exist"**: that gives failures, attempts and timestamps.
- **Multi-step jobs** (report B depends on A and C): a workflow engine (Step Functions, Temporal, Prefect), not the API or the frontend.
- **Polling cost:** backoff and ETags; for push, a notification service fed by a "job finished" event.

## Failure modes, observability, security

- Metrics: queue depth, age of oldest message, job duration p95, failure rate, DLQ size.
- Autoscale workers on queue depth; per-tenant quotas so one user can't flood the queue.
- Authorisation on `job_id` (owner only); download URLs expire.

## Traps from past rounds

- Don't invent a timeout scheme (time encoded in IDs); use the queue's redelivery and DLQ first: that is the "simpler" answer.
- Name the jobs table early; it answers the status and failure questions.
