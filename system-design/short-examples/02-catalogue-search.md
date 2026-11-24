# Search over a large catalogue

Prompt shape: "100M companies, 10 years of financials each, hundreds to thousands of columns. Users search by name (partial) and filter, e.g. Germany AND revenue > €100M."

## Requirements (ask, then assume)

- Query types: name prefix and typos? filters on which fields? (assume prefix + fuzzy name, filters on country, industry, size, latest financials)
- Latency target? (assume p99 < 300 ms for search)
- How often does data change? (assume daily batch updates)
- Ranking: exact name first? (assume exact > prefix > fuzzy, then company size)
- Do users query the full history interactively, or only in reports? (assume reports)

## Numbers

- Search documents: 100M × ~1 KB (name, aliases, country, industry, ~20 latest metrics) ≈ 100 GB index → a sharded search cluster (e.g. 10 shards plus replicas).
- Financials: 100M × 10 years = 1B rows × ~1,000 mostly numeric, sparse columns. Columnar compression makes this low single-digit TB.

## Design: split search from storage

```text
ingestion → columnar store (Parquet/Iceberg on S3, queried by Trino/Athena, or ClickHouse)
          → "latest metrics" job → search index (OpenSearch/Elasticsearch)
          → company details (Postgres, keyed by company_id)
API: search → index returns ids → details from Postgres; reports → columnar store
```

- **Name search:** edge n-grams for prefix, fuzzy matching or trigrams for typos; normalise legal suffixes (GmbH, AG, Ltd) and keep aliases.
- **Filters:** denormalise only the filterable fields into the index (keyword and numeric fields); range filters are cheap there.
- **Full history:** stays in the columnar store; it is wide and analytical, so it doesn't belong in a row database or the search index.
- **Smaller scale alternative:** Postgres with `pg_trgm` for names plus a B-tree index on each filter column.

## Deep dives (the probes)

- **Sharding:** by `company_id` hash; the search engine fans out to all shards and merges results. **Not by name hash**: that breaks prefix and partial search.
- **Pagination:** `search_after` or a cursor, not deep offsets.
- **Reindexing:** build a new index, then switch an alias (zero downtime).
- **Freshness:** a daily batch recomputes latest metrics and bulk-updates the index; use CDC if near-real-time is needed.
- **Hot queries:** a cache for popular prefixes; rate-limit expensive filter combinations.

## Failure modes, observability, security

- Metrics: query p99, index lag (data timestamp vs index timestamp), zero-result rate.
- Search cluster loss → serve degraded name-only search from Postgres trigrams.
- Access control on which companies or fields a customer may see, applied as filters.

## Traps from past rounds

- Don't put all columns into the search index or one relational table.
- Answer the partial-search question with the index type (n-grams/trigrams) straight away.
