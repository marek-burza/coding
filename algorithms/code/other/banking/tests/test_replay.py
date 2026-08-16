import random
import uuid

from fastapi import status
from fastapi.testclient import TestClient
from sqlalchemy import select
from sqlalchemy.orm import Session

from banking.models import Entry
from conftest import balance_cents, open_account, transfer

SEED = 20260727
TRANSFER_COUNT = 40


def recompute_balances(session: Session) -> dict[uuid.UUID, int]:
    session.expire_all()
    balances: dict[uuid.UUID, int] = {}
    entries = session.execute(select(Entry).order_by(Entry.id)).scalars().all()
    for entry in entries:
        balances[entry.account_id] = (
            balances.get(entry.account_id, 0) + entry.amount_cents
        )
    return balances


def test_reported_balances_match_an_independent_recomputation(
    client: TestClient, session: Session
) -> None:
    accounts = [open_account(client, initial_deposit_cents=10000) for _ in range(4)]
    generator = random.Random(SEED)  # noqa: S311  # nosec B311 - not cryptography
    for _ in range(TRANSFER_COUNT):
        source, destination = generator.sample(accounts, 2)
        transfer(client, source, destination, generator.randint(1, 900))

    recomputed = recompute_balances(session)
    for account_id in accounts:
        assert balance_cents(client, account_id) == recomputed[account_id]


def test_value_is_conserved_across_randomised_transfers(
    client: TestClient, session: Session
) -> None:
    deposits = [10000, 5000, 250, 1]
    accounts = [open_account(client, initial_deposit_cents=cents) for cents in deposits]
    total = sum(deposits)
    generator = random.Random(SEED)  # noqa: S311  # nosec B311 - not cryptography

    for _ in range(TRANSFER_COUNT):
        source, destination = generator.sample(accounts, 2)
        transfer(client, source, destination, generator.randint(1, 900))
        assert sum(balance_cents(client, account) for account in accounts) == total

    assert sum(recompute_balances(session).values()) == total


def test_the_entry_log_is_append_only(client: TestClient, session: Session) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)

    before = [
        (entry.id, entry.account_id, entry.amount_cents)
        for entry in session.execute(select(Entry).order_by(Entry.id)).scalars().all()
    ]
    assert transfer(client, source, destination, 2500)[0] == status.HTTP_201_CREATED
    session.expire_all()
    after = [
        (entry.id, entry.account_id, entry.amount_cents)
        for entry in session.execute(select(Entry).order_by(Entry.id)).scalars().all()
    ]

    assert after[: len(before)] == before
    assert len(after) == len(before) + 2


def test_replaying_a_prefix_of_the_log_reproduces_the_balance_at_that_point(
    client: TestClient, session: Session
) -> None:
    source = open_account(client, initial_deposit_cents=10000)
    destination = open_account(client, initial_deposit_cents=1)
    for amount in (100, 200, 300):
        transfer(client, source, destination, amount)

    session.expire_all()
    entries = list(
        session.execute(
            select(Entry).where(Entry.account_id == source).order_by(Entry.id)
        )
        .scalars()
        .all()
    )
    running = 0
    prefix_balances = []
    for entry in entries:
        running += entry.amount_cents
        prefix_balances.append(running)
        assert running >= 0, "The balance went below the limit mid-history"
    assert prefix_balances == [10000, 9900, 9700, 9400]
    assert running == balance_cents(client, source)
