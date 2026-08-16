import uuid

from fastapi import status
from fastapi.testclient import TestClient
from sqlalchemy.orm import Session

from banking import database
from banking.domain import IdempotencyConflict, transfers
from conftest import (
    balance_cents,
    count_entries,
    open_account,
    open_account_directly,
)


def test_replaying_a_key_returns_the_original_transfer(
    client: TestClient, session: Session
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    body = {
        "source_account_id": str(source),
        "destination_account_id": str(destination),
        "amount_cents": 2500,
        "idempotency_key": str(uuid.uuid4()),
    }

    first = client.post("/transfers", json=body)
    assert first.status_code == status.HTTP_201_CREATED
    entries_after_first = count_entries(session)

    replay = client.post("/transfers", json=body)
    assert replay.status_code == status.HTTP_201_CREATED
    assert replay.json() == first.json()
    assert count_entries(session) == entries_after_first
    assert balance_cents(client, source) == 7500
    assert balance_cents(client, destination) == 2501


def test_replaying_a_key_with_a_different_payload_is_a_conflict(
    client: TestClient, session: Session
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    key = str(uuid.uuid4())
    body = {
        "source_account_id": str(source),
        "destination_account_id": str(destination),
        "amount_cents": 2500,
        "idempotency_key": key,
    }

    assert client.post("/transfers", json=body).status_code == (status.HTTP_201_CREATED)
    entries_after_first = count_entries(session)

    conflict = client.post("/transfers", json={**body, "amount_cents": 9999})
    assert conflict.status_code == status.HTTP_409_CONFLICT
    assert conflict.json()["code"] == "idempotency_conflict"
    assert count_entries(session) == entries_after_first
    assert balance_cents(client, source) == 7500


def test_a_different_destination_under_the_same_key_is_a_conflict(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    elsewhere = open_account(client, initial_deposit_cents=1)
    key = str(uuid.uuid4())
    body = {
        "source_account_id": str(source),
        "destination_account_id": str(destination),
        "amount_cents": 2500,
        "idempotency_key": key,
    }

    assert client.post("/transfers", json=body).status_code == (status.HTTP_201_CREATED)
    conflict = client.post(
        "/transfers", json={**body, "destination_account_id": str(elsewhere)}
    )
    assert conflict.status_code == status.HTTP_409_CONFLICT
    assert balance_cents(client, elsewhere) == 1


def test_replay_against_the_domain_layer_directly(session: Session) -> None:
    source = open_account_directly(initial_deposit_cents=10000)
    destination = open_account_directly(initial_deposit_cents=1)
    key = str(uuid.uuid4())

    def execute(amount_cents: int) -> transfers.TransferResult:
        with database.Session() as own_session:
            result = transfers.execute(
                own_session,
                source_account_id=source,
                destination_account_id=destination,
                amount_cents=amount_cents,
                idempotency_key=key,
            )
            own_session.commit()
            return result

    first = execute(2500)
    entries_after_first = count_entries(session)
    replayed = execute(2500)

    assert replayed == first
    assert count_entries(session) == entries_after_first

    try:
        execute(9999)
    except IdempotencyConflict:
        pass
    else:  # pragma: no cover
        raise AssertionError("A differing payload must raise IdempotencyConflict")
    assert count_entries(session) == entries_after_first
