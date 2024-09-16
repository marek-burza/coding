# Banking API

## Running

To start the app (`Docker` is required for this to work), run:

```bash
/bin/sh run.sh
```

To populate the `customers` table (`curl` is required for this to work), run:

```bash
/bin/sh populate.sh
```

To review the OpenAPI documentation and to interact with the API go to [http://localhost/](http://localhost/).

## Decisions

### Persistent Storage: SQLite & SQLAlchemy

Given that it is a banking app, it makes sense to opt here for a relational database
because of the strong consistency they offer. In addition to this the solution can then rely on `SQL`
for interaction with the data and for transactions which can ensure consistency of the account balances.

Since the implementation for this assignment is meant to stand on its own I picked `SQLite`
as a simple relational database embodiment. Furthermore, I picked [`SQLAlchemy`](https://github.com/sqlalchemy/sqlalchemy)
Python library to abstract away the interface to database used (which allows for an easy path to swap out
`SQLite` for `MySQL`, `PostgreSQL` or other relational database) and to leverage
[Object-Relational Mapping](https://en.wikipedia.org/wiki/Object%E2%80%93relational_mapping)
and ease API implementation.

### Frameworks: FastAPI & uvicorn

The two combinations of API framework & HTTP server framework I am familiar with are:

- [`FastAPI`](https://github.com/fastapi/fastapi) & [`uvicorn`](https://github.com/encode/uvicorn)
- [`Connexion`](https://github.com/spec-first/connexion) & [`aiohttp`](https://github.com/aio-libs/aiohttp)

There are certainly more frameworks and their combinations available, but I chose `FastAPI` & `uvicorn`
given their maturity on one hand and simplicity of the code using it on the other.

Given the time constraints it also appealed to me that with `FastAPI` I could rely on a single source
of truth for both the API specification/documentation and its implementation - by annotating
the API implementation I could let the framework derive the specification/documentation from it
(which then also be used for e.g. generation of the frontend code).

Note: The API was meant to be consumed by multiple frontends (web, iOS, Android etc.) - and though I picked
OpenAPI specification format I am well aware of the alternatives which could also be applicable for use
by a variety of frontends (e.g. GraphQL).

## Design

The organization of the API follows the [REST](https://en.wikipedia.org/wiki/REST) guidelines (in particular:
stateless approach, focus on URI-based representation of resources, etc.)
and [Richardson Maturity Model](https://en.wikipedia.org/wiki/Richardson_Maturity_Model) where:

1. Level 1 - resources (`customers`, `accounts` & `transfers`) are mapped to their respective endpoints
2. Level 2 - PUT & GET HTTP verbs are utilized on those endpoints to provide "create" & "read" operations
3. Level 3 - was omitted for brevity

Considering the assignment specification, the functionality is fully covered (plus a couple additions
to be able to add customers to the empty database).
However, to have a robust [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete)
functionality, the update and deletion of entries would need to be implemented/added still.

While the customer identifier was specified as an integer in the assignment description,
the account identifier was not. I opted to use a more frequently applied UUID for the account identifier.
While UUID still carries a risk of collision I did not provide a solution for that issue,
that is because in a real-world scenario, where a more robust relational database would be used,
we could rely on the unique identifiers automatically assigned by the database.

Where possible I relied on validation supplied by `FastAPI` and the schema I supplied it with.
Aside from this I implemented additionally:

Validation of:

- UUID account identifiers

Sanitization of:

- Customer name - all special characters are removed (and only spaces and letters of broadly understood alphabets remain)

I implemented also a number of other safeguards, e.g.:

- To create an account for a customer, the customer must exist
- The balance (neither the initial one nor subsequent after transfer) cannot be less than zero
- The transfer amount must be greater than zero
- To execute a transfer, both source and destination accounts must exist

The more complex operations (such as transfer of funds) are ensured to be atomic by application of transactions.

I opted for balance & transfer amounts to be integers (with 1 corresponding to the smallest monetary unit).
That is because floating point values (as encoded with IEEE 754 standard) do not follow decimal quantization
which would eventually lead to rounding errors.

Code repeatedly used in various spots was extracted as separate functions
(i.e. `query_and_verify_customer_identifier`, `validate_account_identifier`, `query_and_verify_account_identifier`)
in the interest of readability and to follow the [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) principle.

## Production Aspects

### Structure & Packaging

The code was structured following the typical template used for modern Python packages - with implementation
code residing in the `src` directory and test code in the `tests` directory. Furthermore, the code was broken up
to separate different concerns:

- [`main.py`](./src/banking/main.py) is the main entry point for the application - it starts up the `uvicorn` server
  (binding it to port 80), couples it with `FastAPI` API routes (while also providing a redirect of `/` URL
  to the OpenAPI documentation & UI)
- [`database.py`](./src/banking/database.py) establishes the connection to the `SQLite` database
- three directories: [`api`](./src/banking/api/) (which holds the implementation of the HTTP API endpoints
  interacting with the database), [`models`](./src/banking/models/) (which implements the classes mapped to the database entries),
  and [`schemas`](./src/banking/schemas/) (which define the classes of objects the API operates on)
- in each of the three directories there are three files corresponding to the three entities the API
  operates on: `customers`, `accounts` and `transfers`.

Packaging is covered here in two ways:

- On one hand the code is sufficiently complete to act as a Python package which can be installed
  with `pip` and/or published as a `wheel` file. This is covered by the [`pyproject.toml`](./pyproject.toml) file
  which encompasses specification of package metadata & dependencies, and configuration of quality-related
  tooling (linting / testing).
- On the other hand the code is packaged as a Docker container image which is the current de-facto
  standard packaging for server app distribution (e.g. Kubernetes, ECS, Lambda). In this context,
  Alpine-based Python Docker image was selected to ensure small Docker container image size (and thus
  its faster distribution and start-up time). Additionally, only the implementation of the API
  is packaged with its dependencies (rather than also including the testing code and its dependencies too).
  One can also consider this as a best practice for maintaining hygiene of the development environment
  (in particular its repeatability and restriction to just the bare necessities).

### Dependencies

All dependencies (including linting/testing packages) are pinned to major version to prevent breaking changes
from entering the build, yet providing flexibility for the most recent minor & patch versions to be picked up
to result with builds with most recent functionality & bug/security fixes included.

### Linting

In the interest of maintainability and to ensure basic code quality level as well as to prevent common pitfalls
the code is a subject to linting.
To have a broad coverage of various aspects, a combination of tools is applied:

- [`isort`](https://github.com/PyCQA/isort) (to standardize how imports are formatted for an easy overview),
- [`black`](https://github.com/psf/black) (to enforce a common code style),
- [`flake8`](https://github.com/PyCQA/flake8) & [`mypy`](https://github.com/python/mypy)
  (to ensure type hint consistency and basic code quality)
- and finally [`bandit`](https://github.com/PyCQA/bandit) (to flag common security issues,
  since it is potentially an internet-facing API).

### Testing

The implemented [tests](./tests/) are quite basic, thus I opted for the built-in `unittests` rather than the more sophisticated `pytest`.

The implemented tests are focused on the corner cases associated with the aforementioned input sanitization,
validation and verification, as well as some basic transfer integrity checks.

Besides mere testing, coverage is reported as well (and though the coverage is at 100% I would caution anyone
that this does not mean the API exhaustively tested).

Similarly to the API as well as schemas & models, also tests are organized around the endpoints.

### CI/CD

The first three steps are grouped into convenience scripts:

- [`automation/1.build.sh`](./automation/1.build.sh) builds the Docker container image
- [`automation/2.lint.sh`](./automation/2.lint.sh) & [`automation/3_test.sh`](./automation/3.test.sh) run linting & testing respectively,
  and uses the Docker container image built earlier
  (to ensure uniformity of the environment between linting/testing & production runtime environments)

Note: All of the scripts feature `set -e` to ensure the script exits upon the first failure.

Naturally, the next steps belonging to the "CD" portion of the chain are not included, since that would depend
on the specifics of the artifact store to be used and the nature of the compute infrastructure &
infrastructure-as-a-code applied.

## Appendix A: Original Assignment

### Objective

Your assignment is to build an internal API for a financial institution using Python.

### Brief

While modern banks have evolved to serve a plethora of functions, at their core,
banks must provide certain basic features. Today, your task is to build the basic HTTP API
for one of those banks. Imagine you are designing a backend API for bank employees.
It could ultimately be consumed by multiple frontends (web, iOS, Android etc).

### Tasks

- Implement assignment using:
  - Language: Python
- There should be API routes that allow them to:
  - Create a new bank account for a customer, with an initial deposit amount.
    A single customer may have multiple bank accounts.
  - Transfer amounts between any two accounts, including those owned by
    different customers.
  - Retrieve balances for a given account.
  - Retrieve transfer history for a given account.
- Write tests for your business logic.

You are expected to design any other required models and routes for your API.

### Evaluation Criteria

- Python best practices
- Completeness: did you complete the features?
- Correctness: does the functionality act in sensible, thought-out ways?
- Maintainability: is it written in a clean, maintainable way?
- Testing: is the system adequately tested?
- Documentation:
  - is the API well-documented?
  - are design choices well-explained?

## Appendix B: Next Steps

- Use MySQL or PostgreSQL instead of SQLite
- Provide basic service setup for Kubernetes
- Introduce a cleaner separation between application layers (e.g. API & DB handling)
- Provide unit as well as integration tests
- Review industry standards for REST interfaces and apply best practices
