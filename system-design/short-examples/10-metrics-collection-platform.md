# Metrics collection platform across data-centre sites

Prompt shape: "20 sites (data centres), 250 Linux servers each, 5,000 in total. Collect their metrics and store them so they can be queried. Estimate the resources yourself. The platform must survive losing a whole site. Dashboards and alerting are out of scope."

## Requirements (ask, then assume)

- Which metrics and how often? (assume ~1,000 series per server, 15 s interval)
- Retention? (assume 30 days raw, 1 year downsampled)
- Query latency and freshness? (assume seconds-old data, interactive queries)
- Network between sites: reliable? (assume WAN links can partition)
- Growth? (assume sites and servers double within two years)

## Numbers (say them out loud)

- Active series: 5,000 × 1,000 = 5M.
- Ingest: 5M / 15 s ≈ 330k samples/s.
- Storage: ~1.5 bytes/sample compressed (delta-of-delta, XOR) → ~500 KB/s ≈ 43 GB/day ≈ 1.3 TB for 30 days, ×2-3 for replication.
- Conclusion: one large machine could almost hold it, but site-loss tolerance and growth call for a small replicated cluster across sites.

## Boxes

```text
each server: agent (node_exporter / OpenTelemetry collector)
each site: local collector (Prometheus agent / vmagent / OTel collector)
           scrapes local servers, keeps a disk buffer (WAL)
           → remote write over WAN →
storage cluster spread over 3+ sites (VictoriaMetrics cluster / Mimir / Thanos)
           replication factor 2-3, replicas placed on different sites
query layer (in several sites) → deduplicates replicas → users and tools
long-term: downsampled blocks in object storage
```

- **Pull inside a site, push across the WAN:** scraping needs discovery and low latency (local inventory or service discovery); remote write tolerates WAN latency and buffers when the link drops.

## Deep dives (the probes)

- **Losing a site:** metrics of that site stop (unavoidable); everything else keeps working because every stored series has replicas on other sites and query nodes exist in several sites. Placement must be site-aware, not just node-aware.
- **WAN partition:** site collectors buffer to disk and backfill when the link returns; size the buffer for hours (≈ 250 servers × 1,000 series × 4 samples/min × 2 bytes ≈ 2 MB/min per site).
- **Cardinality explosion:** label limits per metric, rejecting or dropping high-cardinality labels at the collector.
- **Scaling:** shard storage by series hash; add nodes and rebalance; separate ingestion and query nodes.
- **Retention and cost:** raw for 30 days, 5-minute rollups for a year in object storage.

## Failure modes, observability, security

- Meta-monitoring: the platform monitors itself from at least two sites.
- Back-pressure: collectors drop least important metrics first when storage is slow.
- TLS and authentication on remote write; per-team tenants and quotas.

## Traps

- Do the estimate before choosing tools; it shows the hard part is replication and partitions, not raw volume.
