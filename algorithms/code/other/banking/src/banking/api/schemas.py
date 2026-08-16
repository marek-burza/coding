import enum
import unicodedata
import uuid
from datetime import datetime
from typing import Annotated, Optional

from pydantic import BaseModel, ConfigDict, Field, StrictInt, field_validator

from banking.models import (
    IDEMPOTENCY_KEY_MAX_LENGTH,
    MAX_AMOUNT_CENTS,
    NAME_MAX_LENGTH,
)

HISTORY_PAGE_SIZE_DEFAULT = 100
HISTORY_PAGE_SIZE_MAX = 1000

AmountCents = Annotated[
    StrictInt,
    Field(
        gt=0,
        le=MAX_AMOUNT_CENTS,
        description="Amount in Euro cents. Positive; direction is expressed by "
        "the ledger, not by a sign here.",
        examples=[2500],
    ),
]
OverdraftLimitCents = Annotated[
    StrictInt,
    Field(
        ge=0,
        le=MAX_AMOUNT_CENTS,
        description="How far below zero this account may go, in Euro cents.",
        examples=[0],
    ),
]


class Direction(enum.StrEnum):
    """The effect of an entry on the account being asked about."""

    CREDIT = "CREDIT"
    DEBIT = "DEBIT"


def _reject_unstorable_name(value: str) -> str:
    normalised = unicodedata.normalize("NFC", value).strip()
    if not normalised:
        raise ValueError("Customer name must not be blank")
    if any(not character.isprintable() for character in normalised):
        raise ValueError("Customer name must not contain control characters")
    if len(normalised) > NAME_MAX_LENGTH:
        raise ValueError(f"Customer name must be at most {NAME_MAX_LENGTH} characters")
    return normalised


class CustomerCreate(BaseModel):
    name: str = Field(
        description="Name of the customer, stored exactly as sent once "
        "Unicode-normalised. Never sanitized.",
        examples=["John Doe"],
    )

    _normalise = field_validator("name")(_reject_unstorable_name)


class CustomerRead(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int = Field(description="Server-generated identifier of the customer")
    name: str = Field(description="Name of the customer")


class AccountCreate(BaseModel):
    customer_id: int = Field(
        description="Identifier of the customer that owns the account",
        examples=[1],
    )
    initial_deposit_cents: AmountCents
    overdraft_limit_cents: OverdraftLimitCents = 0


class AccountRead(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: uuid.UUID = Field(description="Server-generated identifier of the account")
    customer_id: int = Field(description="Identifier of the owning customer")
    overdraft_limit_cents: int = Field(
        description="How far below zero this account may go, in Euro cents"
    )


class BalanceRead(BaseModel):
    account_id: uuid.UUID = Field(description="Identifier of the account")
    balance_cents: int = Field(
        description="Balance in Euro cents, derived from the entry log on every "
        "read. May be negative down to the overdraft limit.",
        examples=[7500],
    )


class TransferCreate(BaseModel):
    source_account_id: uuid.UUID = Field(
        description="Account the funds leave", examples=[str(uuid.uuid4())]
    )
    destination_account_id: uuid.UUID = Field(
        description="Account the funds arrive in", examples=[str(uuid.uuid4())]
    )
    amount_cents: AmountCents
    idempotency_key: str = Field(
        min_length=1,
        max_length=IDEMPOTENCY_KEY_MAX_LENGTH,
        description="Generated once per intended transfer. Replaying it returns "
        "the original outcome; reusing it with a different payload is a "
        "conflict, not a second transfer.",
        examples=["3f1a8d2c-7b4e-4a19-9c33-5f2e1d0a6b74"],
    )


class TransferRead(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: uuid.UUID = Field(description="Identifier of the transfer")
    source_account_id: uuid.UUID = Field(description="Account the funds left")
    destination_account_id: uuid.UUID = Field(
        description="Account the funds arrived in"
    )
    amount_cents: int = Field(description="Amount moved, in Euro cents")
    created_at: datetime = Field(description="When the transfer was recorded")


class HistoryEntryRead(BaseModel):
    """One ledger entry, rendered from the queried account's perspective."""

    entry_id: int = Field(
        description="Monotonic identifier that history is ordered by, and that "
        "pagination cursors over"
    )
    transfer_id: Optional[uuid.UUID] = Field(
        description="The transfer this entry belongs to, absent for a deposit"
    )
    direction: Direction = Field(
        description="CREDIT if the money arrived, DEBIT if it left"
    )
    counterparty_account_id: Optional[uuid.UUID] = Field(
        description="The other account in the transfer, absent for a deposit"
    )
    amount_cents: int = Field(
        description="Signed effect on the queried account, in Euro cents"
    )
    created_at: datetime = Field(description="When the entry was recorded")


class HistoryPage(BaseModel):
    """One page of history, plus the cursor that fetches the page after it."""

    entries: list[HistoryEntryRead] = Field(
        description="Ledger entries for the queried account, newest first"
    )
    next_cursor: Optional[int] = Field(
        description="Pass back as `cursor` to fetch the next page. Null on the "
        "last page, so a client stops without issuing an empty request.",
        examples=[41],
    )


class ErrorResponse(BaseModel):
    """The one error body shape, for every failure the API can produce."""

    code: str = Field(
        description="Machine-readable failure code", examples=["insufficient_funds"]
    )
    detail: str = Field(description="Human-readable explanation")
