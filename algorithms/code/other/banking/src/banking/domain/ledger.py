import uuid
from collections.abc import Sequence
from typing import NamedTuple

from sqlalchemy import func, select
from sqlalchemy.orm import Session

from banking.domain import InsufficientFunds, UnknownAccount, UnknownCustomer
from banking.models import Account, Customer, Entry, EntryType


class Posting(NamedTuple):
    account_id: uuid.UUID
    amount_cents: int


def balance_cents(session: Session, account_id: uuid.UUID) -> int:
    return int(
        session.execute(
            select(func.coalesce(func.sum(Entry.amount_cents), 0)).where(
                Entry.account_id == account_id
            )
        ).scalar_one()
    )


def lock_accounts(
    session: Session, account_ids: Sequence[uuid.UUID]
) -> dict[uuid.UUID, Account]:
    accounts = (
        session.execute(
            select(Account)
            .where(Account.id.in_(set(account_ids)))
            .order_by(Account.id)
            .with_for_update()
        )
        .scalars()
        .all()
    )
    locked = {account.id: account for account in accounts}
    for account_id in account_ids:
        if account_id not in locked:
            raise UnknownAccount(str(account_id))
    return locked


def record(
    session: Session,
    entry_type: EntryType,
    postings: Sequence[Posting],
    transfer_id: uuid.UUID | None = None,
) -> list[Entry]:
    locked = lock_accounts(session, [posting.account_id for posting in postings])
    match entry_type:
        case EntryType.DEPOSIT:
            pass
        case EntryType.TRANSFER:
            if sum(posting.amount_cents for posting in postings) != 0:
                raise ValueError("A transfer's entries must sum to zero")
            _reject_overdrawn(session, locked, postings)

    entries = [
        Entry(
            account_id=posting.account_id,
            transfer_id=transfer_id,
            type=entry_type,
            amount_cents=posting.amount_cents,
        )
        for posting in postings
    ]
    session.add_all(entries)
    session.flush()
    return entries


def _reject_overdrawn(
    session: Session,
    locked: dict[uuid.UUID, Account],
    postings: Sequence[Posting],
) -> None:
    for posting in postings:
        if posting.amount_cents >= 0:
            continue
        account = locked[posting.account_id]
        resulting = balance_cents(session, posting.account_id) + posting.amount_cents
        if resulting < -account.overdraft_limit_cents:
            raise InsufficientFunds(str(posting.account_id))


def open_account(
    session: Session,
    customer_id: int,
    initial_deposit_cents: int,
    overdraft_limit_cents: int,
) -> Account:
    customer = session.get(Customer, customer_id)
    if customer is None:
        raise UnknownCustomer(str(customer_id))
    account = Account(
        customer_id=customer_id, overdraft_limit_cents=overdraft_limit_cents
    )
    session.add(account)
    session.flush()
    record(
        session,
        EntryType.DEPOSIT,
        [Posting(account_id=account.id, amount_cents=initial_deposit_cents)],
    )
    return account
