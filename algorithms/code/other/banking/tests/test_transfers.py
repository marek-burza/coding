import uuid

from fastapi import status
from fastapi.testclient import TestClient
from sqlalchemy import func, select
from sqlalchemy.orm import Session

from banking.models import Entry
from conftest import balance_cents, open_account, transfer


def test_successful_transfer_moves_the_balance(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    status_code, body = transfer(client, source, destination, 5000)
    assert status_code == status.HTTP_201_CREATED
    assert body["amount_cents"] == 5000
    assert balance_cents(client, source) == 5000
    assert balance_cents(client, destination) == 5001


def test_transfer_is_rejected_for_insufficient_funds(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    status_code, body = transfer(client, source, destination, 20000)
    assert status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
    assert body["code"] == "insufficient_funds"
    assert balance_cents(client, source) == 10000


def test_transfer_landing_exactly_on_the_overdraft_limit_succeeds(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=1000, overdraft_limit_cents=500)
    destination = open_account(client, initial_deposit_cents=1)
    status_code, _ = transfer(client, source, destination, 1500)
    assert status_code == status.HTTP_201_CREATED
    assert balance_cents(client, source) == -500


def test_transfer_one_cent_past_the_overdraft_limit_is_rejected(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=1000, overdraft_limit_cents=500)
    destination = open_account(client, initial_deposit_cents=1)
    status_code, body = transfer(client, source, destination, 1501)
    assert status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
    assert body["code"] == "insufficient_funds"
    assert balance_cents(client, source) == 1000


def test_self_transfer_is_rejected(client: TestClient) -> None:
    account_id = open_account(client, initial_deposit_cents=10000)
    status_code, body = transfer(client, account_id, account_id, 100)
    assert status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
    assert body["code"] == "self_transfer"
    assert balance_cents(client, account_id) == 10000


def test_transfer_from_unknown_account_is_404(client: TestClient) -> None:
    destination = open_account(client, initial_deposit_cents=1)
    status_code, body = transfer(client, uuid.uuid4(), destination, 100)
    assert status_code == status.HTTP_404_NOT_FOUND
    assert body["code"] == "unknown_account"


def test_transfer_to_unknown_account_is_404(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    status_code, body = transfer(client, source, uuid.uuid4(), 100)
    assert status_code == status.HTTP_404_NOT_FOUND
    assert body["code"] == "unknown_account"


def test_zero_and_negative_amounts_are_rejected(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount_cents in (0, -100):
        status_code, body = transfer(client, source, destination, amount_cents)
        assert status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
        assert body["code"] == "invalid_request"


def test_amount_is_not_coerced_from_a_string(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    response = client.post(
        "/transfers",
        json={
            "source_account_id": str(source),
            "destination_account_id": str(destination),
            "amount_cents": "5000",
            "idempotency_key": str(uuid.uuid4()),
        },
    )
    assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT


def test_failed_transfer_leaves_no_entries_behind(
    client: TestClient, session: Session
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    before = session.execute(select(func.count()).select_from(Entry)).scalar_one()
    assert transfer(client, source, destination, 20000)[0] == (
        status.HTTP_422_UNPROCESSABLE_CONTENT
    )
    after = session.execute(select(func.count()).select_from(Entry)).scalar_one()
    assert after == before


def test_transfer_writes_two_entries_summing_to_zero(
    client: TestClient, session: Session
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    _, body = transfer(client, source, destination, 2500)
    entries = list(
        session.execute(
            select(Entry).where(Entry.transfer_id == uuid.UUID(str(body["id"])))
        )
        .scalars()
        .all()
    )
    assert len(entries) == 2
    assert sum(entry.amount_cents for entry in entries) == 0
