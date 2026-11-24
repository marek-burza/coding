# Object storage (S3-like)

Prompt shape: "Design an object store: buckets, keys, PUT/GET/DELETE/LIST, very high durability."

## Requirements (ask, then assume)

- Object sizes? (assume bytes to 5 TB; multipart for large ones)
- Durability and availability targets? (assume 11 nines durability, 99.99% availability)
- Consistency? (assume strong read-after-write, including LIST)
- Scale? (assume 100 PB, 1M requests/s, average object 1 MB)
- Versioning, lifecycle rules, multi-region? (assume versioning and lifecycle yes; multi-region as an extension)

## Numbers

- 100 PB with 3-way replication = 300 PB raw; with erasure coding 10+4 = 140 PB raw (1.4x overhead).
- 1M requests/s mostly small GETs → metadata lookups dominate; data throughput depends on object size.
- Metadata: 100 PB / 1 MB ≈ 100 billion objects × ~500 B ≈ 50 TB of metadata, sharded.

## Boxes

```
client (signed requests) → front-end API servers (stateless: auth, request routing)
  → metadata service: (bucket, key) → version, size, checksum, chunk placement
     range-partitioned by (bucket, key), each partition replicated with consensus (Raft/Paxos)
  → storage nodes: chunks (extents), erasure-coded across 3 availability zones
  → placement service: picks nodes, tracks capacity and health
background: scrubber (checksums), repairer, garbage collector, lifecycle worker
```

## Deep dives (the probes)

- **PUT:** stream data to storage nodes, compute checksums, wait for a durable quorum of fragments, then commit the metadata record. The metadata commit is the moment the object becomes visible, which gives strong read-after-write.
- **GET:** metadata lookup, fetch enough fragments in parallel, reconstruct if some are missing; verify the checksum.
- **Large objects:** multipart upload: parts uploaded in parallel and retried independently, then a complete call commits the list of parts.
- **LIST by prefix:** range partitioning keeps keys sorted, so a prefix is a range scan; hot prefixes are split into more partitions automatically.
- **Durability:** erasure coding across zones, background scrubbing to catch bit rot, fast repair when a disk or node dies (rebuild from remaining fragments); the durability math is about repair time versus failure rate.
- **Delete and versioning:** a delete writes a tombstone (or a new version marker); a garbage collector reclaims chunks later.
- **Lifecycle:** background workers move objects to colder tiers or expire them, by rules on prefix and age.

## Failure modes, observability, security

- Losing a zone: data stays readable from the other zones' fragments; writes continue with placement across the remaining zones.
- Hot keys: cache popular objects at the front end or a CDN.
- Security: request signing, IAM and bucket policies, encryption at rest (managed or customer keys), pre-signed URLs for temporary access.
- Metrics: request latency per operation, error rates, repair backlog, capacity per zone.
