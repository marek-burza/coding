import uuid
from typing import Annotated

from fastapi import APIRouter, Depends, Path, Query, Response, status
from sqlalchemy import select
from sqlalchemy.orm import Session

from banking import database
from banking.api.errors import responses
from banking.api.schemas import (
    HISTORY_PAGE_SIZE_DEFAULT,
    HISTORY_PAGE_SIZE_MAX,
    AccountCreate,
    AccountRead,
    BalanceRead,
    Direction,
    HistoryEntryRead,
    HistoryPage,
    TransferCreate,
    TransferRead,
)
from banking.domain import UnknownAccount, ledger, transfers
from banking.models import Account, Entry

router = APIRouter()

metadata = [
    {
        "name": "accounts",
        "description": "Accounts, their derived balances and their history.",
    },
    {
        "name": "transfers",
        "description": "Movements of funds between two accounts.",
    },
]

AccountId = Annotated[uuid.UUID, Path(description="Identifier of the account")]


@router.post(
    "/accounts",
    tags=["accounts"],
    status_code=status.HTTP_201_CREATED,
    response_model=AccountRead,
    responses=responses(
        status.HTTP_404_NOT_FOUND, status.HTTP_422_UNPROCESSABLE_CONTENT
    ),
    summary="Open an account with an initial deposit",
)
def create_account(
    body: AccountCreate,
    response: Response,
    session: Annotated[Session, Depends(database.get_session)],
) -> Account:
    account = ledger.open_account(
        session,
        customer_id=body.customer_id,
        initial_deposit_cents=body.initial_deposit_cents,
        overdraft_limit_cents=body.overdraft_limit_cents,
    )
    session.commit()
    session.refresh(account)
    response.headers["Location"] = f"/accounts/{account.id}/balance"
    return account


@router.get(
    "/accounts/{account_id}/balance",
    tags=["accounts"],
    response_model=BalanceRead,
    responses=responses(status.HTTP_404_NOT_FOUND),
    summary="Read an account's balance",
)
def read_balance(
    account_id: AccountId,
    session: Annotated[Session, Depends(database.get_session)],
) -> BalanceRead:
    _require_account(session, account_id)
    return BalanceRead(
        account_id=account_id,
        balance_cents=ledger.balance_cents(session, account_id),
    )


@router.get(
    "/accounts/{account_id}/transfers",
    tags=["accounts"],
    response_model=HistoryPage,
    responses=responses(status.HTTP_404_NOT_FOUND),
    summary="Read a page of an account's transfer history, newest first",
)
def read_history(
    account_id: AccountId,
    session: Annotated[Session, Depends(database.get_session)],
    limit: Annotated[
        int,
        Query(
            ge=1,
            le=HISTORY_PAGE_SIZE_MAX,
            description="Page size. Bounds one response, not how much history "
            "is reachable: follow next_cursor for the rest.",
        ),
    ] = HISTORY_PAGE_SIZE_DEFAULT,
    cursor: Annotated[
        int | None,
        Query(
            ge=1,
            description="An entry id, taken from a previous page's next_cursor. "
            "Absent starts at the newest entry.",
        ),
    ] = None,
) -> HistoryPage:
    _require_account(session, account_id)
    query = select(Entry).where(Entry.account_id == account_id)
    if cursor is not None:
        query = query.where(Entry.id < cursor)
    rows = list(
        session.execute(query.order_by(Entry.id.desc()).limit(limit + 1))
        .scalars()
        .all()
    )
    entries = rows[:limit]
    next_cursor = entries[-1].id if len(rows) > limit else None
    counterparties = _counterparties(session, account_id, entries)
    return HistoryPage(
        entries=[
            HistoryEntryRead(
                entry_id=entry.id,
                transfer_id=entry.transfer_id,
                direction=(
                    Direction.CREDIT if entry.amount_cents > 0 else Direction.DEBIT
                ),
                counterparty_account_id=counterparties.get(entry.id),
                amount_cents=entry.amount_cents,
                created_at=entry.created_at,
            )
            for entry in entries
        ],
        next_cursor=next_cursor,
    )


@router.post(
    "/transfers",
    tags=["transfers"],
    status_code=status.HTTP_201_CREATED,
    response_model=TransferRead,
    responses=responses(
        status.HTTP_404_NOT_FOUND,
        status.HTTP_409_CONFLICT,
        status.HTTP_422_UNPROCESSABLE_CONTENT,
    ),
    summary="Transfer funds between two accounts, idempotently",
)
def create_transfer(
    body: TransferCreate,
    response: Response,
    session: Annotated[Session, Depends(database.get_session)],
) -> transfers.TransferResult:
    result = transfers.execute(
        session,
        source_account_id=body.source_account_id,
        destination_account_id=body.destination_account_id,
        amount_cents=body.amount_cents,
        idempotency_key=body.idempotency_key,
    )
    session.commit()
    response.headers["Location"] = f"/accounts/{result.source_account_id}/transfers"
    return result


def _require_account(session: Session, account_id: uuid.UUID) -> Account:
    account = session.get(Account, account_id)
    if account is None:
        raise UnknownAccount(str(account_id))
    return account


def _counterparties(
    session: Session, account_id: uuid.UUID, entries: list[Entry]
) -> dict[int, uuid.UUID]:
    transfer_ids = {entry.transfer_id for entry in entries if entry.transfer_id}
    if not transfer_ids:
        return {}
    others = (
        session.execute(
            select(Entry).where(
                Entry.transfer_id.in_(transfer_ids),
                Entry.account_id != account_id,
            )
        )
        .scalars()
        .all()
    )
    by_transfer = {other.transfer_id: other.account_id for other in others}
    return {
        entry.id: by_transfer[entry.transfer_id]
        for entry in entries
        if entry.transfer_id in by_transfer
    }
