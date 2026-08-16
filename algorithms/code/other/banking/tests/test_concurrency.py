import threading
import uuid
from collections.abc import Callable

from sqlalchemy.orm import Session

from banking import database
from banking.domain import InsufficientFunds, ledger, transfers
from conftest import open_account_directly

COMMITTED = "committed"
REJECTED = "insufficient_funds"


def attempt(
    source: uuid.UUID, destination: uuid.UUID, amount_cents: int
) -> Callable[[], str]:

    def run() -> str:
        try:
            with database.Session() as session:
                transfers.execute(
                    session,
                    source_account_id=source,
                    destination_account_id=destination,
                    amount_cents=amount_cents,
                    idempotency_key=str(uuid.uuid4()),
                )
                session.commit()
            return COMMITTED
        except InsufficientFunds:
            return REJECTED

    return run


def run_together(*calls: Callable[[], str]) -> list[str]:
    outcomes: list[str] = ["not run"] * len(calls)
    barrier = threading.Barrier(len(calls))

    def run(index: int, call: Callable[[], str]) -> None:
        barrier.wait()
        try:
            outcomes[index] = call()
        except Exception as error:  # noqa: BLE001 - surfaced as a failed assert
            outcomes[index] = f"{type(error).__name__}: {error}"

    threads = [
        threading.Thread(target=run, args=(index, call))
        for index, call in enumerate(calls)
    ]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join(timeout=30)
    assert not any(thread.is_alive() for thread in threads), (
        "A transfer deadlocked or hung"
    )
    return outcomes


def balance_of(session: Session, account_id: uuid.UUID) -> int:
    session.expire_all()
    return ledger.balance_cents(session, account_id)


def test_only_one_of_two_racing_transfers_can_succeed(session: Session) -> None:
    source = open_account_directly(initial_deposit_cents=10000)
    destination = open_account_directly(initial_deposit_cents=1)

    outcomes = run_together(
        attempt(source, destination, 8000), attempt(source, destination, 8000)
    )

    assert sorted(outcomes) == [COMMITTED, REJECTED]
    assert balance_of(session, source) == 2000
    assert balance_of(session, destination) == 8001


def test_opposing_transfers_do_not_deadlock(session: Session) -> None:
    first = open_account_directly(initial_deposit_cents=10000)
    second = open_account_directly(initial_deposit_cents=10000)

    outcomes = run_together(attempt(first, second, 3000), attempt(second, first, 4000))

    assert outcomes == [COMMITTED, COMMITTED]
    assert balance_of(session, first) == 11000
    assert balance_of(session, second) == 9000
    assert balance_of(session, first) + balance_of(session, second) == 20000


def test_concurrent_transfers_never_cross_the_overdraft_limit(
    session: Session,
) -> None:
    source = open_account_directly(
        initial_deposit_cents=1000, overdraft_limit_cents=500
    )
    destination = open_account_directly(initial_deposit_cents=1)

    outcomes = run_together(*[attempt(source, destination, 600) for _ in range(5)])

    assert outcomes.count(COMMITTED) == 2
    assert outcomes.count(REJECTED) == 3
    assert balance_of(session, source) == -200
    assert balance_of(session, source) >= -500


def test_racing_the_same_idempotency_key_writes_one_transfer(
    session: Session,
) -> None:
    source = open_account_directly(initial_deposit_cents=10000)
    destination = open_account_directly(initial_deposit_cents=1)
    key = str(uuid.uuid4())

    def same_key() -> str:
        with database.Session() as own_session:
            transfers.execute(
                own_session,
                source_account_id=source,
                destination_account_id=destination,
                amount_cents=2500,
                idempotency_key=key,
            )
            own_session.commit()
        return COMMITTED

    outcomes = run_together(same_key, same_key)

    assert outcomes == [COMMITTED, COMMITTED]
    assert balance_of(session, source) == 7500
    assert balance_of(session, destination) == 2501
