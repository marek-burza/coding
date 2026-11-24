# System design outlines

One page per recurring problem type, prepared in advance so the thinking is already done before the interview.

| File | Problem type | Source |
| --- | --- | --- |
| [01-long-running-jobs.md](01-long-running-jobs.md) | Request, queue, worker, status, retries, dead workers | Asked in an interview |
| [02-catalogue-search.md](02-catalogue-search.md) | Prefix/fuzzy search, filters, wide analytical store | Asked in an interview |
| [03-feature-flags.md](03-feature-flags.md) | Hash-based rollout, local evaluation, config push | Asked in an interview (exact prompt) |
| [04-external-system-deadlines.md](04-external-system-deadlines.md) | Idempotent submission, state machine, reconciliation, deadline alerts | Asked in an interview (exact prompt) |
| [05-etl-ingestion.md](05-etl-ingestion.md) | Batch vs streaming, backfills, schema changes, data quality | Common in data and ML platform loops |
| [06-model-serving.md](06-model-serving.md) | Batching, GPU utilisation, versioning, canaries, latency budgets | Common in ML platform loops |
| [07-rate-limiter.md](07-rate-limiter.md) | Algorithms, shared counters, failure behaviour | Classic warm-up question |
| [08-financial-analytics-platform.md](08-financial-analytics-platform.md) | Search plus slow reports, end-to-end; combines 01 and 02 | Asked in an interview (exact prompt) |
| [09-mcp-gateway-and-agent-evaluation.md](09-mcp-gateway-and-agent-evaluation.md) | Single MCP endpoint over microservices; evaluating an agent workflow | Asked in an interview |
| [10-metrics-collection-platform.md](10-metrics-collection-platform.md) | Multi-site time-series ingestion, storage, site-loss tolerance | Asked in an interview (exact prompt) |
| [11-file-storage-and-sync.md](11-file-storage-and-sync.md) | Google Drive / Dropbox-like storage, sync, sharing, versions | Classic question |
| [12-object-storage.md](12-object-storage.md) | S3-like object store: metadata, erasure coding, durability | Classic question |

## The checklist (same skeleton every time)

Keep it on paper next to the screen.

1. **Requirements, 5 minutes max.** Ask at most 5 questions, then say "I'll assume X" and move on.
2. **Numbers, out loud.** Requests per second, data size, concurrency. One estimate usually settles the design.
3. **API and data model.** Endpoints, the main table or record, its states.
4. **Boxes and flow.** Draw it. Narrate the happy path once.
5. **Deep dive where the interviewer points.** Treat "can we do better than X?" as "X is wrong": drop it.
6. **Failure modes, observability, security.** What breaks, how you notice, who may do what.

## Delivery rules

- Answer first, nuance second.
- When a question has two halves, name both before answering.
- Ask for time explicitly ("give me 30 seconds") rather than talking to buy it.
- When the interviewer says "don't focus on X", drop X.
