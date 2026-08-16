import uuid

from fastapi import status
from fastapi.testclient import TestClient
from sqlalchemy import insert, select
from sqlalchemy.orm import Session

from banking.models import Entry, EntryType
from conftest import (
    balance_cents,
    history_page,
    open_account,
    transfer,
    walk_history,
)


def test_account_creation_writes_exactly_one_deposit_entry(
    client: TestClient, session: Session
) -> None:
    account_id = open_account(client, initial_deposit_cents=10000)
    entries = list(
        session.execute(select(Entry).where(Entry.account_id == account_id))
        .scalars()
        .all()
    )
    assert len(entries) == 1
    assert entries[0].type == EntryType.DEPOSIT
    assert entries[0].amount_cents == 10000
    assert entries[0].transfer_id is None
    assert balance_cents(client, account_id) == 10000


def test_account_creation_returns_201_with_a_location(client: TestClient) -> None:
    response = client.post(
        "/accounts", json={"customer_id": 1, "initial_deposit_cents": 500}
    )
    assert response.status_code == status.HTTP_201_CREATED
    assert response.headers["Location"] == f"/accounts/{response.json()['id']}/balance"


def test_account_creation_defaults_the_overdraft_limit_to_zero(
    client: TestClient,
) -> None:
    response = client.post(
        "/accounts", json={"customer_id": 1, "initial_deposit_cents": 500}
    )
    assert response.json()["overdraft_limit_cents"] == 0


def test_account_creation_for_unknown_customer_is_404(client: TestClient) -> None:
    response = client.post(
        "/accounts", json={"customer_id": 9999, "initial_deposit_cents": 500}
    )
    assert response.status_code == status.HTTP_404_NOT_FOUND
    assert response.json()["code"] == "unknown_customer"


def test_balance_for_unknown_account_is_404(client: TestClient) -> None:
    response = client.get(f"/accounts/{uuid.uuid4()}/balance")
    assert response.status_code == status.HTTP_404_NOT_FOUND
    assert response.json()["code"] == "unknown_account"


def test_history_for_unknown_account_is_404(client: TestClient) -> None:
    response = client.get(f"/accounts/{uuid.uuid4()}/transfers")
    assert response.status_code == status.HTTP_404_NOT_FOUND
    assert response.json()["code"] == "unknown_account"


def test_history_is_ordered_by_entry_id_descending(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount in (100, 200, 300):
        assert transfer(client, source, destination, amount)[0] == (
            status.HTTP_201_CREATED
        )
    history = client.get(f"/accounts/{source}/transfers").json()["entries"]
    entry_ids = [entry["entry_id"] for entry in history]
    assert entry_ids == sorted(entry_ids, reverse=True)
    assert history[-1]["direction"] == "CREDIT"
    assert history[-1]["transfer_id"] is None


def test_history_is_rendered_from_the_queried_accounts_perspective(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    assert transfer(client, source, destination, 2500)[0] == status.HTTP_201_CREATED

    outgoing = client.get(f"/accounts/{source}/transfers").json()["entries"][0]
    incoming = client.get(f"/accounts/{destination}/transfers").json()["entries"][0]

    assert outgoing["transfer_id"] == incoming["transfer_id"]
    assert outgoing["direction"] == "DEBIT"
    assert incoming["direction"] == "CREDIT"
    assert outgoing["amount_cents"] == -2500
    assert incoming["amount_cents"] == 2500
    assert outgoing["counterparty_account_id"] == str(destination)
    assert incoming["counterparty_account_id"] == str(source)


def test_page_size_and_cursor_are_validated_rather_than_clamped(
    client: TestClient,
) -> None:
    account_id = open_account(client, initial_deposit_cents=100)
    path = f"/accounts/{account_id}/transfers"
    for params in (
        {"limit": 1001},
        {"limit": 0},
        {"cursor": 0},
        {"cursor": "not-an-id"},
    ):
        response = client.get(path, params=params)
        assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT, params
    assert client.get(path, params={"limit": 1000}).status_code == status.HTTP_200_OK


def test_history_pages_concatenate_to_the_unpaginated_ordering(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount in range(1, 7):
        assert transfer(client, source, destination, amount)[0] == (
            status.HTTP_201_CREATED
        )

    whole = history_page(client, source, limit=1000)
    paged = walk_history(client, source, limit=3)
    assert [entry["entry_id"] for entry in paged] == [
        entry["entry_id"] for entry in whole["entries"]
    ]
    assert len(paged) == 7
    assert len({entry["entry_id"] for entry in paged}) == 7


def test_next_cursor_is_null_only_on_the_last_page(client: TestClient) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount in range(1, 5):
        transfer(client, source, destination, amount)

    first = history_page(client, source, limit=2)
    assert len(first["entries"]) == 2
    assert first["next_cursor"] == first["entries"][-1]["entry_id"]

    second = history_page(client, source, limit=2, cursor=first["next_cursor"])
    assert len(second["entries"]) == 2
    assert second["next_cursor"] is not None

    last = history_page(client, source, limit=2, cursor=second["next_cursor"])
    assert len(last["entries"]) == 1
    assert last["next_cursor"] is None

    exact = history_page(client, source, limit=5)
    assert len(exact["entries"]) == 5
    assert exact["next_cursor"] is None


def test_an_entry_written_mid_walk_does_not_shift_the_page_boundary(
    client: TestClient,
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount in range(1, 5):
        transfer(client, source, destination, amount)

    first = history_page(client, source, limit=2)
    seen = [entry["entry_id"] for entry in first["entries"]]

    assert transfer(client, source, destination, 99)[0] == status.HTTP_201_CREATED

    rest: list[int] = []
    cursor = first["next_cursor"]
    while cursor is not None:
        page = history_page(client, source, limit=2, cursor=cursor)
        rest.extend(entry["entry_id"] for entry in page["entries"])
        cursor = page["next_cursor"]

    walked = seen + rest
    assert len(walked) == len(set(walked)), "An entry was returned twice"
    assert walked == sorted(walked, reverse=True)
    assert len(walked) == 5


def test_history_past_the_page_size_maximum_is_fully_reachable(
    client: TestClient, session: Session
) -> None:
    account_id = open_account(client, initial_deposit_cents=1)
    total = 1500
    session.execute(
        insert(Entry),
        [
            {
                "account_id": account_id,
                "transfer_id": None,
                "type": EntryType.DEPOSIT,
                "amount_cents": 1,
            }
            for _ in range(total - 1)
        ],
    )
    session.commit()

    walked = walk_history(client, account_id, limit=1000)
    assert len(walked) == total
    entry_ids = [entry["entry_id"] for entry in walked]
    assert len(set(entry_ids)) == total
    assert entry_ids == sorted(entry_ids, reverse=True)


def test_a_cursor_from_another_account_cannot_read_across_accounts(
    client: TestClient,
) -> None:
    mine = open_account(client, initial_deposit_cents=100)
    theirs = open_account(client, initial_deposit_cents=100)
    for amount in (10, 20, 30):
        transfer(client, theirs, mine, amount)

    their_newest = history_page(client, theirs, limit=1)["entries"][0]["entry_id"]
    page = history_page(client, mine, limit=100, cursor=their_newest + 1)
    my_entry_ids = {
        entry["entry_id"] for entry in history_page(client, mine, limit=1000)["entries"]
    }
    assert {entry["entry_id"] for entry in page["entries"]} <= my_entry_ids
