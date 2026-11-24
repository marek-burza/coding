# Data ingestion and ETL

Prompt shape: "Ingest data from many sources (devices, partner APIs, files) and make it reliable for analytics and ML training."

## Requirements (ask, then assume)

- Sources and formats; push or pull? (assume telemetry events, partner files, a few APIs)
- Volume and freshness SLA? (assume 0.5 TB/day; dashboards within 15 min, training data daily)
- Consumers: BI, ML training, product features? (assume all three)
- Who owns the schema at the source? (assume the source teams, changes without notice)
- PII or regulatory constraints? (assume some PII: mask it)

## Numbers

0.5 TB/day ≈ 6 MB/s on average; at ~1 KB per event that's ~6k events/s, with daily peaks of ×3-5. Partitioned object storage handles this easily; the hard parts are correctness and change, not throughput.

## Design

```
sources → landing zone (raw, immutable, partitioned by ingest date)
        → validate and parse → bronze/raw → silver/clean (deduplicated, typed) → gold/curated (business tables, features)
streaming path (only if needed): Kafka/Kinesis → Flink or Spark Structured Streaming → same tables
storage: Parquet with a table format (Iceberg/Delta) on S3; orchestration with Airflow/Prefect/Dagster
```

- **Batch vs streaming:** batch by default (cheaper, simpler, easy to rerun); streaming only for a real minutes-level freshness need. Same transformation code for both where possible.
- **Idempotent loads:** overwrite a partition, or `MERGE` on a dedup key. Rerunning a day gives the same result.

## Deep dives (the probes)

- **Backfills:** the same job, parameterised by date range, rerun from the immutable raw layer; separate queue or priority so backfills don't starve daily runs; table formats give time travel to compare.
- **Schema changes:** schema registry or data contracts; additive changes pass automatically, breaking changes create a new version; schema-on-read in the raw layer; unparseable records go to a quarantine table, never silently dropped.
- **Late and out-of-order data:** partition by event time, set a watermark, reprocess a trailing window (e.g. the last 3 days).
- **Data quality:** checks on row counts, null rates, value ranges, uniqueness, freshness (dbt tests, Great Expectations). A failed critical check blocks publishing to gold; others warn.
- **Lineage:** which job and which input produced each table version, so a bad source can be traced and reprocessed.

## Failure modes, observability, security

- Metrics: freshness lag per table, volume vs expected (anomaly), failed tasks, quarantine counts.
- Cost: compact small files; lifecycle rules on raw data.
- PII: mask or tokenise at silver; access controlled per layer; retention rules.
