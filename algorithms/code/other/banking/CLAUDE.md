# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Internal HTTP API for bank employees: customers, accounts, derived balances, idempotent transfers, per-account transfer history. Python 3.13, FastAPI, SQLAlchemy 2.x ORM, Alembic, PostgreSQL, packaged and run with `uv`. Source lives under `src/banking/` (package name `banking`).

## Commands

```shell
./automation/test.sh                       # the whole suite: starts Postgres, migrates, runs pytest + coverage
```

`test.sh` is the single definition of how tests run, and CI (`.gitlab-ci.yml`) calls it too. It starts Postgres with `docker run` as the container `banking-db`, polls `pg_isready`, runs `alembic upgrade head`, then `coverage run -m pytest`. The container is reused when already running and uses `--rm` plus a `tmpfs`, so `docker stop banking-db` is a full reset.
If `BANKING_DATABASE_URL` is already exported it uses that database and skips the container entirely.

Running pytest directly requires a migrated database first, otherwise the session-scoped `schema` fixture fails with an explanatory message:

```shell
docker run --detach --rm --name banking-db \
  --env POSTGRES_USER=banking --env POSTGRES_PASSWORD=banking --env POSTGRES_DB=banking \
  --publish 5432:5432 --tmpfs /var/lib/postgresql postgres:18-alpine
until docker exec banking-db pg_isready --quiet --username banking; do sleep 0.1; done

export BANKING_DATABASE_URL="postgresql+psycopg://banking:banking@localhost:5432/banking"
uv run alembic upgrade head
uv run pytest tests/test_transfers.py::test_successful_transfer_moves_the_balance -v  # a single test
```

Serving locally: `uv run uvicorn banking.main:app --port 8000`, then `/` redirects to the OpenAPI docs.

Checks (all of these run in CI's `lint` job except `alembic check`):

```shell
uv run ruff check --select I,E,B,SIM src
uv run ruff format --check --diff
uv run deptry .
uv run mypy src
uv run bandit -r src
uv run complexipy src --max-complexity-allowed 25
uv run alembic check     # migrations still match the models
```

Coverage has `fail_under = 90` (`pyproject.toml`).

## Architecture

Three layers, and the boundaries are the point:

- `api/` (`routes.py`, `customers.py`, `schemas.py`, `errors.py`) is thin. Handlers own the transaction boundary (they call `session.commit()`), validate at the edge through Pydantic schemas, and delegate the work.
  `errors.install()` registers handlers mapping each `DomainError` subclass to a status code and the single `ErrorResponse` body shape, so domain failures never surface as a 500 or a raw framework validation dump.
- `domain/` (`ledger.py`, `transfers.py`, `__init__.py`) holds all business rules and the typed errors. It takes a `Session` but never commits.
- `models.py` / `migrations/` define the schema. `database.py` builds the engine (`NullPool`, chosen for Lambda) and the `get_session` FastAPI dependency.

### The ledger is the core

Balances are never stored. The `entries` table is append-only and double-entry, and a balance is `SUM(amount_cents)` over an account's entries, recomputed on every read. Nothing updates or deletes an entry, so a reported balance can always be reproduced by replaying the log (`tests/test_replay.py` asserts exactly that).

- `EntryType.DEPOSIT`: single positive entry with no counter-entry, exempt from the balance check. The only way funds enter the system, and it happens exactly once, at account creation.
- `EntryType.TRANSFER`: entries that must sum to zero; `ledger.record` raises `ValueError` otherwise.

Money is integer Euro cents everywhere, including across the API. Never a float, never a second stored major-unit column, no currency column (single currency assumed). Request amounts are positive `StrictInt`; direction is expressed by the ledger's sign, not by the client.

### Concurrency

`READ COMMITTED` (Postgres default) plus explicit row locks, not a higher isolation level. `ledger.lock_accounts` issues one `SELECT ... FOR UPDATE ORDER BY Account.id` over every account a write touches: ascending-id ordering is what stops A-to-B and B-to-A transfers from deadlocking, and holding the lock from read to commit is what makes the overdraft check safe.
Never read a balance, decide in Python, and then write outside that lock. Do not make network or external calls while the transaction is open.

Every account has a non-negative `overdraft_limit_cents` (default 0); a debit is rejected unless the resulting balance is at least `-overdraft_limit_cents`.

### Idempotency

`POST /transfers` is idempotent on `idempotency_key` in the request body. Enforcement is the database's unique constraint, never a `SELECT` then `INSERT`: `transfers.execute` inserts inside a `begin_nested()` savepoint and catches `IntegrityError`, checking `database.violated_constraint(error)` against `uq_transfers_idempotency_key` before treating it as a replay.
A replay returns the original outcome; the same key with a different payload compares against the stored `request_hash` and raises `IdempotencyConflict` (409).

### History pagination

Cursor pagination over `Entry.id`, the single monotonic key, never an offset and never a timestamp ordering. Handlers fetch `limit + 1` rows to decide `next_cursor`, which is null on the last page. The page bound (100 default, 1000 max) limits one response, not how much history is reachable.

## Conventions

- HTTP status codes are always `fastapi.status` constants, never integer literals, in handlers, decorators and tests alike.
- Branch on enum values with `match`/`case`, not `if`/`elif`.
- Constraint and index names come from the `NAMING_CONVENTION` metadata in `models.py`; code that inspects a constraint by name (idempotency) depends on it.
- Migrations are the only way the schema is created, including for tests. No `create_all`. `0001_initial_schema.py` also seeds the four assignment customers. Until a non-recreatable database has run it, amend that initial revision in place rather than stacking a correction revision.
- Comments and docstrings are deliberately sparse and non-chatty; recent commits removed chatty ones, so do not reintroduce them.

## Testing

Tests run against a real PostgreSQL instance. Do not mock the database for anything asserting transactional or concurrency behaviour.
`tests/conftest.py` truncates all tables and re-seeds the customers after every test, and exposes helpers (`open_account`, `open_account_directly`, `transfer`, `walk_history`, `balance_cents`) that tests import directly from `conftest` (`pythonpath` includes `tests`). Concurrency tests use real threads with a `threading.Barrier` and separate sessions.

Business logic is tested against the domain and persistence layer, not only through HTTP. Coverage beyond the happy path is expected for: insufficient funds, idempotent replay, concurrent transfers on a shared account, and the replay check that recomputed balances match reported ones.

## Documentation

The README is weighted as heavily as the code. Anything deliberately stubbed goes in its `Production` section as a roadmap entry saying what breaks first and roughly what closing it costs, ordered correctness gaps first. Never silently omit a compromise. `sessions/` holds the transcripts of the sessions that produced this repository, with an index in the README.
