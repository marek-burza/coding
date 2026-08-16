# Rework plan: merging the two older codebases into one top-level deliverable

## What this plan is

`older/service` supplies the repo shape and tooling. `older/banking` supplies the domain.
The top level ends up looking like `older/service`, with the bank domain inside it. Every
file that has a genuine ancestor arrives by `git mv` so history survives; files with no
ancestor are created new, and that distinction is called out per phase rather than faked
with an arbitrary rename pairing.

All findings this plan depends on are inlined below. It does not depend on any other
document in the repo except `CLAUDE.md` and `Assignment.md`.

## The single largest thing to understand before starting

`CLAUDE.md` mandates a **double-entry, append-only ledger with derived balances**. The
`older/banking` code is a **stored-balance** design: `accounts.balance` is a column,
`api/transfers.py:117-118` mutates it in place, and there is no entry table. There is no
incremental path from one to the other. The following are therefore not adaptations but
rewrites, in a repo where the surviving lineage is naming, layering and the FastAPI idiom
rather than the transfer logic itself:

| Concern | `older/banking` today | Required end state |
| --- | --- | --- |
| Balance | `accounts.balance` column, mutated | derived by aggregating ledger entries |
| Transfer | two in-place column updates | balanced `TRANSFER` entry pair in one txn |
| Deposit | initial `balance` argument | single-sided `DEPOSIT` entry, overdraft-exempt |
| Overdraft | implicit `balance >= 0` | per-account `overdraft_limit_cents`, default 0 |
| Idempotency | none | body key, DB unique constraint, payload hash |
| History | unordered list of transfer rows | cursor-paginated, per-account perspective |
| Concurrency | read-decide-write in Python | `SELECT ... FOR UPDATE`, ascending id order |
| Routes | 6 routes, `PUT`, query parameters | 6 routes, `POST`/`GET`, JSON bodies |

Budget consequences are in **Budget risk** near the end. The overrun is accepted: see
**Decisions taken**.

---

## Target top-level layout

```text
.
├── CLAUDE.md                  (unchanged)
├── Assignment.md              (unchanged)
├── README.md                  (consolidated: see Phase F)
├── Dockerfile                 (git mv from older/service, Lambda-ready, adapted)
│                              (the deployment target: containerized Lambda)
├── compose.yaml               (new: Postgres for local dev and for the suite)
├── pyproject.toml             (git mv from older/service, rewritten contents)
├── uv.lock                    (git mv from older/service, regenerated)
├── alembic.ini                (new)
├── .gitlab-ci.yml             (git mv from older/service, AWS jobs removed)
├── .gitignore, .dockerignore  (git mv from older/service)
├── automation
|   └── test.sh                (new: the one documented command)
├── migrations/                (new: single initial revision + env.py)
├── sessions/                  (unchanged)
├── src/banking/
│   ├── __init__.py            (git mv from older/banking)
│   ├── main.py                (git mv from older/banking, adapted)
│   ├── database.py            (git mv from older/banking, adapted)
│   ├── models.py              (git mv from older/banking/models/accounts.py)
│   ├── api/
│   │   ├── __init__.py        (git mv from older/banking)
│   │   ├── routes.py          (git mv from older/banking/api/transfers.py)
│   │   ├── customers.py       (git mv from older/banking/api/customers.py)
│   │   ├── schemas.py         (git mv from older/banking/schemas/transfers.py)
│   │   └── errors.py          (new)
│   └── domain/
│       ├── __init__.py        (new)
│       ├── ledger.py          (new: the single write chokepoint)
│       └── transfers.py       (new: the transfer use case)
└── tests/
    ├── conftest.py            (git mv from older/service/tests/conftest.py)
    ├── test_accounts.py       (git mv from older/banking)
    ├── test_transfers.py      (git mv from older/banking)
    ├── test_customers.py      (git mv from older/banking, one assertion inverted)
    ├── test_app.py            (git mv from older/banking/tests/test_redirect.py)
    ├── test_idempotency.py    (new)
    ├── test_concurrency.py    (new)
    └── test_replay.py         (new)
```

Files deliberately left behind in `older/` (they have no successor and die when you delete
that directory): all of `older/service/src/na/`, `older/service/infra/`,
`older/service/tests/` except `conftest.py`, `older/banking/automation/`,
`older/banking/run.sh`, `older/banking/populate.sh`, `older/banking/.flake8`,
`older/banking/Assignment.md`,
`older/banking/src/banking/{utilities.py,models/customers.py,models/transfers.py,api/accounts.py,schemas/{customers,accounts}.py}`.

Three of those deserve a note. `api/accounts.py` and `schemas/accounts.py` are dropped
rather than moved because `routes.py` absorbs the account and transfer routes and
`schemas.py` absorbs all request and response models; only one file can carry the rename
and `transfers.py` is the closer ancestor of each. The two surviving `models/` files are
folded into the single `models.py` for the same reason. `utilities.py` is dropped because
both of its functions go away (see finding F14 and F48).

`errors.py` is genuinely new. In an earlier draft it inherited `api/customers.py`'s
history, on the reasoning that the customer module was dying; the customer routes survive
(see **Decisions taken**), so `customers.py` keeps its own lineage and the error mapping
starts clean.

---

## Phase A: establish the structure and the tooling

No behaviour change. The suite is still `unittest`, still SQLite, and still passes at the
end of this phase. That is the point: it is the safety net for everything after.

**This phase is two commits, in this order.**

1. **A.1 and A.2**: pure `git mv` plus the import fixing that makes the tree importable
   again. No reformatting, no configuration change, so a rename-aware diff shows only
   renames and `git log --follow` keeps working across them.
2. **A.3 through A.6**: the tooling configuration (pyproject, Dockerfile, CI) together with
   the black-79-to-ruff-88 reformat that the new ruff configuration forces. The reformat
   touches nearly every line in the tree; it belongs with the configuration that causes it,
   and it must not be split further, because a tree between the two is unformatted against
   its own configuration.

### A.1 Move `older/service` to the top level

```shell
git mv older/service/Dockerfile older/service/pyproject.toml older/service/uv.lock \
       older/service/.gitlab-ci.yml older/service/.gitignore older/service/.dockerignore .
git mv older/service/tests/conftest.py tests/conftest.py
```

`older/service/README.md` is **not** moved. The root `README.md` already exists and already
carries CLAUDE-aligned `Production` prose (derived balances, single-sided deposits). It is
the consolidation target in Phase F, and `git mv -f` over it would destroy that text. This
is the one place where the "every file lands via `git mv`" rule is knowingly broken, and
it is confirmed in **Decisions taken**.

`older/service/infra/` and `older/service/src/na/` stay put and die with `older/`.

### A.2 Move the bank domain up

```shell
git mv older/banking/src/banking src/banking
git mv src/banking/api/transfers.py     src/banking/api/routes.py
git mv src/banking/schemas/transfers.py src/banking/api/schemas.py
git mv src/banking/models/accounts.py   src/banking/models.py
git mv older/banking/tests/test_accounts.py  tests/test_accounts.py
git mv older/banking/tests/test_transfers.py tests/test_transfers.py
git mv older/banking/tests/test_customers.py tests/test_customers.py
git mv older/banking/tests/test_redirect.py  tests/test_app.py
git rm -r src/banking/schemas src/banking/models/ \
          src/banking/api/accounts.py src/banking/utilities.py
```

Then fix imports so the package is importable under its new name. At this point the code
still uses the stored-balance logic; it just lives at the new paths.

### A.3 `pyproject.toml`, modelled on `older/service`

Keep from the service file: the `uv_build` backend, the `[dependency-groups] dev` shape,
`[tool.coverage]` with `show_missing` and `fail_under = 85`,
`[tool.pytest.ini_options]` with `testpaths` and `pythonpath`, and the major-version-capped
pin style (`>=x.y,<n`). 85 rather than the older tree's 100: the old number was reachable
only because the old surface was small, and defending it here would push effort towards
covering error branches that the type checker already makes unreachable.

```toml
[project]
name = "banking"
description = "Internal HTTP API for bank employees"
version = "0.0.0"
requires-python = ">=3.13"
dependencies = [
    "alembic>=1.16,<2",
    "fastapi>=0.139.2,<1.0",
    "psycopg[binary]>=3.2,<4",
    "pydantic>=2.13.4,<3.0",
    "sqlalchemy>=2.0.32,<3.0",
    "uvicorn[standard]>=0.51.0,<1.0",
]

[dependency-groups]
dev = [
    "bandit>=1.9.2,<2.0", "complexipy>=5.0.0,<6.0", "coverage>=7.13.0,<8.0",
    "deptry>=0.24.0,<1.0", "httpx>=0.28,<1.0", "mypy>=1.19.0,<2.0",
    "pytest>=8.4.0,<9.0", "ruff>=0.15.0,<1.0",
]

[build-system]
requires = [ "uv_build>=0.11.28,<0.12" ]
build-backend = "uv_build"

[tool.deptry.per_rule_ignores]
DEP002 = [ "alembic", "psycopg", "uvicorn" ]
```

Dropped from the service file: `cfn-lint` and `checkov` (no CloudFormation here),
`cloudpathlib`, `python-multipart`, `starlette`, `httpx2`.

Findings folded in here:

- **F41a** Drop the `setuptools-scm` build requirement; it is unused. Superseded anyway by
  moving to `uv_build`.
- **F41b** `older/banking/pyproject.toml:13` has the doubled license string
  `{text = "License: MIT"}`. Use the service form, `license = "..."`, once.
- **F28** A lockfile is required for reproducible builds. `uv.lock` supplies it. Digest
  pinning the base image is listed as optional in **Budget risk**.
- The old `black`/`isort`/`flake8` trio and `.flake8` are replaced by `ruff`, so the line
  length moves from the old 79 to ruff's default 88, and
  `skip-string-normalization = true` goes away. Expect a large but purely mechanical
  reformat diff, including the removal of every `# noqa` comment that only existed to
  satisfy flake8's line length. It is the second of this phase's two commits, so it never
  mixes with the renames.

### A.4 Tooling commands, carried over verbatim from `older/service`

```shell
uv run ruff check --select I,E,B,SIM src
uv run ruff format --check --diff
uv run deptry .
uv run mypy src
uv run bandit -r src
uv run complexipy src --max-complexity-allowed 25
```

### A.5 `Dockerfile`

`git mv` the service Dockerfile and adapt: keep `python:3.13.14-slim-bookworm` (glibc,
which the AWS Lambda Web Adapter needs), keep the AWS Lambda Web Adapter extension copy,
keep the two-`RUN` split that keeps dependency layers cached, keep `AWS_LWA_PORT=8000`,
drop `CLOUDPATHLIB_FILE_CACHE_MODE`, change the entrypoint to `banking.main:app`. Add a
non-root user.

This image is the deployment artifact: the first deployment is this container on Lambda
behind API Gateway, which is why the adapter and the port convention are kept rather than
treated as leftovers from the other assignment.

Findings folded in here:

- **F26** Drop the `uvicorn.run(port=80)` assumption at `older/banking/src/banking/main.py:58`.
  Port 8000, non-privileged, as in the service image.
- **F27a** Replace the hardcoded site-packages entrypoint of the old banking image
  (`ENTRYPOINT [".../site-packages/banking/main.py"]`) with the uvicorn invocation.
- **F27b** Test and lint dependencies must not reach the runtime layer. `uv sync --no-dev`
  achieves this; the old banking image installed `.[tests]` into the runtime image, which
  is also why the old README's claim to the contrary was false (see F17).
- **F41c** Drop the stale `# hadolint ignore=DL3013` comment from `older/banking/Dockerfile:7`.
- **F47** Keep the non-root container user (the old banking image had one, the service
  image does not).
- `.dockerignore` arrives from the service; add `migrations/` back in (the image needs it
  for the deploy-time migration task) and keep `tests` excluded.

### A.6 `.gitlab-ci.yml`

`git mv` it up, then delete the `.aws`, `provision`, `build` and `deploy` jobs and the
`STACK_*` variables, leaving the `default`/`stages`/`variables`/`.uv` scaffolding and the
`check` stage. The `lint` job loses the `cfn-lint` and `checkov` lines.

The `test` job is **cut to a single `./automation/test.sh` invocation**. It does not re-list the
pytest and coverage commands, so there is exactly one definition of how the suite runs and
CI cannot drift from what a reviewer executes locally. Its Postgres service block arrives
in Phase C, not here.

### A.7 Verification (must pass before Phase B)

```shell
uv sync && \
uv run ruff check --select I,E,B,SIM src && uv run ruff format --check --diff && \
uv run mypy src && uv run bandit -r src && uv run deptry . && \
uv run complexipy src --max-complexity-allowed 25 && \
uv run python -m unittest discover -s tests -p "test_*.py" && \
docker build -t banking .
```

---

## Phase B: `unittest` to `pytest` (sequential step 1 of 3)

Mechanical port only. No new behaviour, no new cases, still SQLite. `tests/conftest.py`
arrived from the service in Phase A; it is rewritten here to hold a `client` fixture in the
same `TestClient` context-manager idiom it already uses, plus a `session` fixture, both
wired through `app.dependency_overrides[database.get_session]`.

Findings folded in here:

- **F6a** Isolation must come from a fixture, not from `automation/3.test.sh:8` deleting
  `banking.db` between runs. That script is not carried over at all.
- **F8** `older/banking/tests/test_transfers.py:20-169` is a single 150-line method
  asserting many behaviours. Split it into one test per behaviour. Do it now, while it is
  a pure refactor, rather than during the domain rewrite.
- **F13/F14 consequence** `older/banking/tests/test_customers.py:42-62` asserts that
  duplicate customer names are rejected. The file is ported as-is here, because Phase B is
  a mechanical port and the constraint still exists at this point. The assertion is
  **inverted** in Phase D, where F13 removes the unique constraint: two customers may share
  a name, and the test says so.

Coverage moves to the service's invocation: `uv run coverage run -m pytest -v` then
`uv run coverage report`.

### Verification (must pass before Phase C)

```shell
uv run pytest -v && uv run coverage run -m pytest && uv run coverage report
```

The pass/fail set must be identical to Phase A's `unittest` run, modulo the split of the
one long test into several.

---

## Phase C: SQLite to PostgreSQL (sequential step 2 of 3)

Still the stored-balance domain. Only persistence changes. Suite green at the end.

### C.1 How Postgres is provided

A `compose.yaml` service, which also serves local development, so there is one artifact
rather than a test-only database plus a second mechanism for running the app. Testcontainers
goes in `Production` as the path worth taking once the suite runs in parallel.

```yaml
services:
  db:
    image: postgres:18-alpine
    environment:
      POSTGRES_PASSWORD: banking
      POSTGRES_USER: banking
      POSTGRES_DB: banking
    ports: [ "5432:5432" ]
    tmpfs: [ /var/lib/postgresql/data ]   # throwaway, and faster
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -U banking" ]
      interval: 1s
      retries: 30
```

`--wait` takes its readiness from that healthcheck, so `test.sh`, the one documented
command, needs no hand-rolled wait loop:

```sh
#!/bin/sh
set -e
if [ -z "${BANKING_DATABASE_URL}" ]; then
    docker compose up --detach --wait db
    export BANKING_DATABASE_URL="postgresql+psycopg://banking:banking@localhost:5432/banking"
fi
uv sync
uv run alembic upgrade head
uv run coverage run -m pytest -v
uv run coverage report
```

`.gitlab-ci.yml`'s `test` job calls this same script (A.6), so there is no second
definition of how the suite runs.

### C.2 Test isolation strategy (a real design decision, not a detail)

The obvious fixture (wrap each test in a transaction and roll it back) **breaks the
concurrency tests that Phase E must add**, because two sessions racing the same row need
two genuinely committed transactions and cannot share one outer transaction. Rather than
run two contradictory isolation mechanisms, use one that works for both:

- **Migrations are `test.sh`'s job, not a fixture's.** `test.sh` runs
  `alembic upgrade head` before invoking pytest, and no fixture repeats it. One owner, so a
  reviewer never has to work out which one actually created the schema. The session-scoped
  fixture only asserts that the schema is present and fails with a readable message
  pointing at `test.sh` if it is not.
- **Function-scoped, autouse**: after each test, `TRUNCATE` **every** domain table with
  `RESTART IDENTITY CASCADE`, then re-seed the four assignment customers. The table list is
  derived from `Base.metadata.sorted_tables` minus `alembic_version`, not hand-maintained,
  so Phase D's new `entries` table is covered without editing the fixture. Truncate is fast
  enough at this data volume that the loss versus rollback is not measurable.
- Customers are truncated like everything else, because `POST /customers` tests create them
  and leaked rows would make test order significant. Re-seeding restores ids 1 to 4, and
  the fixture advances the customer id sequence past the seeded rows the same way the
  migration does (see D.1), so a subsequent `POST /customers` cannot collide.

This supersedes the rollback-fixture half of finding F6.

### C.3 Database wiring

- **F2** `older/banking/src/banking/database.py:9` hardcodes
  `DATABASE_URL = 'sqlite:///./banking.db'` as a module constant. Make it environment
  configurable (`BANKING_DATABASE_URL`), which is the prerequisite for Postgres and for
  injecting credentials from Secrets Manager later. Drop the SQLite `check_same_thread`
  connect-arg along with it.
- **F9** `database.py:25-26` yields a bare `Session()` that is never closed. Under SQLite
  that leaked nothing that mattered; under Postgres every unclosed session holds a real
  connection open. Wrap the yield in `with Session() as session:`.
- **Pooling: `NullPool`, with a comment saying why.** The deployment target is a container
  on Lambda, where each execution environment holds its own pool and `max_connections` on
  RDS is consumed by concurrency rather than by throughput. `NullPool` trades a connection
  handshake per request for a connection count that cannot outrun the database. The comment
  names the two alternatives it was chosen over (RDS Proxy, or a long-lived container
  service with a real pool) and points at the `Production` entry that sizes them.
- **F41d** Remove both `Base.metadata.create_all` calls (`database.py:29` and
  `main.py:15`). Alembic is the only way the schema is created, for tests as well.

### C.4 Alembic

- **F3** Add an Alembic baseline. Postgres removes the `create_all` safety net and schema
  changes now need an explicit ordered path. A **single** initial revision creates
  everything, including the four seeded customers from the assignment (John Doe,
  Jack Smith, Jane Taylor, Jade Wilson), so that `alembic upgrade head` against an
  empty database is enough to exercise the API. It is amended in place in Phase D rather
  than corrected by a second revision, which is legitimate while no unrecreatable database
  has run it.
- **F32** Add the `NOT NULL` constraints the SQLite schema never had, now that Postgres
  actually enforces declarations that were previously decorative: on amounts, on the
  account references, and on the foreign keys.
- **F4b** Add the table-level `CHECK` constraints (positive amount, non-negative overdraft
  limit) so the database refuses invalid state independently of application code.

### C.5 SQLAlchemy currency

- **F30** Migrate to SQLAlchemy 2.0 style: `DeclarativeBase`, `Mapped`, `mapped_column`,
  replacing `sqlalchemy.ext.declarative.declarative_base` at
  `older/banking/src/banking/models/__init__.py:1`, and removing the `cast(Column[int], ...)`
  calls at `api/transfers.py:117-118`. Use `select()` throughout; no `session.query(...)`
  should survive anywhere in the tree.
- **F39** Replace the `.count()` then `.all()`/`.first()` double queries
  (`api/customers.py:46`, `api/transfers.py:45,49`) with `.one_or_none()`.
- **F12** `api/customers.py:101-112` classifies constraint violations by matching driver
  error strings (`'customers.identifier' in str(error)`) and silently falls through
  without re-raising when neither matches, which returns an unbound local. Both are fatal
  under psycopg, whose wording differs from SQLite's. Inspect
  `error.orig.diag.constraint_name` instead, and re-raise on the fallthrough. The customer
  routes survive, so this applies where it was found **and** to the idempotency-key handling
  in Phase D, which needs the same insert-then-catch pattern and must be constraint-name
  based from the start. One shared helper, not two spellings of the same idea.

### C.6 `.gitlab-ci.yml` Postgres service

Add a `services: [ postgres:18-alpine ]` block plus the matching `POSTGRES_*` and
`BANKING_DATABASE_URL` variables to the `test` job, and have `test.sh` skip
`docker compose up` when `BANKING_DATABASE_URL` is already set, so the same script works
against the CI service container and against local compose.

### Verification (must pass before Phase D)

```shell
./automation/test.sh
```

from a clean checkout, with no manual setup beyond Docker and `uv` being installed. No
`--volumes` flag: the data directory is `tmpfs`, so stopping the container is already a
full reset, and a flag implying otherwise would mislead.

---

## Phase D: the ledger rewrite

The expensive phase. This is where `CLAUDE.md` is actually satisfied. It sits after the
Postgres migration deliberately: the locking semantics only exist on Postgres, and building
them twice would be waste.

### D.1 Schema (amend the Phase C initial revision in place)

- `customers(id, name)`, seeded with the four from the assignment.
- `accounts(id, customer_id, overdraft_limit_cents)`. **No `balance` column exists in the
  migration for a reviewer to find.**
- `entries(id BIGSERIAL, account_id, transfer_id NULL, type, amount_cents, created_at)`,
  append-only, with `id` as the monotonic integer that history orders by, and that a cursor
  would use if pagination were built.
- `transfers(id, idempotency_key UNIQUE, request_hash, created_at)`.
- Constraints a reviewer will look for by name: positive amount check, non-negative
  overdraft-limit check, foreign keys on entry account ids, unique index on the idempotency
  key, `NOT NULL` everywhere it matters.
- **F16** Timestamps are `DateTime(timezone=True)` with a server-side default, replacing
  `Integer` + `time.time()` at `older/banking/src/banking/models/transfers.py:14`.
- **F37** Customer ids become **server-generated** integers (`SERIAL`), with the migration
  seeding ids 1 to 4 for the four customers from the assignment. `POST /customers` no
  longer takes an identifier, which removes the old duplicate-identifier 409 path entirely.
  The migration inserts those four rows with **explicit** ids, so it must then advance the
  sequence (`setval`) past them; without that the first `POST /customers` collides on id 1.
  A test asserts that creating a customer against a freshly migrated database succeeds,
  because this is the kind of thing that only fails on a reviewer's clean clone.
  Account ids are server-generated UUIDs. The mixed id types are a stated choice, documented
  in one README line: customers are a small fixed reference set that the assignment
  enumerates, accounts are created by clients at runtime.
- **F48** Drop the UUID "collision risk" claim from the old README. The real trade-offs for
  a UUID primary key are index locality and storage size. Also note that
  `older/banking/src/banking/utilities.py:25`'s `uuid.UUID(x, version=4)` **coerces** the
  version field rather than validating it, so it never rejected a non-v4 UUID. The
  validation moves into the Pydantic schema as a `UUID` type and the helper goes away.

### D.2 Domain layer

`src/banking/domain/ledger.py`: **exactly one function writes ledger entries.** Deposit and
transfer both go through it, so no second code path can insert an entry and skip the
overdraft check. Entry type is a `StrEnum` and branching on it uses `match/case`.
`DEPOSIT` is exempt from the balance check and single-sided; `TRANSFER` is always balanced.

`src/banking/domain/transfers.py` reads top to bottom as: resolve accounts, reject self
transfer, lock both rows in ascending account id, check funding balance against
`-overdraft_limit_cents` inclusive, write balanced entries, return.

- **F4a** `older/banking/src/banking/api/transfers.py:101-125` reads the balance, decides
  in Python, then writes. Under Postgres `READ COMMITTED` that is a lost-update window. Add
  `.with_for_update()` on both account reads, and hold the lock from read to commit. Do not
  let a later `session.refresh` or a second unlocked query silently bypass it.
- The ascending-id lock ordering carries a one-line comment **naming** the A-to-B / B-to-A
  deadlock it prevents. Without the comment it reads as an accident.
- **F5** Reject a transfer where source and destination are the same account
  (`api/transfers.py:102-116` currently allows it).
- **Idempotency**: insert the transfer row first, catch `IntegrityError` on the unique key,
  then compare the stored request hash. Equal hash returns the original transfer unchanged;
  differing hash is a conflict. **No `SELECT` before the `INSERT`.** A comment records that
  insert-then-catch was chosen over `SELECT`-then-`INSERT` deliberately, and why.
- The replayed response is **reconstructed from the ledger**, not from a stored response
  body: the `transfers` row carries no amount or account columns, so the original outcome is
  rebuilt by reading the two `entries` rows that share its `transfer_id`. This is what makes
  a replay provably the same transfer rather than a remembered payload. The `request_hash`
  covers exactly the fields that determine those entries (source account, destination
  account, `amount_cents`), so a differing hash means a genuinely different transfer.
- The commit happens inside the handler's boundary, where a failure is still convertible to
  a typed domain error. This is what keeps an idempotency collision a 409 rather than a
  500. One database transaction per request, no network or external calls while it is open.
- Sync SQLAlchemy throughout. Async plus row locks plus concurrency tests is the wrong bet
  inside this timebox.

### D.3 API surface

Six routes, replacing the six existing ones. The customer routes survive (see **Decisions
taken**), so the surface is the four `CLAUDE.md` names plus the two the older tree already
had:

| Route | Replaces |
| --- | --- |
| `POST /customers` | `PUT /customers/` (query parameters) |
| `GET /customers` | `GET /customers/?customer_identifier=` |
| `POST /accounts` | `PUT /accounts/` (query parameters) |
| `POST /transfers` | `PUT /transfers/` (query parameters) |
| `GET /accounts/{id}/balance` | `GET /accounts/?account_identifier=` |
| `GET /accounts/{id}/transfers` | `GET /transfers/?account_identifier=` |

`GET /health` (F29) is a seventh path but is not part of the domain surface: it is an
operational probe, excluded from the endpoint table in the README for the same reason.

- **F23** All creates move from `PUT` with query parameters to `POST` with JSON bodies,
  using the request schemas that currently exist but are only used internally.
- **F10** Creates return `201` with a `Location` header, not `200`.
- `GET /customers` lists the reference set and drops the optional `customer_identifier`
  filter, so the one-route-two-behaviours shape (list, or filter-and-404) goes away. There
  is no `GET /customers/{id}`: a four-row seeded reference table does not need one, and the
  ported unknown-identifier 404 test retargets to `GET /accounts/{id}/balance`, where 404
  is load-bearing.
- Initial deposit is a ledger entry written through the one chokepoint, not a `balance`
  argument.
- `POST /accounts` takes `customer_id`, `initial_deposit_cents`, and an **optional**
  `overdraft_limit_cents` defaulting to `0`. Settable at creation and never afterwards:
  there is no update route, because a mutable limit needs an authorisation story this build
  does not have, and a limit that can only be set once is still enough to test the boundary
  behaviour that `CLAUDE.md` requires. The absence of an update route is a `Production`
  line, not silence.
- History is rendered **from the queried account's perspective**: direction, counterparty
  account id, signed effect on this account, entry id, timestamp. Not a raw entry dump.
  Ordered by entry id descending, with an explicit `order_by` and a **limit of 1000
  entries**, validated in the schema rather than clamped in the handler.
  **Pagination is cut** (see **Decisions taken**) and becomes a `Production` entry next to
  the balance-caching one, naming the cursor design that was not built: a cursor over the
  monotonic entry id, never an offset.
  **Superseded by Phase G, which is implemented.** The route is cursor-paginated, the 1000
  is a page-size maximum rather than a cap on reachable history, and the `Production` entry
  was deleted rather than written. This bullet is left standing only because it records the
  shape the ledger rewrite actually shipped with before Phase G replaced it.
  This absorbs **F15** (expose a timezone-aware
  timestamp and a direction field, and add the explicit `order_by` that
  `older/banking/src/banking/api/transfers.py:151-160` lacks entirely) and **F38** (the old
  `GET /transfers/` returned `200 []` for an unknown account while `GET /accounts/`
  returned 404; the new balance and history routes both 404 consistently).

### D.4 Errors and schemas

`src/banking/api/errors.py` maps the domain exception hierarchy to status codes in **one
table**, with a consistent body shape carrying a machine-readable `code`:

| Domain error | Status |
| --- | --- |
| unknown account | 404 |
| unknown customer | 404 (raised by `POST /accounts`, since `GET /customers` no longer filters) |
| insufficient funds | 422 |
| self transfer | 422 |
| invalid customer name | 422 |
| idempotency conflict | 409 |

- **Status codes are `fastapi.status` constants everywhere**, never literal integers: in
  the mapping table, in `Response`/`HTTPException` construction, in the per-route
  `responses={}` documentation, and in test assertions. The older tree already does this in
  `api/customers.py` and `api/transfers.py`; it is stated here so the rewrite does not
  quietly regress to `404`. Two greps enforce it, because the source and test spellings
  differ: `status_code=[0-9]` catches construction and `responses={` keys, and
  `status_code == [0-9]` catches assertions. Both are in the Phase D verification.

- **F10 (superseded in part)** The old codes were 417 for validation
  (`api/accounts.py:33`, `api/customers.py:91`) and 412 for insufficient funds
  (`api/transfers.py:109`). 417 becomes 422. 412 becomes **422**, not 409: `CLAUDE.md`
  assigns 409 to the idempotency conflict, which did not exist when the earlier note was
  written.
- **F22** `older/banking/src/banking/schemas/accounts.py:14-24` raises `HTTPException` from
  a Pydantic validator, so the schema layer imports FastAPI. Use `Field(ge=...)` or raise
  `ValueError`. Also drop the `FieldValidationInfo` import from the private
  `pydantic_core.core_schema` module.
- **F11** `older/banking/src/banking/schemas/transfers.py:19-21` writes
  `Field('Auto-generated UUID...')`, which passes the text as the field **default**, not as
  documentation. Use `description=`.
- **F31** Replace Pydantic v1 idioms: `.dict()` at `api/customers.py:99`,
  `api/accounts.py:71`, `api/transfers.py:124` becomes `.model_dump()`, and `class Config`
  in all three schema files becomes `model_config = ConfigDict(from_attributes=True)`.
- **F13** Drop `unique=True` on the customer name (`models/customers.py:9`). Two customers
  may share a name, and the ported `tests/test_customers.py` assertion inverts to say so.
  The old duplicate-name 409 path goes with it.
- **F14** `utilities.py:19-20` strips every non-alphabetic character from a customer name,
  silently turning `O'Brien` into `O Brien`. Replace lossy sanitization with
  validate-and-reject (422) plus Unicode NFC normalization, in the schema layer, so
  `utilities.py` disappears and `POST /customers` never stores a name the caller did not
  send.
- Money naming is explicit everywhere: `amount_cents`, `initial_deposit_cents`,
  `overdraft_limit_cents`, `balance_cents`. **No field named `amount` anywhere.**
  `grep -rn "float" src/` must return nothing; it is the first thing a reviewer greps for.
  Amount validation lives in the schema objects: positive integer, upper bound of
  **1_000_000_000_000 cents** (ten billion euro, comfortably inside `BIGINT` even after
  summing an account's entries), no coercion from string, so the domain layer receives
  already-valid input.
- OpenAPI carries request examples including the idempotency key, and documents the error
  responses per route, not just the success case (**F47**: the old per-endpoint
  `responses={}` documentation habit is kept and extended).
- **F29** Add `GET /health`. Structured JSON logging with a correlation id is listed as
  optional in **Budget risk**.

### Verification (must pass before Phase E)

```shell
./automation/test.sh
grep -rn "float" src/
grep -rn "session.query" src/
grep -rn "status_code=[0-9]" src/ tests/
grep -rn "status_code == [0-9]" tests/
```

with all four greps returning nothing, and the ported Phase B tests updated to the new
routes and passing.

---

## Phase E: extend test coverage (sequential step 3 of 3)

Business logic is tested against the domain and persistence layer directly, not only
through HTTP. **No mocked session in any of these**; anything asserting transactional or
concurrency behaviour needs the real engine.

`tests/test_transfers.py`
- insufficient funds
- the boundary case landing **exactly** on `-overdraft_limit_cents`, which must succeed
  (asserted in a named test, not merely implied by the comparison operator)
- self transfer rejected (**F7**)
- unknown account
- `amount_cents == 0` rejected (**F7**)
- rollback on failure: a failed transfer leaves no entries behind (**F7**)

`tests/test_idempotency.py`
- replay with the same key and the same payload returns the original transfer and leaves
  the entry count unchanged
- the same key with a different payload returns 409 and writes nothing

`tests/test_concurrency.py` (**F7**)
- two real sessions racing the same funding account where only one can succeed
- an opposing A-to-B / B-to-A pair run simultaneously, proving the lock ordering
- assertions are on **outcomes** and on the final balance never crossing the limit, not
  merely on the absence of an exception

`tests/test_replay.py`
- recompute every balance from the entry log independently and compare against what the
  balance endpoint reports
- value conservation: the sum of all balances is invariant across N randomised transfers
  (**F7**)

`tests/test_accounts.py`
- account creation writes exactly one `DEPOSIT` entry
- balance for an unknown account is 404
- history is ordered by entry id descending and is rendered from the queried account's
  perspective: the same transfer appears with opposite direction and opposite sign on the
  two accounts it touches

`tests/test_customers.py` (ported, adapted)
- two customers may share a name (the inverted F13 assertion)
- a name that cannot be stored losslessly is rejected with 422 rather than sanitized (F14)
- the four seeded customers from the assignment are present after `alembic upgrade head`

If the concurrency file ends up the weakest in the repo, the budget was misallocated.

### Verification

```shell
./automation/test.sh
```

with `fail_under = 85` in `[tool.coverage.report]` satisfied.

---

## Phase F: consolidate the two READMEs into one

Base: the existing root `README.md`, which already carries the `Production` entries on
derived balances and single-sided deposits and is already correct for the merged design.
Both older READMEs are folded into it.

### Carried over verbatim (still factually true after the merge)

- The FastAPI and uvicorn choice and its justification (both older READMEs argue it; the
  service version, which also names the `Flask`/`Django`/`aiohttp` alternatives, is the
  stronger text).
- The minor-units rationale, `older/banking/README.md:94-96`: integers because IEEE 754
  floats do not follow decimal quantization and would accumulate rounding errors (**F47**).
- The `Debian`-versus-`Alpine` base image argument from `older/service/README.md:92`: glibc
  rather than musl.
- The two-`RUN` layer split for fast local iteration.
- The `uv` packaging paragraph.
- The REST and Richardson Maturity Model framing, **rejustified** rather than copied: no
  Level 3 hypermedia because the clients are first-party and version-coupled, not "omitted
  for brevity" (**F44**).

### Revised because the merge changed the facts

- **F17** Three claims in `older/banking/README.md` that the code contradicts. All three
  must be rewritten, not carried: test dependencies excluded from the image (lines 128-130,
  false, the image installed `.[tests]`), dependencies pinned (lines 136-138, they were
  capped to a major version, not pinned; now there is a real lockfile), and transfers made
  safe by transactions (line 92, they were not, hence F4).
- **F45** "Incomplete CRUD" at `older/banking/README.md:65-66` becomes correct append-only
  ledger design: there is no `UPDATE` or `DELETE` because a ledger does not have them.
- The persistence section is rewritten from SQLite to Postgres (**F42**), naming the
  isolation level relied on: `READ COMMITTED` plus `SELECT ... FOR UPDATE`, why the funding
  account's own balance is the only cross-row invariant, the deterministic lock ordering,
  and what moving to `SERIALIZABLE` would cost (a retry loop and the reasoning that goes
  with it). The stored-balance justification that finding F42 anticipated is **not** written,
  because balances are now derived.
- The testing section is rewritten: `pytest` against a real Postgres, replacing
  "the tests are basic so I used built-in `unittest`". The old "100% coverage" line is
  dropped rather than restated.
- **F20** The five generic "Appendix A: Next Steps" bullets are replaced by specific, sized,
  ordered next steps.
- **F19** "Production Aspects" is rewritten in AWS-concrete terms and ordered as a roadmap,
  each entry saying what breaks first and roughly what closing it costs, correctness gaps
  ahead of conveniences. One `Deployment` paragraph names the concrete shape: **this
  container image on Lambda behind API Gateway, RDS Postgres, Alembic migrations as a
  one-off task in the deploy pipeline rather than at application startup, SQS for the
  outbox, IAM-scoped database credentials.**
- The `Production` roadmap gains three entries that this plan explicitly defers, each with
  what breaks first and what it costs:
  - **Connections.** `NullPool` pays a connection handshake per request, which is the price
    of not letting Lambda concurrency consume RDS `max_connections`. What breaks first is
    latency under sustained load, not correctness. Closing it means RDS Proxy (which pins a
    backend connection for the life of a `SELECT ... FOR UPDATE` transaction, so it recovers
    less than the marketing suggests) or moving to a long-lived container service such as
    ECS with a real pool. Sized at roughly a day either way.
  - **History pagination.** The list route returns at most 1000 entries, newest first, with
    no cursor. What breaks first is an account past 1000 entries, whose older history
    becomes unreachable. The design is written down and not built: a cursor over the monotonic
    entry id, never an offset, which makes ordering total and the tie-break question
    disappear. Roughly 25 minutes.
    **Phase G deleted this entry by building it**, and the README's `Production` items were
    renumbered accordingly. The roadmap must describe what the code actually omits, and a
    deferral that ships in the same submission reads as an oversight.
  - **Balance caching.** Balances are recomputed from the entry log on every read. What
    breaks first is read latency once an account has a long history. Closing it means a
    snapshot row plus the invalidation and reconciliation that go with it, and the replay
    test becomes the thing that proves the cache honest.
  - **Testcontainers**, once the suite is parallel, replacing the shared compose database.
- **F18** A "Known limitations and accepted risks" section naming: no auth (**F43**), no
  audit actor (**F40**), single currency, and no rate limiting.
- **F21** A short endpoint table, so the API is documented even when `/docs` is unreachable.
- **F46** One sentence each for single currency, integer minor units, the 1000-entry
  history limit, and the amount cap.
- An idempotency contract written for a **client** rather than a reviewer: generate one key
  per intended transfer, what a replay returns, what a conflict means, and why the
  uniqueness is enforced by the database.
- An error catalogue table: code, HTTP status, meaning.
- One sentence, not a theme, on what the replay test proves: balances recomputed
  independently from the entry log must agree with every balance the API reported.
- `sessions/` gets a short index naming what each session decided and what model output was
  rejected.

### Dropped, not carried

Everything in `older/service/README.md` that belongs to the phone-number assignment.

### Not restated, because it cannot be verified from this repo

- The live endpoint `https://*.execute-api.eu-central-1.amazonaws.com`.
  Belongs to the other assignment and to an account this repo does not describe. Dropped.
- The service README's file-by-file AI-use disclosure cannot be honestly re-derived for
  merged and rewritten files. It is **replaced by a short index of `sessions/`**, one line
  per file, which is what the current assignment actually asks for:
  `1_guardrails.md`, `2_assignment_diff.md`, `3a_decisions.md`, `3b_best_practices.md`,
  `3c_gaps_to_fix.md`. Each line names what that session decided and what model output was
  rejected. Dotfiles in `sessions/` are not listed.
- Any performance or coverage number from either README (the "100%" coverage claim, the
  `SHA-256` collision-probability comparison) is not restated without a measurement in this
  repo.

### Verification (Phase F)

```shell
grep -rn "older/" --exclude-dir=older --exclude-dir=.git . | grep -v Plan.md
```

returns nothing, plus a manual read of the README against the running service.

---

## Phase G: cursor pagination, as originally intended, and the 1000 cap lifted

This phase reverses a scope cut. Two earlier decisions are being undone, and it is worth
being precise about what they were, because the reversal has to reach every place they
propagated to.

**What was originally intended.** The working agreements first required transfer history to
use cursor pagination over a single monotonic integer key, the entry id, which makes the
ordering total and the tie-breaking question disappear: entries sharing a timestamp still
have distinct ids. The cursor is that id, never an offset. The plan carried that as a
`next_cursor` field in the response body, and the test coverage phase carried one assertion
for it: the cursor advances, and a page boundary neither drops nor duplicates an entry.

**How it was cut.** Pagination was dropped as a budget measure, sized at roughly 25 minutes,
with the instruction to record it in `Production` next to the balance-caching entry. The
route became a single fixed-limit page ordered by entry id descending. The working
agreements were then edited to match rather than left in disagreement with the plan, and a
concrete ceiling was chosen at the same time as two other unnamed numbers: history limit
**1000** entries, coverage `fail_under = 85`, amount cap **1_000_000_000_000 cents**. The
1000 was to be validated in the request schema rather than clamped in the handler.

**What this phase does.** Builds the cursor and lifts the cap. The 1000 stops being a
ceiling on reachable history and becomes at most a page-size guard. Every entry an account
has ever had is reachable by walking the cursor.

### G.1 Route shape

`GET /accounts/{id}/transfers` gains two query parameters, both validated in the request
schema rather than clamped in the handler, consistent with how the fixed limit was to be
handled:

- `limit`: page size, default `100`, minimum `1`, maximum `1000`. The maximum is a
  protection against one request materialising an unbounded result set. It is **not** a
  limit on how much history is reachable, which is the distinction this phase exists to
  draw, and the README must draw it in those words.
- `cursor`: an entry id, optional. Absent means start at the newest entry.

The response body gains `next_cursor`, alongside the existing list of history items.
`next_cursor` is `null` on the last page, which is how a client knows to stop rather than
having to issue one more request that comes back empty.

### G.2 Query

Ordering stays entry id descending, newest first. The page predicate is
`entry.id < cursor` when a cursor is supplied, and nothing when it is not.

Fetch `limit + 1` rows and return `limit` of them. If the extra row came back, its id is
`next_cursor`; if it did not, `next_cursor` is `null`. This is what makes the last page
detectable without a second query and without an extra round trip for the client.

Two properties are worth a comment in the code, because both are the reason a cursor was
specified over an offset and neither is obvious from reading the query:

- **Total ordering.** Entry ids are unique and monotonic, so a descending walk has no ties
  to break and needs no secondary sort key. Ordering by timestamp would need one, because
  entries written inside the same transaction can share a timestamp.
- **Stability under concurrent writes.** New entries always take ids greater than any
  existing cursor, so a transfer committed while a client is midway through paging cannot
  shift a page boundary, cannot cause an entry to be skipped, and cannot cause one to be
  returned twice. `OFFSET` has none of these properties: an insert at the head shifts every
  subsequent page by one and silently duplicates a row across the boundary.

Nothing about the transaction boundary changes: a history read is one read-only transaction
per request as before, with no lock taken.

### G.3 Tests

Restore the assertion that was cut, and add the ones that specifically protect the lifted
cap. These go in `tests/test_accounts.py`, next to the existing history-ordering test:

- Paging with `limit=N` over `2N+1` entries yields three pages whose concatenation is
  exactly the single-page ordering: nothing dropped, nothing duplicated, order preserved.
- `next_cursor` is `null` on the final page and non-null on every page before it.
- An entry written **after** the first page is fetched does not appear on any later page and
  does not shift the boundary. This is the assertion that would fail under offset
  pagination, so it is the one that earns the design.
- **The cap is lifted**: an account with more than 1000 entries can have all of them
  reached by walking the cursor. Seed past the old ceiling and assert the walk returns every
  entry. This test fails if anyone reintroduces a total-history limit, which is the point of
  writing it.
- `limit` above the maximum, `limit` below 1, and a non-integer cursor are each rejected at
  the schema with 422, not clamped silently.
- A cursor pointing at an entry belonging to another account returns that account's page
  correctly filtered, so the cursor cannot be used to read across accounts.

The last one matters more than its size suggests: a cursor is client-supplied input into a
`WHERE` clause, and the account filter has to be applied independently of it.

### G.4 Documents this phase owns

The cut propagated to six places. All six move together, or the submission contradicts
itself:

1. **The working agreements** (`CLAUDE.md`, the transfer-history clause). It currently states
   that history is not paginated in this build and that the route returns up to 1000 entries
   newest first, with the entry id named as the cursor a later pass would use. That is now
   false. Restore the original requirement: cursor pagination over the monotonic entry id,
   the cursor is the id and never an offset. The precedent set earlier in this repo is that
   the agreements move so the two documents agree, rather than the plan carrying a knowing
   gap, and the same precedent applies in this direction.
2. **Phase D's history bullet**, which says pagination is cut. Superseded, and marked as
   such in place.
3. **The `Production` roadmap entry** for history pagination. Deleted, not reworded. A
   roadmap entry describing something that shipped in the same submission converts a
   decision into an oversight, which is exactly the failure mode the roadmap exists to
   avoid.
4. **The README limitations sentence** that names the 1000-entry history limit alongside
   single currency and integer minor units. It becomes a sentence about page size and the
   cursor contract instead, and must not leave a reader thinking history is capped.
5. **The README endpoint table**, which gains `limit` and `cursor` on the history route and
   `next_cursor` in its response.
6. **`Decisions taken` entries 8 and 9.** Entry 8 records pagination as cut; it is rewritten
   to record that it was cut and then restored, with the reason for each move, because a
   reviewer reading the session logs will see both and an unexplained reversal reads worse
   than either decision. Entry 9's history-limit number changes meaning from a total cap to
   a page-size maximum; the other two numbers in it are untouched.

Also update the comparison table near the top of this plan, whose History row currently
reads "ordered by entry id, per-account perspective". It returns to "cursor-paginated,
per-account perspective".

The client-facing README section gains one paragraph: how to page (omit the cursor, then
follow `next_cursor` until it is null), and one sentence on why the cursor is an id rather
than an offset. Written for a client, in the same register as the idempotency contract.

### G.5 Ordering note

Phase G is written as a separate phase because that is how it was asked for, and it is
independently committable that way. It is cheaper if it is not: building the paginated shape
directly in Phase D costs the same 25 minutes it was sized at, while doing it here means
writing the fixed-limit route, its README paragraph and its `Production` entry first and
then unwinding all three. If Phase G is confirmed as in scope before implementation starts,
fold G.1 and G.2 into Phase D, fold G.3 into Phase E, and let Phase F write the paginated
story once. Phase G then reduces to G.4 with nothing to undo. The phase is kept separate
here so that the decision stays visible and reversible.

### Verification (Phase G)

```shell
./automation/test.sh
```

with the new pagination tests passing and `fail_under = 85` still satisfied, plus:

```shell
grep -rn "1000" src/ README.md CLAUDE.md
```

reviewed by eye: every surviving occurrence must be the page-size maximum, and none may be
a limit on total reachable history.

---

## The Lambda deployment shape

**Decided: the first deployment is this container image on Lambda behind API Gateway, and
the connection ceiling is paid for with `NullPool`.**

The tension is narrower than it first looks, and it is worth being precise about where it
actually is:

- **Not** with the container Postgres. That compose service is a local-development and test
  fixture and is never deployed. In production the database is RDS. There is no conflict to
  resolve.
- **Not** with per-request transactions. A Lambda invocation maps cleanly onto one HTTP
  request and therefore onto one database transaction. Nothing about the transaction
  boundary changes.
- **The real tension is connection management.** Lambda scales by adding execution
  environments, each holding its own SQLAlchemy pool. `max_connections` on RDS is
  consumed by concurrency, not by throughput, so a spike that would be unremarkable on a
  fixed fleet exhausts the database. The mitigations are RDS Proxy, or `NullPool` and
  paying connection setup per request, and both are real costs.
- A second, smaller tension: `SELECT ... FOR UPDATE` holds a lock for the duration of the
  request, and a cold start inside that window lengthens the hold. Not disqualifying, but
  it points the same direction.

**The resolution for this build is `NullPool`.** It is one argument to `create_engine`, it
makes connection count a function of in-flight requests rather than of environment count,
and it cannot fail in a way that surprises a reviewer. The cost is a TCP, TLS and Postgres
auth handshake on every request, which is real but is latency, not correctness. RDS Proxy
is the alternative and is deliberately not taken: it is another component, another failure
domain, an hourly cost, and it pins a backend connection for the duration of a locked
transaction, which is exactly the pattern this service is built out of.

The README says this in about four sentences: the image runs unchanged locally and on
Lambda; `NullPool` is what makes Lambda safe against RDS today; the handshake per request
is the accepted cost; and sustained concurrency is the signal to move to RDS Proxy or to a
long-lived container service such as ECS, which is written up in `Production` with what it
would cost. Naming the trigger is what makes it a decision rather than an oversight.

---

## Budget risk

**This plan does not fit in 4 hours, and the overrun is authorized.** Honest estimate,
assuming no debugging surprises:

| Phase | Estimate |
| --- | --- |
| A: structure, tooling, Dockerfile, CI | 40 to 60 min |
| B: unittest to pytest | 25 to 40 min |
| C: Postgres, compose, Alembic, session wiring | 60 to 75 min |
| D: **ledger rewrite** (schema, domain, six routes, error table, schemas) | 125 to 185 min |
| E: concurrency, idempotency, replay tests | 60 to 90 min |
| F: README consolidation | 45 to 60 min |
| G: cursor pagination and the lifted cap | 40 to 55 min |
| **Total** | **6.75 to 9.5 hours** |

Phase G is sized above the 25 minutes the pagination cut was originally scored at, because
that figure covered the route and its test only. The extra covers the six document sites the
cut propagated to and the two tests that specifically protect the lifted cap. Folding G into
Phases D and E as described in G.5 brings it back close to the original 25.

Three decisions moved these numbers from the previous draft. Phase A comes down because the
tooling configuration is carried over from work already done rather than derived. Phase D
goes up by about 30 minutes for the surviving customer routes and down by about 25 for the
cut pagination, so it roughly holds. Phase D alone is still most of a 4-hour budget, and it
is non-negotiable because it is what `CLAUDE.md` requires.

What remains at risk, in the order I would look at it if the estimate proves optimistic:

1. **The Phase B port may be partly wasted work.** The ported tests exercise the
   stored-balance API and a good portion of them are rewritten in Phase D. The counter is
   that they are the only green suite guarding the Postgres swap in Phase C, which is worth
   more than the 25 minutes they cost. Kept for that reason.
2. **The Phase C Alembic revision is written twice**, once for the stored-balance schema
   and once amended for the ledger. Amending is cheap and `CLAUDE.md` endorses it, but it
   is roughly 15 minutes of rework that a different phase order would avoid at the cost of
   breaking the green-suite-per-phase rule.
3. **Optional items already parked**, none of which are in the estimate above: an
   `app.openapi()` snapshot test, structured JSON logging with a correlation id, a
   digest-pinned base image, and an `initiated_by` actor column on transfers. All four go
   in `Production` as named gaps rather than being half-built.

---

## Decisions taken

Every open question from the previous draft is now answered. Recorded here because several
of them are visible in the finished repo and a reviewer will wonder whether they were
chosen or defaulted into.

1. **The ledger rewrite is accepted as a rewrite.** The stored-balance design has no
   incremental path to double-entry with derived balances, and no attempt is made to fake
   lineage where none exists.

2. **The customer routes survive.** `POST /customers` and `GET /customers` stay, adapted to
   JSON bodies and server-generated ids. This keeps findings F12, F13, F14 and F37 live, at
   roughly 30 minutes and two extra routes for a reviewer to read past.

3. **Package name is `banking`**, carried from the older tree, so `src/banking/`. The
   environment variable follows it: `BANKING_DATABASE_URL`.

4. **Deployment is a container on Lambda behind API Gateway, not ECS.** `NullPool` for the
   first iteration; `Production` records what would trigger RDS Proxy or a move to ECS.
   `CLAUDE.md` has been updated to match, so the plan and the working agreements do not
   disagree.

5. **HTTP status codes are `fastapi.status` constants, never literal integers**, in source
   and in tests. Also now in `CLAUDE.md`, and grep-verified at the end of Phase D.

6. **Postgres comes from `compose.yaml`**, not testcontainers. Testcontainers is a
   `Production` line for when the suite goes parallel.

7. **The CI pipeline stays, with its `test` job cut to `./automation/test.sh`**, so there is one
   definition of how the suite runs and CI cannot drift from it.

8. **Cursor pagination was cut, and is now restored.** It was dropped as a budget measure,
   sized at 25 minutes, and written up as a `Production` entry next to balance caching, with
   `CLAUDE.md` edited to match so the two documents did not disagree. **Phase G reverses
   that**, and is implemented: the cursor is built, the 1000-entry ceiling is lifted, the
   `Production` entry is deleted rather than reworded, and `CLAUDE.md` has moved back to
   requiring the cursor. The reversal is recorded rather
   than tidied away, because both states are visible in the session logs and an unexplained
   flip reads worse than either decision.

9. **The three unnamed numbers are named**: coverage `fail_under = 85`, history limit
   **1000** entries, amount cap **1_000_000_000_000 cents**. Each is written where it is
   enforced (pyproject, the history request schema, the amount schema), not repeated in
   prose, so there is one place to change each. **Phase G changes what the 1000 means**: it
   stops being a cap on reachable history and becomes the maximum page size, with a default
   of 100. The other two numbers are unaffected.

10. **Phase A ships as two commits**, `git mv` first with no content change, the ruff
    reformat second, so the rename diff stays readable and `git log --follow` keeps working.

11. **The README AI-use disclosure becomes a short index of the five non-dot files in
    `sessions/`**, one line each, rather than a file-by-file attribution that cannot be
    honestly reconstructed after merging and rewriting.

12. **`older/service/README.md` is not moved.** The root `README.md` already carries
    CLAUDE-aligned text and is the consolidation target; `git mv -f` over it would destroy
    that. This is the one knowing exception to the "everything arrives by `git mv`" rule.

**Assumptions carried forward (confirmed):**

13. **`CLAUDE.md` beats the older rework notes wherever they conflict.** The consequential
    cases: balances are derived, not a stored column; insufficient funds is 422, not 409
    (409 is the idempotency conflict); the idempotency key is a body field, not an
    `Idempotency-Key` header; and there is no `GET /accounts/{id}` or `GET /transfers/{id}`.
    The deployment shape is the one exception, and it was resolved by changing `CLAUDE.md`
    rather than by ignoring it.

14. **Postgres 18 on the `alpine` image, with a `tmpfs` data directory.** Throwaway state,
    faster suite, and it forces the "schema comes only from Alembic" rule to be true rather
    than aspirational.

15. **Python 3.13**, following the service tree, up from the banking tree's 3.12.

16. **Test isolation is truncate-and-reseed per test, not a rolled-back transaction**, since
    a rollback fixture cannot host the concurrency tests.

17. **`older/` is left entirely intact.** No file inside it is deleted by this plan, only
    moved out of it. The Phase F verification proves nothing outside `older/` still
    references a path inside it.

18. **No Makefile, no pre-commit, no Terraform, no second currency, no repository
    abstraction with one implementation.** Scope discipline is itself a deliverable, and
    anything cut becomes a `Production` line rather than silence.
