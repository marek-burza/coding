import hashlib
import uuid
from collections.abc import Sequence
from dataclasses import dataclass
from datetime import datetime

from sqlalchemy import select
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm import Session

from banking import database
from banking.domain import IdempotencyConflict, SelfTransfer
from banking.domain.ledger import Posting, record
from banking.models import Entry, EntryType, Transfer

IDEMPOTENCY_KEY_CONSTRAINT = "uq_transfers_idempotency_key"


@dataclass(frozen=True)
class TransferResult:
    id: uuid.UUID
    created_at: datetime
    source_account_id: uuid.UUID
    destination_account_id: uuid.UUID
    amount_cents: int


def request_hash(
    source_account_id: uuid.UUID,
    destination_account_id: uuid.UUID,
    amount_cents: int,
) -> str:
    payload = f"{source_account_id}:{destination_account_id}:{amount_cents}"
    return hashlib.sha256(payload.encode()).hexdigest()


def execute(
    session: Session,
    source_account_id: uuid.UUID,
    destination_account_id: uuid.UUID,
    amount_cents: int,
    idempotency_key: str,
) -> TransferResult:
    if source_account_id == destination_account_id:
        raise SelfTransfer(str(source_account_id))

    expected_hash = request_hash(
        source_account_id, destination_account_id, amount_cents
    )
    transfer = Transfer(idempotency_key=idempotency_key, request_hash=expected_hash)
    try:
        with session.begin_nested():
            session.add(transfer)
            session.flush()
    except IntegrityError as error:
        if database.violated_constraint(error) != IDEMPOTENCY_KEY_CONSTRAINT:
            raise
        return _replay(session, idempotency_key, expected_hash)

    entries = record(
        session,
        EntryType.TRANSFER,
        [
            Posting(account_id=source_account_id, amount_cents=-amount_cents),
            Posting(account_id=destination_account_id, amount_cents=amount_cents),
        ],
        transfer_id=transfer.id,
    )
    return _result(transfer, entries)


def _replay(
    session: Session, idempotency_key: str, expected_hash: str
) -> TransferResult:
    transfer = session.execute(
        select(Transfer).where(Transfer.idempotency_key == idempotency_key)
    ).scalar_one()
    if transfer.request_hash != expected_hash:
        raise IdempotencyConflict(idempotency_key)
    entries = (
        session.execute(select(Entry).where(Entry.transfer_id == transfer.id))
        .scalars()
        .all()
    )
    return _result(transfer, entries)


def _result(transfer: Transfer, entries: Sequence[Entry]) -> TransferResult:
    source = next(entry for entry in entries if entry.amount_cents < 0)
    destination = next(entry for entry in entries if entry.amount_cents > 0)
    return TransferResult(
        id=transfer.id,
        created_at=transfer.created_at,
        source_account_id=source.account_id,
        destination_account_id=destination.account_id,
        amount_cents=destination.amount_cents,
    )
