# Feature flags at scale

Prompt shape: "Design dynamic feature flags for a very large company: roll out by country and by percentage of users, changes live within ~2 minutes."

## Requirements (ask, then assume)

- Who evaluates flags: backend servers, clients, both? (assume backend servers)
- Sticky assignment: does a user stay in the rollout as it grows? (assume yes)
- Targeting: country, percentage, user lists? (assume country + percentage + allow/deny lists)
- Propagation SLA? (assume < 2 minutes)
- Boolean only, or variants? (assume variants possible)

## Numbers (say them out loud)

10k services × 100 flags = 1M flags × ~200 B ≈ 200 MB in total, but only ~20 KB per service. 1M servers. **Every server can hold its own service's flags in memory**: that single estimate decides the design.

## Design: control plane and data plane

```
admins → admin API → config store (versioned, single writer, audited, validated)
                   → distribution: regional relays/caches → servers (subscribe to their service)
each server: client library, in-memory snapshot, evaluates locally (no network call per flag)
```

- **Evaluation:** `bucket = hash(flag_salt + user_id) mod 10000`; in the rollout if `bucket < pct × 100` and the request's country matches. Country comes from the request context.
- **Why hashing:** nothing stored per user, no batch job when users or flags are created, sticky as the percentage grows (10% users stay in at 20%), independent per flag via the salt.
- **Same user, same answer across services:** same salt and same hash function in every client library.

## Deep dives (the probes)

- **"Can we do better than a stored user-to-flag mapping?"** Yes: deterministic hashing, above.
- **"Calling a service per flag adds latency":** evaluate locally; the remote service only distributes config.
- **Pushing to 1M servers in 2 minutes:** fan-out tree via regional relays, streaming or long-poll subscriptions, deltas keyed by config version, jitter on reconnect.
- **Config service unreachable:** keep the last-known-good snapshot (also on disk), and the default value in code as the final fallback.
- **Rollback:** set the percentage to 0 or flip a kill switch; kill switches take priority over normal updates.

## Failure modes, observability, security

- A bad config push is the biggest risk: validate it, and roll out the config change itself in stages (canary region first).
- Metrics: config version per server (staleness), evaluation counts, exposure logs (sampled) for experiments.
- Ownership and audit: who changed which flag, when; approval for high-risk flags; cleanup of stale flags.

## Traps from past rounds

- Requirements took three quarters of the interview: cap them at 5 minutes, then estimate.
- Stay server-side; don't drift into UI, DNS or WebSockets to the frontend.
