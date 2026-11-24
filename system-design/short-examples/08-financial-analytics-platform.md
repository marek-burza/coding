# Financial analytics platform (search + slow reports)

Prompt shape: "Users register and log in, search companies, and request a PDF or CSV financial report (revenue, EBITDA, ... for the last 10 years). A report takes ~30 minutes; users check status and download later." Follow-ups: 100M companies with 500-2,000 columns, partial-name search with filters, dead workers, splitting the API, dependent reports.

## Requirements (ask, then assume)

- Search by name only, or also by website and filters? (assume partial name + filters like country and revenue)
- Report formats and size? (assume PDF or CSV, a few MB)
- Notify when done, or poll? (assume poll; push as an extension)
- Tenancy: do customers see different data? (assume same data, per-user reports)
- Volume? (assume 10k reports/day, interactive search all day)

## Numbers

100M companies × 10 years = 1B financial rows × 500-2,000 sparse numeric columns → a columnar store, low single-digit TB compressed. Search index ≈ 100M × 1 KB ≈ 100 GB. Reports: ~0.1/s average, ~200 running at once (rate × 30 min).

## API

- Auth: a managed identity provider issues tokens; the gateway validates them (no auth logic in the services)
- `GET /companies?q=merc&country=DE&min_revenue=100000000` → paged results
- `POST /reports` `{company_id, format}` → `202 {job_id}`; `GET /reports/{job_id}` → status and download URL

## Boxes

```
CDN (static frontend) → load balancer/gateway (TLS, token validation, rate limits)
  → search service → search index (n-grams + filter fields) → details from Postgres
  → report service → jobs table + queue → report workers → read columnar store → write file to S3
client polls report service → pre-signed S3 URL
```

## Deep dives (the probes)

- **Data layout:** filterable "latest" fields denormalised into the search index; full history in Parquet/Iceberg (Trino/Athena) or ClickHouse; never shard by name hash (it breaks partial search). See `02-catalogue-search.md`.
- **How does the user know it's done?** Status in the jobs table (PENDING/RUNNING/SUCCEEDED/FAILED). Polling with backoff is fine; for push, a separate notification service consumes "job finished" events, so the API stays stateless.
- **Worker died without writing an error:** queue visibility timeout redelivers, `maxReceiveCount` → dead-letter queue → mark FAILED; heartbeat column plus sweeper for long jobs. See `01-long-running-jobs.md`.
- **When does the API have too many responsibilities?** Split when parts differ in scaling, state or latency: search (read-heavy, low latency), report management (writes, async), auth (managed). Long-running or stateful work never runs in request handlers.
- **Report B needs workers A and C first:** a workflow engine (Step Functions, Temporal, Airflow/Prefect) owns the dependency graph and state, with retries and timeouts per step. Not the frontend (exposes internals) and not the API (state, crashes).

## Failure modes, observability, security

- Cache identical report requests (same company, format, data version).
- Per-user quotas on report requests; authorisation on `job_id`; download URLs expire.
- Metrics: search p99, queue depth and oldest message age, report duration, failure and DLQ counts.

## Traps

- Draw arrows in the direction requests flow, and say "the gateway validates tokens issued by the identity provider" in one sentence.
- Have the dead-worker answer ready; reach for the queue's built-in redelivery before inventing timers.
