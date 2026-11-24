# Integration with an external system that has deadlines

Prompt shape: "An optimiser produces bids every minute. Design the market service that trades them on a day-ahead auction (bids by 12:00, results by 14:00) and an intraday market (continuous, immediate accept/reject), then hands schedules to the asset service."

## Requirements (ask, then assume)

- Who decides what to bid? (the optimiser; the market service only executes: ask once, move on)
- Market rules: gate closure, result time, partial acceptance? (assume as stated, partial fills possible)
- Volume: assets × 96 quarter-hours per day? (assume 500 assets → ~48k day-ahead slots/day)
- What happens if the exchange API is down near the deadline? (assume alert plus fallback)
- Latency needs for intraday? (assume seconds, not milliseconds)

## Data model

- `bids`: `bid_id, asset_id, delivery_period, market, quantity, direction, price, version` (latest version per key supersedes older ones)
- `orders`: `client_order_id, exchange_order_id, state (NEW|SUBMITTED|ACCEPTED|PARTIAL|REJECTED|CANCELLED), filled_qty, timestamps`
- `positions`: committed quantity per asset and delivery period, across both markets
- `outbox`: schedule events to publish to the asset service

## Flow

```text
optimiser → bids (versioned) → risk check (battery power/energy/state-of-charge limits, both markets combined)
  → day-ahead adapter: freeze at 11:30, aggregate, submit once, fetch results from 14:00 with retries
  → intraday adapter: submit, immediate accept/reject, cancel/replace when a newer bid version arrives
→ positions updated → schedule written to outbox in the same transaction → published to asset service
→ daily reconciliation against exchange trade reports
```

## Deep dives (the probes)

- **Idempotency:** `client_order_id` derived from `(bid_id, version)`. A retry after a timeout reuses it, so the exchange (or a query before resubmitting) prevents a double trade. Idempotency protects against retries and duplicates; security is authentication and signing, a different concern.
- **Never over-commit a battery:** the position check runs before every submission and sees both markets.
- **Outbox pattern:** order state and the schedule event commit together; a relay publishes, and the consumer is idempotent.
- **Reconciliation:** compare our orders and fills with the exchange's reports; mismatches open an alert, never a silent fix.

## Failure modes, observability, security

- Deadline alerts: day-ahead not submitted by T-30 min; results missing at 14:15; escalate to a human with a fallback (default bids or none).
- Metrics: rejection rate, submission latency, position vs schedule mismatches, reconciliation differences.
- Exchange credentials in a secrets manager; mTLS to the exchange; audit log of every order.

## Traps from past rounds

- Time went into narrowing the problem, and the design covered only queueing. Cover storage, the state machine and observability as well.
- Know your own take-home: be able to explain any part of the code, including what a coding agent wrote (e.g. cursor pagination: `WHERE id > :last ORDER BY id LIMIT n`).
