# Bank API

Internal HTTP API for bank employees: customers, accounts, balances, transfers between accounts, and per-account transfer history. Python, FastAPI, SQLAlchemy, Alembic, PostgreSQL.

To make the assignment also interesting to me as a learning experience, I decided to recast it as a modernization of my earlier attempt (from 2 years ago). Coincidentally, it brings some realism, since in practice one does not frequently face a greenfield task, and often there is some preexisting code to work with.
One additional benefit is that the old attempt already seeds some of the documentation and contained tests, which provides useful counterbalance to the coding agent from the very beginning of the implementation.
I also decided to combine it with scaffolding from a newer project of mine which did see an AWS deployment, and with it, I wanted carry over another aspects such as: use of `uv` packaging, newer tool for code formatting and linting, dependency version-pinning discipline, a tool to keep dependencies trim, cognitive code complexity cap, bare minimum of CI, etc.

The assignment took roughly 4 hours including preparation, planning, implementation, implementation review, side-channel documentation consultation, and clean up. Notably, anticipating Claude's overestimation of implementation time, I authorized extension (as you may notice in the session logs) - in all, the AI-assisted implementation took about 1.5h rather than what it had estimated.
As a side remark, I pre-seeded `CLAUDE.md` with some basic instructions - given that it was one of my first uses of the version 5 family of Anthropic's models - I e.g. included reference to ["The new rules of context engineering for Claude 5 generation models"](https://claude.com/blog/the-new-rules-of-context-engineering-for-claude-5-generation-models)
and you may spot some of this "bleeding" into the sessions.

The core of the implementation is a ledger. Balances are not stored anywhere; they are derived by summing an append-only, double-entry log, so any balance the API reports can be reproduced by replaying that log. `tests/test_replay.py` asserts exactly that: every balance the API reports is recomputed independently from the entry log and the two must agree.

## Running the tests

One command, from a clean clone. It needs only Docker and [`uv`](https://docs.astral.sh/uv/).

```shell
./automation/test.sh
```

It starts PostgreSQL with a single `docker run`, waits for `pg_isready`, applies the migrations, runs the suite and prints coverage. The container is named `banking-db` and is reused if it is already running, so repeated runs do not restart it; its storage is a `tmpfs` and it is started with `--rm`, so `docker stop banking-db` is a full reset.
CI runs the same script, so there is exactly one definition of how the suite runs and it cannot drift from what you execute locally. If `BANKING_DATABASE_URL` is already set, the script uses that database and skips starting a container.

## Running the service

```shell
docker run --detach --rm --name banking-db \
  --env POSTGRES_USER=banking --env POSTGRES_PASSWORD=banking --env POSTGRES_DB=banking \
  --publish 5432:5432 --tmpfs /var/lib/postgresql postgres:18-alpine
until docker exec banking-db pg_isready --quiet --username banking; do sleep 0.1; done

export BANKING_DATABASE_URL="postgresql+psycopg://banking:banking@localhost:5432/banking"
uv sync
uv run alembic upgrade head
uv run uvicorn banking.main:app --port 8000
```

Then open [http://127.0.0.1:8000/](http://127.0.0.1:8000/), which redirects to the generated OpenAPI documentation. The migration seeds the four customers from the assignment, so the API is usable immediately.

The container image is the deployment artifact and runs the same way locally:

```shell
docker build -t banking .
docker run --rm -it --network host \
  -e BANKING_DATABASE_URL="postgresql+psycopg://banking:banking@localhost:5432/banking" banking
```

### The other checks

```shell
uv run ruff check --select I,E,B,SIM src   # imports and basic linting
uv run ruff format --check --diff          # formatting
uv run deptry .                            # dependencies trim to the project's needs
uv run mypy src                            # types (still mypy; ty lacks the rule coverage)
uv run bandit -r src                       # basic security linting
uv run complexipy src --max-complexity-allowed 25
uv run alembic check                       # migrations still match the models
```

## API

Documented briefly here as well as in `OpenAPI` docs. `GET /health` is a liveness probe rather than part of the domain surface, and is left out of the table for that reason.

| Method | Path                       | Purpose                                                                                                |
| ------ | -------------------------- | ------------------------------------------------------------------------------------------------------ |
| `POST` | `/customers`               | Create a customer. Returns `201` and a `Location`.                                                     |
| `GET`  | `/customers`               | List the customers.                                                                                    |
| `POST` | `/accounts`                | Open an account for a customer with an initial deposit and an optional overdraft limit. Returns `201`. |
| `GET`  | `/accounts/{id}/balance`   | Balance in Euro cents, recomputed from the entry log.                                                  |
| `GET`  | `/accounts/{id}/transfers` | A page of transfer history for the account, newest first. Takes `limit` (1-1000) and `cursor`.         |
| `POST` | `/transfers`               | Move funds between two accounts, idempotently. Returns `201`.                                          |

A working example, demonstrating the idempotency contract:

```shell
BASE=http://127.0.0.1:8000
SOURCE=$(curl -s -X POST $BASE/accounts -H 'Content-Type: application/json' \
  -d '{"customer_id": 1, "initial_deposit_cents": 10000}' | jq -r .id)
TARGET=$(curl -s -X POST $BASE/accounts -H 'Content-Type: application/json' \
  -d '{"customer_id": 2, "initial_deposit_cents": 1, "overdraft_limit_cents": 5000}' | jq -r .id)
KEY=$(python3 -c 'import uuid; print(uuid.uuid4())')

transfer() {  # amount_cents, idempotency_key
  curl -s -X POST $BASE/transfers -H 'Content-Type: application/json' -d "{
    \"source_account_id\": \"$SOURCE\", \"destination_account_id\": \"$TARGET\",
    \"amount_cents\": $1, \"idempotency_key\": \"$2\"}"
}

transfer 2500 $KEY   # 201, the transfer
transfer 2500 $KEY   # 201, the same transfer id: a replay, not a second movement
transfer 3000 $KEY   # 409 idempotency_conflict, nothing written

curl -s $BASE/accounts/$SOURCE/balance      # 7500, so the money moved exactly once
curl -s "$BASE/accounts/$SOURCE/transfers?limit=2"
```

To page through the whole history, omit `cursor` on the first request, then pass the `next_cursor` from each response back as `cursor` on the next, and stop when it comes back `null`:

```shell
CURSOR=$(curl -s "$BASE/accounts/$SOURCE/transfers?limit=2" | jq -r .next_cursor)
curl -s "$BASE/accounts/$SOURCE/transfers?limit=2&cursor=$CURSOR"
```

### Errors

Every failure returns the same body shape, `{"code": ..., "detail": ...}`, with a machine-readable `code`. Domain failures are typed exceptions mapped to status codes in one table (`src/banking/api/errors.py`); they never surface as a `500`, and a validation failure never surfaces as a bare framework dump.

| Code                   | Status | Meaning                                                |
| ---------------------- | ------ | ------------------------------------------------------ |
| `unknown_account`      | `404`  | No account with that identifier.                       |
| `unknown_customer`     | `404`  | No customer with that identifier.                      |
| `insufficient_funds`   | `422`  | The funding account would go past its overdraft limit. |
| `self_transfer`        | `422`  | Source and destination are the same account.           |
| `idempotency_conflict` | `409`  | The key was already used with a different payload.     |
| `invalid_request`      | `422`  | The request failed schema validation.                  |

### The idempotency contract, for a client

Generate one key per **intended** transfer, not one per HTTP attempt, and keep it across retries.

- **Same key, same payload** returns `201` and the original transfer, unchanged. The money moves once no matter how many times you send it. This is what makes a retry after a timeout safe: you do not know whether the first attempt committed, and you do not need to.
- **Same key, different payload** returns `409` and writes nothing. A key identifies one intended movement of money, so reusing it for a different one is a client bug rather than a second transfer.
- **A new key** is a new transfer, even if every other field is identical. Two genuine payments of the same amount between the same accounts are a normal thing to want.

Uniqueness is enforced by a unique constraint in the database rather than by an application-level check. The service inserts the key first and handles the integrity error; a `SELECT` before the `INSERT` has a window in which two concurrent requests carrying the same key both pass the same check.
The replayed response is rebuilt from the two ledger entries that share the transfer ID, not from a stored response body, so a replay is provably the same transfer rather than a remembered payload.

## Design

### Money

Money is never a float. Everything is stored and computed in integer Euro cents, and amounts cross the API as integers too, so there is no decimal parsing and no rounding mode anywhere in the system. Floating point values as encoded by IEEE 754 do not follow decimal quantization and would accumulate rounding errors, which is not acceptable for balances.
Major units are a presentation concern for whichever frontend consumes this, and are never a second stored column.

Amounts in requests are positive integers; direction is expressed by the ledger rather than by a sign on a request field. Every monetary field says so in its name: `amount_cents` `initial_deposit_cents`, `overdraft_limit_cents`, `balance_cents`. There is no field called `amount`.

A single currency is assumed, so there is no currency column. Amounts are capped at 1_000_000_000_000 cents (ten billion Euro), which keeps an account's summed entries comfortably inside `BIGINT`.

### The ledger

The `entries` table is append-only and double-entry. Nothing updates or deletes an entry.

- `amount_cents` on an entry is **signed**, and an account's balance is the sum of its entries. A transfer writes two entries that sum to zero, which is what makes debits equal credits. A deposit writes a single positive entry.
- A `TRANSFER` is always balanced, and is the only operation that moves value between accounts.
- A `DEPOSIT` is how funds enter the system: single-sided, exempt from the balance check, and with no counter-entry. Account creation writes exactly one. No other entry type creates value.
- **Exactly one function writes entries** (`src/banking/domain/ledger.py`), so there is no second code path that could insert an entry and skip the overdraft check.

Balances are derived on every read and never cached or snapshotted, which keeps the entry log unambiguously the single source of truth and makes the replay property trivially true. The cost, and what closing it would take, is in **Production** below.

Every account carries a non-negative `overdraft_limit_cents`, defaulting to `0`. A transfer is rejected unless the funding account's resulting balance is at least the negative of that limit, and the boundary is inclusive: landing exactly on the limit succeeds. The limit is settable at creation and never afterwards, because a mutable limit needs an authorisation story this build does not have.

There is no `UPDATE` and no `DELETE` in this API. That is not incomplete CRUD; it is what a ledger is. Correcting a mistake means writing a compensating entry, so the history of what was believed, and when, survives.

### The schema

Four tables and one enum type, created only by migrations. **There is no balance column.**

| Table | Primary key | Columns | Constraints and indices |
| --- | --- | --- | --- |
| `customers` | integer, server-generated | `name` | indexed `name`; the four assignment customers are seeded by the initial revision |
| `accounts` | UUID | `customer_id` → `customers.id`, `overdraft_limit_cents` | `CHECK overdraft_limit_cents >= 0`; `customer_id` indexed |
| `transfers` | UUID | `idempotency_key`, `request_hash`, `created_at` | **unique** on `idempotency_key`: that constraint *is* the idempotency mechanism |
| `entries` | bigint, monotonic sequence | `account_id` → `accounts.id`, `transfer_id` → `transfers.id` (nullable), `type`, `amount_cents`, `created_at` | `CHECK amount_cents <> 0`, `CHECK` bounded to ±10^12; both foreign keys indexed |

The `entry_type` enum is `DEPOSIT` or `TRANSFER`. Two details in that table are load-bearing rather than incidental:

- **`entries.id` is a bigint sequence, not a UUID**, unlike accounts and transfers. It is the single monotonic key that history orders and cursors over, so a UUID there would break pagination. This is the one place the identifier type is a requirement rather than a preference.
- **`entries.transfer_id` is nullable, and that nullability is the deposit-versus-transfer distinction at storage level.** A `TRANSFER` writes two rows sharing a `transfer_id` that sum to zero; a `DEPOSIT` writes one row with `transfer_id` null.

Constraint and index names come from a naming convention declared on the metadata (`src/banking/models.py`), which is what lets the idempotency path recognise `uq_transfers_idempotency_key` by name when PostgreSQL raises the integrity error.

### Concurrency and transactions

Concurrent transfers touching the same accounts are assumed, including A to B and B to A at the same moment.

- Isolation is PostgreSQL's default **`READ COMMITTED`**, with correctness carried by explicit row locks rather than by the isolation level. `READ COMMITTED` plus `SELECT ... FOR UPDATE` is sufficient here because the only cross-row invariant is the funding account's own balance, and that row is held from the read until the commit.
- `SERIALIZABLE` would also be correct, and would let the locks go, but it costs a retry loop on serialization failures and the reasoning that goes with it: every write path becomes retryable, which means every write path must be idempotent or safely repeatable, and the retry budget becomes a tuning parameter. That did not fit the timebox.
- The balance is never read, decided on in Python, and then written. The account rows are locked `FOR UPDATE` first, and the lock is held until the commit.
- Accounts are locked in **ascending ID order**, so that opposing transfers cannot each hold the row the other needs.
- Each request gets exactly one database transaction. The handler owns the boundary and commits where a failure is still convertible to a typed domain error and a response, rather than after the handler has returned. Handlers stay thin and delegate to the domain layer, and no network or external call happens while the transaction is open.

### Identifiers

Customer IDs are server-generated integers; account and transfer IDs are UUIDs. The mixed types are deliberate: customers are a small fixed reference set that the assignment enumerates and the migration seeds, whereas accounts are created by clients at runtime. The real trade-offs for a UUID primary key are index locality and storage size rather than collision probability.

### Transfer history

History is ordered by a single monotonic integer key, the entry ID, rather than by timestamp. That makes the ordering total and the tie-breaking question disappear: entries sharing a timestamp still have distinct IDs.
It is rendered from the queried account's perspective, so the same transfer appears on the two accounts it touches with opposite direction and opposite sign, with the counterparty account named.

It is paginated with a cursor over that entry ID, never an offset. The response carries a `next_cursor`, which is null on the last page so a client stops without issuing a request that comes back empty.
`limit` bounds one response (1 to 1000, 100 by default) and is validated in the request schema rather than clamped in the handler; it is not a limit on how much history is reachable, because the cursor walks past it.

An offset would have been cheaper to write and wrong in a way that only shows up under load. IDs are handed out monotonically, so an entry committed while a client is midway through paging always sorts above the cursor it already holds: it cannot shift a page boundary, cannot push an entry across one so it is never returned, and cannot cause one to be returned twice.
`OFFSET` has none of those properties on a table that is being appended to, which is every ledger. `tests/test_accounts.py` asserts exactly that case.

### Validation

Validation happens at the edge, in Pydantic schema objects, so handlers stay thin and the domain layer receives already-valid input. Amounts are strict integers, so a JSON string is rejected rather than coerced.

Customer names are validated and rejected rather than sanitized. A name is Unicode NFC normalised and stored exactly as sent; a name that cannot be stored losslessly is a `422`. Silently rewriting `O'Brien` into `O Brien` stores something the caller never sent, which is worse than refusing it.

The database refuses invalid state independently of the application: `NOT NULL` on every column that matters, foreign keys, a non-negative overdraft check, a non-zero and bounded amount check, and the unique index on the idempotency key.

### REST

The API follows REST conventions and the Richardson Maturity Model to Level 2: resources map to URIs, and HTTP verbs carry the operation, with `POST` for creates returning `201` and a `Location`.
Level 3 hypermedia is deliberately absent, not omitted for brevity: the consumers here are first-party frontends that ship version-coupled with this service, so discoverability buys nothing the generated OpenAPI document does not already provide, and it would add a link-rendering concern to every response.

### Frameworks

`FastAPI` and `uvicorn`, chosen for maturity and for the simplicity of the resulting code (`Flask`, `Django` and `aiohttp` are the alternatives I have used before, and `uWSGI` on the server-side).
The decisive property for a timeboxed assignment is a single source of truth for both the API implementation and its specification: annotating the implementation lets the framework derive the OpenAPI document, which can then drive frontend code generation. GraphQL is a reasonable alternative for a multi-frontend consumer, and was not chosen because the surface here is small and resource-shaped.

Sync SQLAlchemy throughout. Async plus row locks plus concurrency tests is the wrong bet inside this timebox.

### Persistence

A relational database, for the strong consistency a bank needs and for transactions that can carry the balance invariant. PostgreSQL specifically, because the correctness story rests on `SELECT ... FOR UPDATE` semantics.

Alembic migrations are versioned from the first commit and are the only way the schema is created, including for tests and for the seeded customers. There is no `create_all` anywhere.
A single initial revision creates everything, and is amended in place rather than corrected by a second revision for as long as no database that cannot be recreated has run it, so the schema a reviewer reads is the schema as designed.

### Packaging

A proper Python package built with `uv`, with a committed `uv.lock` for reproducible builds. Dependencies are capped to a major version to keep breaking changes out of the build while still picking up bug and security fixes; the lockfile is what actually pins them.

The service is packaged as a container image, the de-facto standard for server distribution.
The base image is `python:3.13.14-slim-bookworm`: Debian rather than Alpine because I considered LAmbda as the initial deployment target, and Debian is `glibc`-based (compatible with the Lambda Web Adapter out of the box) while Alpine is `musl`-based and causes problems there. Alpine would be the better pick for ECS or EKS.

Dependency installation and project installation are split into two `RUN` layers, so a code change that does not touch dependencies does not refetch them on rebuild. Test and lint dependencies are excluded from the runtime image (`uv sync --no-dev`). The image meant to be run as a non-root user. It still carries the `uv` binary to keep the `Dockerfile` simple, which a multistage build would trim.

## Testing

`pytest` against a real PostgreSQL instance, started by the same documented command that runs the suite. Business logic is tested against the domain and persistence layer directly, not only through HTTP, and **nothing that asserts transactional or concurrency behaviour is mocked**: two committed transactions racing the same row is the property under test, and a mocked session cannot exhibit it.

Test isolation is truncate-and-reseed per test rather than a rolled-back outer transaction, precisely because a rollback fixture cannot host the concurrency tests. The table list is derived from the model metadata rather than hand-maintained. Migrations are `test.sh`'s job and no fixture repeats them; a session-scoped fixture only asserts the schema is present and points at `test.sh` if it is not.

Beyond the happy path:

- `tests/test_transfers.py`: insufficient funds, the boundary landing exactly on the overdraft limit, one cent past it, self transfer, unknown accounts, zero and negative amounts, amounts not coerced from strings, and that a failed transfer leaves no entries.
- `tests/test_idempotency.py`: a replayed key returns the original transfer with the entry count unchanged; a key reused with a different amount or a different destination is a `409` that writes nothing.
- `tests/test_concurrency.py`: two sessions racing one funding account, five simultaneous transfers against a limited balance, opposing A-to-B and B-to-A transfers, and two threads racing the same idempotency key. Assertions are on outcomes and on the final balance never crossing the limit, not on the absence of an exception.
- `tests/test_replay.py`: reported balances compared against an independent recomputation summed from the entry log, value conservation across randomised transfers checked after every one of them, the log proven append-only, and every prefix of it a valid history.
- `tests/test_accounts.py`: history pagination, including that a walk over several pages concatenates to exactly the unpaginated ordering, that `next_cursor` is null only on the last page, that an account with more entries than the page maximum is still reachable in full, and that a cursor cannot be used to read across accounts.
  One case writes an entry midway through a walk and asserts the boundary does not shift: that is the case an offset would fail, so it is the one that earns the cursor.

Coverage is reported and currently 99% against a floor of 90%, which is worth reading as a floor rather than as evidence: coverage says the lines ran, not that the system is exhaustively tested.

The concurrency tests were checked by mutation rather than trusted for being green. Removing `.with_for_update()` fails two of them, and making the overdraft comparison exclusive fails the boundary test. Removing the ascending-id `ORDER BY` fails nothing: both racers take both rows in a single statement, so PostgreSQL gives them the same plan and therefore the same lock order anyway.
The `ORDER BY` stays because it makes the guarantee explicit rather than a property of the current planner, which is exactly the kind of thing an added index or a version bump changes.

## Deployment

The first deployment is this container image on **Lambda** behind **API Gateway**, with **RDS PostgreSQL** for storage, Alembic migrations run as a one-off task in the deploy pipeline rather than at application startup, and IAM-scoped database credentials injected as `BANKING_DATABASE_URL` from Secrets Manager rather than baked into the image.
Lambda is the most lightweight option; ECS or EKS would need justification for the extra infrastructure and cost. The Lambda Web Adapter is built into the image so that the same artifact runs unchanged locally and on Lambda: there it longpolls the Runtime API and forwards each invocation to the `uvicorn` the image already runs, at the cost of one extra process and an HTTP hop.

The real tension in that shape is connection management, not the transaction boundary. A Lambda invocation maps cleanly onto one HTTP request and therefore onto one database transaction.
But Lambda scales by adding execution environments, each holding its own connection pool, so RDS `max_connections` is consumed by concurrency rather than by throughput, and a spike that would be unremarkable on a fixed fleet exhausts the database.
The first iteration would therefore use SQLAlchemy's `NullPool`: connection count becomes a function of in-flight requests rather than of environment count, and the price is a TCP, TLS and PostgreSQL auth handshake on every request, which is latency and not correctness. Sustained concurrency is the signal to move to RDS Proxy or to a long-lived container service, sized in **Production** below.
Naming the trigger is what makes this a decision rather than an oversight.

The `banking-db` container is a local development and test fixture, with `tmpfs` storage that is discarded on stop. It is never deployed.

## Production

Deliberate compromises, ordered as a roadmap rather than a list: correctness gaps first, then conveniences. Each says what breaks first and roughly what closing it costs.

### 1. No authentication or authorisation

Every endpoint is open. Anyone who can reach the service can move money between any two accounts. This is the largest gap in the repository, and leaving it whole rather than half-built is deliberate: partial auth is worse than none, because it invites the reader to assume a security property that is not there.

What breaks first is any exposure beyond a trusted network. Closing it means an authenticating edge (Cognito or an OIDC provider in front of API Gateway, never a hand-rolled protocol), a caller identity propagated into the request context, and authorisation rules for which employee may move funds on which account. Call it two days for something honest, plus the audit column below.

### 2. No audit actor on a transfer

The ledger records that money moved and when, but not which employee moved it. For an internal bank API this is a compliance gap rather than a nicety. What breaks first is any question of the form "who authorised this". Closing it is an `initiated_by` column on `transfers` populated from the authenticated caller, so it is blocked on item 1 and is about an hour after that.

### 3. Deposits are single-sided entries

Funds enter through a `DEPOSIT` entry, which is exempt from the overdraft check and has no counter-entry. This is a deliberate departure from strict double-entry: in a fully balanced ledger an incoming deposit would debit a bank-side account (equity, or a cash or settlement account representing the funding source) rather than appearing from nowhere.
That account was left out because the assignment has no external funding rail to model it against, and inventing one adds a concept a reviewer then has to learn in order to read the ledger.

The practical consequence is that summing every entry in the system does not come to zero, so a whole-ledger sum cannot be used as an integrity test. Per-account balances and the transfer path are unaffected.
Closing it means introducing the counter-account, making `DEPOSIT` write a balanced pair against it, and exempting only that account from the overdraft rule; the system-wide sum then becomes zero and is worth asserting continuously.
Real deposits would also arrive from a payment rail rather than an API call, so the entry would carry a reference to the external settlement event and be idempotent on it, exactly as transfers are idempotent on a client-supplied key. Half a day, and it is the item that most changes what the ledger means.

### 4. Balances are recomputed from the entry log on every read

No cached or snapshotted balance column exists. What breaks first is read latency, once an account accumulates enough entries for the aggregate to miss its latency budget; nothing becomes incorrect.
At a scale like 60k customers reporting on quarter-hourly intervals, which is millions of entries a day, summing an account's whole history on every read stops being viable within weeks, and the answer is not to abandon the log but to close each settlement period into a snapshot written in the same transaction as the entries that move it.
Closing it means a materialised balance updated in the same transaction as the entries that move it, so a read is a single row lookup. That balance is a cache and never an authority, so it needs a reconciliation job that recomputes from the entry log and alerts on disagreement, and the existing replay test becomes the assertion that the two agree. Roughly a day with the reconciliation.
Measure before adding the column.

### 5. Connection management under Lambda

`NullPool` pays a connection handshake per request, which is the price of not letting Lambda concurrency consume RDS `max_connections`. What breaks first is latency under sustained load, not correctness. Closing it means RDS Proxy, which pins a backend connection for the life of a `SELECT ...
FOR UPDATE` transaction and therefore recovers less than the marketing suggests, or moving to a long-lived container service such as ECS Fargate with a real pool. Roughly a day either way, and ECS is the option I would take if the traffic profile justified it.

### 6. The overdraft limit cannot be changed after creation

There is no update route, because a mutable limit needs the authorisation story of item 1. A limit that can only be set at creation is still enough to exercise the boundary behaviour. Closing it is a small route plus the trail of who changed it and when, which for a credit limit should itself be an append-only record.

### 7. Test infrastructure

A single shared database container with truncate-and-reseed between tests. What breaks first is running the suite in parallel, where tests would truncate each other's data. Closing it means testcontainers with a database per worker, roughly half a day including the CI wiring.

### 8. Operational visibility

There is a `GET /health` liveness probe and nothing else: no structured JSON logging with a correlation ID, no metrics, no tracing. What breaks first is the first production incident, where a request cannot be followed across the API Gateway, Lambda and RDS boundaries.
Closing it is structured logging with a request ID taken from the API Gateway context, plus the metrics that matter here, which are transfer rate, rejection rate by code, and lock wait time. A day.

### Also deliberately not built

A second currency, a repository abstraction with exactly one implementation, rate limiting, an `app.openapi()` snapshot test, a digest-pinned base image, and any Terraform or CloudFormation. Scope discipline is itself a deliverable; each of these is a line here rather than silence.

## Known limitations and accepted risks

- **No authentication, no authorisation, no rate limiting.** See items 1 and 8 above.
- **No audit actor.** The ledger says what moved, not who moved it.
- **Single currency.** No currency column, and adding one later touches every monetary column and every comparison.
- **Amounts are capped** at 1_000_000_000_000 cents, and a single history response at 1000 entries. Both are enforced where they are declared, not repeated in prose. The history bound is on one page and not on reachable history: the cursor walks past it.
- **The whole-ledger sum is not zero**, because deposits are single-sided. See item 3.

## AI Tool Use

The assignment asks to document the AI tool use. This section summarizes what each of the sessions decided, what agent suggestions were rejected, etc.

### 1 - Guardrails

> I have an assignment @Assignment.md for the role @StaffSoftwareEngineer.txt and my background is in @Experience - I want a set of initial guardrails considering the blind spots I might have in my use of Claude Code, but also in any gaps between the role and my profile.
> Seed a concise CLAUDE.md file which shall provide the guardrails for the implementation - mindful of the aspects related to concurrency and race conditions, DB transaction semantics, migrations aspects as well as the money handling (append-only double-entry, idempotency keys, no float for money, replayable balances).

**Result.** Wrote the first `CLAUDE.md`, constraints rather than procedures, with framework and database left open. Rejected framing the task as a CRUD service over a stored balance column.

### 2 - Assignment Differences

> Read @AssignmentOld.md and @AssignmentNew.md. I completed the old version of this assignment before; I need to know exactly what has changed before I plan the new one.
>
> Produce a requirements delta, not a summary of either document:
>
> - requirements added, removed, or reworded in a way that changes what I must build or hand in
> - changes to evaluation criteria, framework or language constraints, submission mechanics, and the stated time budget
> - anything that may invalidate a design decision or artifact that would otherwise carry over unchanged
> - wording that is ambiguous enough that two reasonable readings lead to materially different work, flagged for me to decide
>
> Ignore cosmetic edits. Where the change is subtle, quote both versions side by side so I can judge it myself rather than relying on your reading.

**Result.** New: a framework exclusion, a 4 hour budget, the AI tool conversation logs, and production-readiness documentation as its own criterion. Functional requirements unchanged; four ambiguities flagged for decision.

### 3A - Decisions

> I simplified CLAUDE.md to disambiguate it (uncommitted) and decided upon a couple of open decision points. Review the change (`git diff CLAUDE.md`), the current file and @Assignment.md.
> 1. Given the current CLAUDE.md, what are there decisions to prioritize next? Are all covered: storage engine, money representation, how a transfer mutates state, transaction boundary, locking, how the schema is created, error mapping, etc. For each, say if it determines the decision or leaves it to you. Anything you would have to invent is a gap I want to fill prior to planning step.
> 2. Diff review. For what I removed or reworded:
> - did any of it carry a constraint the assignment's evaluation criteria depend on (correctness, maintainability, production readiness, testing, documentation)?
> - did the simplification introduce a contradiction, or ambiguity?
> - is anything left that is dead weight: guidance you would follow anyway?
> Rank the findings by the impact on the code. Give quotes for before and after for each. If a change is fine, confirm and move on.
> Do not edit the file.

**Result.** No edits, as instructed. Storage engine and how deposits are funded were named as the two decisions blocking architecture, and the reworded transaction boundary was shown to have introduced a contradiction.

> Decisions:
> - cache: skip and document as something for production
> - migration: let's use Alembic since it is written by the SQLAlchemy author, and the code you'll be reworking is SQLAlchemy-based
> - isolation: we will go for *read committed with explicit row locks* - I suspect that *serializable* might be to big/complex for the timebox and *read committed with explicit row locks* covers the overdraft handling
> - storage: let's go for Postgres
> - funds origination: a special DEPOSIT entry type that is exempt from balance reflects common intuition better (without inventing equity/genesis account)
> - money representation: yes, I meant store cents (AFAIK the code you will be modifying already used that approach)
> - error catalogue: you will be reworking existing code which already uses some, you are allowed to invent the missing ones
> - idempotency key transport: go for body field, client-supplied
> - pagination: cursor pagination on a single monotonic integer key seems like the simplest solution
> - timebox: put back in, you will be later combining two of my past projects as implementation of this assignment and I estimate the implementation will be well under 4 hours anyway
> - overdraft contradiction: go the assignment's required create-account-with-initial-deposit route (putting pre-population in is my mistake)
> - transaction boundary and thin handlers clarification: Each request gets exactly one database transaction. A single boundary opens and commits it. A session may provide and scope the session, but the commit happens where a failure (idempotency conflict, etc.) can still become a typed domain error and a response, not after the handler has returned.
>   Handlers stay thin: they own the boundary and delegate the work to the domain layer. While the transaction is open, make no network or external calls. Does this disambiguate/reconciliate the issue?
> - remove "Style" section in CLAUDE.md
> - remove "Corrections are compensating entries, never edits."
> - replace "Migrations are forward-only ..." by a mention that a migration becomes immutable only once a database that cannot be recreated has run it
> You may update CLAUDE.md & README.md as needed to document this.

**Result.** `CLAUDE.md` rewritten across every affected section; the transaction-boundary wording resolved the contradiction and was adopted nearly verbatim. Three calls were made and flagged rather than buried, including that deposits have no counter-entry, a real departure from double-entry. Other decisions: locking strategy, how the schema is created, error mapping.
Rejected `SERIALIZABLE` with a retry loop as not fitting the timebox, in favour of `READ COMMITTED` plus explicit row locks.

> add 3 to Production section of README and same for the decision on caching - stub is fine for now, you will be adding to it during implementation

**Result.** Created `README.md` as a placeholder with a real `Production` section carrying both entries, each with what was skipped, why, and what closing it costs.

### 3B - Best Practices

> Read @Assignment.md and @CLAUDE.md. For each evaluation criterion in the assignment (Python best practices completeness, correctness, maintainability, production readiness, testing, documentation), answer as the reviewer:
>
> - what will a reviewer actually open to judge this criterion, and what conclusion do they draw if it is absent or weak
> - what specifically distinguishes a strong submission from a merely competent one for a money-moving ledger API, as opposed to any CRUD service
> - the common failure modes that lose points here
>
> Then, given the stated 4-hour budget, split your findings into: worth doing, worth stubbing with the gap documented, and deliberately not doing.
>
> Constraints:
> - exclude anything that would be equally true of any Python project. If it applies to a todo API, leave it out.
> - do not re-open decisions already fixed in CLAUDE.md; assume those settled.
> - concrete artifacts over principles: name the file, endpoint, test, or README section that carries the evidence.
>
> Write the result with the "worth doing" items to PRACTICES.md as input into planning. No implementation code yet.

**Result.** Wrote `PRACTICES.md`, organised by the artifact carrying the evidence rather than by criterion. Worked each evaluation criterion from the reviewer's side and split the findings into worth doing, worth stubbing with the gap documented, and deliberately not doing. The distinguishing property identified: strong submissions make the invariant unbypassable rather than merely upheld.
Rejected several suggestions as speculative for a 4-hour budget.

### 3C - Gaps to Fix

> I implemented the assignment in @Assignment.md when applying for Principal Software Engineer, and was rejected after submitting. I am now reworking it for a Staff Software Engineer application; the role description is at @StaffSoftwareEngineer-JobDescription.pdf.
>
> Be aware that I am telling you it was rejected, which will bias you toward finding fault. I want calibration, not a demolition.
>
> First, audit the setup path statically, since you cannot run Docker here. Trace every command in @README.md against the actual files.
> Report every point where a reviewer following the README verbatim on a clean machine would hit a missing file, an undocumented prerequisite, an unset variable, a version that is not pinned, a service that is started before its database is ready, or a step the README omits entirely. Be concrete about which line of which file is wrong. Do this before forming opinions from reading the code.
>
> Then list the commands you want me to run and paste output for, ranked by what would tell you the most. I will run them and report back.
>
> Then read the assignment, the README write-up, and the implementation.
>
> 1. Gaps against the assignment's evaluation criteria and against the Staff role bar.
> Separate:
> - issues that would plausibly have cost me the submission
> - issues that are real but would not have been decisive
> - things that were done well and should carry over unchanged
> State explicitly where you cannot know whether something mattered. The rejection may have had nothing to do with the code.
>
> 2. Testing: what exists today, what is missing, ranked by the risk each uncovered case carries for a money-moving service. Not a generic list.
>
> 3. REST and CRUD conventions, including Richardson maturity: separate deviations that are actual mistakes from ones that are defensible choices for an internal API. Do not push hypermedia for its own sake; say what a reviewer would reasonably expect at this level.
>
> 4. The README itself is graded on documenting design choices and the path to production. Assess it as a reviewer would, separately from the code.
>
> Write the findings to FIX.md, ordered by what I should fix first given a ~4-hour rebuild, and marking each item as: fix in the rework, or keep as-is and document as an intentional trade-off.

**Result.** Wrote `FIX.md` with a calibrated audit of the previously submitted version. Produced the numbered findings the plan works from. Rejected findings that were stylistic rather than substantive.

> The path is /home/user/workspace not /home/user

**Result.** Clarified that `/home/user` was the computed value of `BASE`, which is the bug itself, and reproduced it from an unrelated directory to show the failure follows the command rather than the checkout location.

> Shorten the FIX.md file to just one-sentence bulletpoint instructions of what to fix. This file will go as one of the input into planning for the rework of the old code into new deliverable.

**Result.** `FIX.md` rewritten as 47 one-line instructions in three blocks, file and line references preserved, all rationale dropped.

> In `FIX.md`:
> - I will be moving to Postgres so replace mention of SQLite
> - Mention Lambda & API Gateway instead of ECS

**Result.** Two remaining SQLite references rewritten to argue from Postgres enforcement; zero SQLite and zero ECS mentions left.

### 4 - Planning

> What would you recommendation be in general for this (considering that the DB would be on RDS anyway): **The real tension is connection management.** Lambda scales by adding execution environments, each holding its own SQLAlchemy pool. `max_connections` on RDS is consumed by concurrency, not by throughput, so a spike that would be unremarkable on a fixed fleet exhausts the database.
> The mitigations are RDS Proxy, or `NullPool` and paying connection setup per request, and both are real costs.

**Result.** Recommended against Lambda, since RDS Proxy pins a connection for the life of a `FOR UPDATE` transaction and a ledger write path therefore defeats its multiplexing. **Overruled** in favour of Lambda plus `NullPool`, with the trigger for revisiting it written into `Production`.

> - Yes, I know the new design is **double-entry, append-only ledger with derived balances** and old **stored-balance** and that this mandates a rewrite - this is fine
> - **Not** moving `older/service/README.md` is fine with me - I would do it the same way you propose
> - `compose.yaml` is fine for the assignment - thus you can simplify C.1
> - Make sure the code uses `fastapi.status` constants rather than literal integers for HTTP codes
> - Do not mention `ECS Fargate` in the plan (nor in `CLAUDE.md`), the first deployment would be to containerized `Lambda` anyway, and as for `Lambda` restricting Postgres, we'd go with `NullPool` for first iteration, and remark in `Production` section that this could justify a move to `ECS`.
> - Service README's AI-use disclosure, is meant to be replaced by a short list of files in `sessions/` (the non-dot ones)
> - As for timing budget, I had similar tooling item (Phase A) done before - it was much faster than 54 minutes so let's keep it; Keep Phase B & C as-is; Drop cursor pagination, mention in `Production` section close to the caching one; If the budget still exceeds 4 hours I authorize the extra time as the remaining scope is also interesting for me as a learning exercise
> - For the reformat blast radius in Phase A: I'm fine with two commits inside Phase A, `git mv` first, the reformat second
> - Keep the customer routes
> - Package name: keep `banking`
> - CI pipeline stays, fine with cutting the test part to `test.sh`
> - All assumptions suggested in the plan are fine

**Result.** The phase-by-phase rework `Plan.md` and `CLAUDE.md` updated to match every decision. Notably, the model recommended **against** Lambda and in favour of a long-lived ECS Fargate container with a small pool; that recommendation was rejected in favour of Lambda plus `NullPool`, with the trigger for revisiting it written into **Production** item.

> Is the plan unambiguous and non-contradictory now? Are any decisions left to be made?

**Result.** No. Eleven problems found and ten fixed, the worst being seeded customer IDs that would break the first `POST /customers` only on a clean clone, which is exactly where a reviewer runs it. The overdraft limit had a column and a test but no route that set it, so it became a create-time-only field.

> Make CLAUDE.md in line with the plan w.r.t. mandate cursor pagination (i.e. no pagination for the moment). As for numbers: fail_under 85, history page limit up to 1000, and keep trillion cents as the cap

**Result.** Both documents brought into agreement with zero knowing gaps, and the three numbers fixed and propagated.

### 5 - Implementation

Executed phases A to F individually, one commit each.

> Implement the plan in @Plan.md starting with Phase A

**Result.** Phase A (tooling and layout) delivered as two commits, all checks green. `DeclarativeBase` had to land early because `declarative_base()` is untypeable under mypy, which surfaced six real errors where handlers annotated Pydantic types but returned ORM objects.

> keep LicenseRef-Proprietary though

**Result.** Reverted, which changes the metadata key to `License-Expression`, so `main.py` was updated to read it; otherwise the OpenAPI license would have rendered as `None`.

> BTW: When running Docker containers you can rely on `--network host` to make things work in the container you are running in

**Result.** Closed the one unverified Phase A item: a full round trip works inside the container.

> Phase B

**Result.** Phase B (unittest to pytest) complete at 24 tests, pass/fail set identical modulo the split. A single shared session turned out not to work, because a committed handler re-loads the expired object and autobegins a new transaction, so the override hands out one session per request.

> Phase C

**Result.** Phase C (SQLite to PostgreSQL, compose, Alembic, `test.sh`) complete, with constraints verified from `pg_constraint` rather than assumed. Two self-caught bugs fixed before committing, including doubled CHECK constraint names that `alembic check` cannot see.

> Phase D

**Result.** Phase D, the ledger rewrite, complete at 28 tests. Correctness was verified directly rather than trusted to a green suite: the emitted SQL, the replay body, two racing threads, and simultaneous A to B and B to A were each checked by hand.

> Phase E

**Result.** Phase E complete at 40 tests, mutation-tested rather than trusted for being green. Removing the row lock is caught, but **removing `.order_by(Account.id)` changes nothing**, reported plainly and recorded on the test.

> Phase F

**Result.** Phase F (documentation) complete, finishing the rework at six commits, with every readme command run verbatim rather than trusted. The `Production` roadmap leads with "no authentication", ahead of caching and pagination.

### 6 - Implementation Extension

> Look-up the history of changes in @sessions/4_planning.md w.r.t. cursor pagination and introduction of cap of 1000 on the history. Add a phase G to the Plan.md with instructions to implement pagination as originally intended and lift the cap.

**Result.** Reopened one scope decision. Phase F had cut cursor pagination as a budget measure and written it up as a `Production` entry; Phase G reverses that, building the cursor over the entry ID, lifting the 1000-entry ceiling to a per-response page bound

> Implement phase G

**Result.** Phase G implemented at 45 tests, with the `Production` entry deleted rather than reworded and the cut-then-restore recorded rather than tidied away. Docker was unavailable, so the suite ran against an already-running Postgres.

### 7 - Reinit `CLAUDE.md`

> /init

**Result.** Regenerated `CLAUDE.md` from the finished repository with `/init`, so the standing working agreements describe what was built rather than what was planned at the outset.
