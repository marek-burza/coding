import uuid
from collections.abc import Iterator
from typing import Any

import pytest
from fastapi import status
from fastapi.testclient import TestClient
from sqlalchemy import func, inspect, select, text
from sqlalchemy.orm import Session

from banking import database, main, models
from banking.domain import ledger

ALEMBIC_TABLE = "alembic_version"

SCHEMA_MISSING = (
    "The database at BANKING_DATABASE_URL has no schema. Migrations are "
    "test.sh's job, not a fixture's: run ./automation/test.sh rather than pytest "
    "directly, or run `uv run alembic upgrade head` first."
)

SEEDED_CUSTOMERS = (
    "John Doe",
    "Jack Smith",
    "Jane Taylor",
    "Jade Wilson",
)


@pytest.fixture(scope="session", autouse=True)
def schema() -> None:
    tables = set(inspect(database.engine).get_table_names())
    assert ALEMBIC_TABLE in tables, SCHEMA_MISSING
    missing = {table.name for table in models.Base.metadata.sorted_tables} - tables
    assert not missing, f"{SCHEMA_MISSING} Missing: {sorted(missing)}"


@pytest.fixture(autouse=True)
def clean_database(schema: None) -> Iterator[None]:
    yield
    names = ", ".join(table.name for table in models.Base.metadata.sorted_tables)
    with database.engine.begin() as connection:
        connection.execute(text(f"TRUNCATE {names} RESTART IDENTITY CASCADE"))
        for name in SEEDED_CUSTOMERS:
            connection.execute(
                text("INSERT INTO customers (name) VALUES (:n)"), {"n": name}
            )


@pytest.fixture
def session() -> Iterator[Session]:
    with database.Session() as db_session:
        yield db_session


@pytest.fixture
def client() -> Iterator[TestClient]:
    with TestClient(main.app) as test_client:
        yield test_client


def count_entries(session: Session) -> int:
    session.expire_all()
    return int(
        session.execute(select(func.count()).select_from(models.Entry)).scalar_one()
    )


def open_account_directly(
    initial_deposit_cents: int,
    overdraft_limit_cents: int = 0,
    customer_id: int = 1,
) -> uuid.UUID:
    with database.Session() as session:
        account = ledger.open_account(
            session,
            customer_id=customer_id,
            initial_deposit_cents=initial_deposit_cents,
            overdraft_limit_cents=overdraft_limit_cents,
        )
        session.commit()
        return account.id


def open_account(
    client: TestClient,
    initial_deposit_cents: int,
    overdraft_limit_cents: int = 0,
    customer_id: int = 1,
) -> uuid.UUID:
    response = client.post(
        "/accounts",
        json={
            "customer_id": customer_id,
            "initial_deposit_cents": initial_deposit_cents,
            "overdraft_limit_cents": overdraft_limit_cents,
        },
    )
    assert response.status_code == status.HTTP_201_CREATED
    return uuid.UUID(response.json()["id"])


def transfer(
    client: TestClient,
    source: uuid.UUID,
    destination: uuid.UUID,
    amount_cents: int,
    idempotency_key: str | None = None,
) -> tuple[int, dict[str, object]]:
    response = client.post(
        "/transfers",
        json={
            "source_account_id": str(source),
            "destination_account_id": str(destination),
            "amount_cents": amount_cents,
            "idempotency_key": idempotency_key or str(uuid.uuid4()),
        },
    )
    return response.status_code, response.json()


def history_page(
    client: TestClient,
    account_id: uuid.UUID,
    limit: int | None = None,
    cursor: int | None = None,
) -> dict[str, Any]:
    params = {
        name: value
        for name, value in (("limit", limit), ("cursor", cursor))
        if value is not None
    }
    response = client.get(f"/accounts/{account_id}/transfers", params=params)
    assert response.status_code == status.HTTP_200_OK
    return dict(response.json())


def walk_history(
    client: TestClient, account_id: uuid.UUID, limit: int
) -> list[dict[str, Any]]:
    entries: list[dict[str, Any]] = []
    cursor: int | None = None
    while True:
        page = history_page(client, account_id, limit=limit, cursor=cursor)
        entries.extend(page["entries"])
        cursor = page["next_cursor"]
        if cursor is None:
            return entries


def balance_cents(client: TestClient, account_id: uuid.UUID) -> int:
    response = client.get(f"/accounts/{account_id}/balance")
    assert response.status_code == status.HTTP_200_OK
    return int(response.json()["balance_cents"])
