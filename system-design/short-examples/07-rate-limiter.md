# Rate limiter

Prompt shape: "Limit each API client to N requests per time window across a fleet of servers."

## Requirements (ask, then assume)

- Limit by what: user, API key, IP, endpoint? (assume API key, per endpoint class)
- Limits and burst tolerance? (assume 100 requests/min, short bursts allowed)
- Exact or approximate? (assume small overage is acceptable)
- Single region or multi-region? (assume single region, discuss multi-region)
- What does a limited client get? (assume `429` with `Retry-After`)

## Numbers

1M active keys × ~50 B of state ≈ 50 MB: fits in one Redis node. At 50k requests/s that's 50k Redis operations/s: fine for a small Redis cluster, especially with local pre-checks.

## Algorithms (pick one, say why)

| Algorithm | Pros | Cons |
|---|---|---|
| Fixed window counter | Simplest, one counter | Double bursts at window edges |
| Sliding log | Exact | Memory grows with requests |
| Sliding window counter | Close to exact, cheap | Approximation |
| **Token bucket** | Allows bursts, smooth refill, cheap | Two values per key |

Choose **token bucket**: state is `(tokens, last_refill_ts)`; on each request refill by elapsed time × rate, cap at capacity, take one token or reject.

## Design

```
client → API gateway / middleware → rate limiter check → service
rate limiter state: Redis cluster sharded by key; atomic update via a Lua script (read, refill, decrement, write)
optional: local in-memory bucket per server for very hot keys, synced periodically
```

## Deep dives (the probes)

- **Race conditions:** the check-and-decrement must be atomic (Lua script or `INCR` with `EXPIRE` for fixed windows).
- **Redis down:** fail open (availability) for normal endpoints, fail closed for expensive or abuse-prone ones. Choose per endpoint and say so.
- **Multi-region:** per-region limits (limit / number of regions) or asynchronous sharing, accepting slight overage.
- **Client experience:** headers `X-RateLimit-Limit`, `X-RateLimit-Remaining`, `Retry-After`.
- **Different tiers:** limits come from config (per plan), cached in the gateway.

## Failure modes, observability, security

- Metrics: 429 rate per key and endpoint, Redis latency, hot keys.
- Abuse: also limit unauthenticated traffic by IP and at the edge (CDN/WAF).
