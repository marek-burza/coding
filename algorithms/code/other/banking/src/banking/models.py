import enum
import uuid
from datetime import datetime

from sqlalchemy import (
    BigInteger,
    CheckConstraint,
    DateTime,
    Enum,
    ForeignKey,
    Integer,
    MetaData,
    String,
    Uuid,
    func,
)
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column

NAMING_CONVENTION = {
    "ix": "ix_%(column_0_label)s",
    "uq": "uq_%(table_name)s_%(column_0_name)s",
    "ck": "ck_%(table_name)s_%(constraint_name)s",
    "fk": "fk_%(table_name)s_%(column_0_name)s_%(referred_table_name)s",
    "pk": "pk_%(table_name)s",
}

NAME_MAX_LENGTH = 200
IDEMPOTENCY_KEY_MAX_LENGTH = 200
REQUEST_HASH_LENGTH = 64

MAX_AMOUNT_CENTS = 1_000_000_000_000


class EntryType(enum.StrEnum):
    DEPOSIT = "DEPOSIT"
    TRANSFER = "TRANSFER"


class Base(DeclarativeBase):
    metadata = MetaData(naming_convention=NAMING_CONVENTION)


class Customer(Base):
    __tablename__ = "customers"

    id: Mapped[int] = mapped_column(Integer, primary_key=True)
    name: Mapped[str] = mapped_column(String(NAME_MAX_LENGTH), index=True)


class Account(Base):
    __tablename__ = "accounts"
    __table_args__ = (
        CheckConstraint(
            "overdraft_limit_cents >= 0", name="overdraft_limit_is_not_negative"
        ),
    )

    id: Mapped[uuid.UUID] = mapped_column(Uuid, primary_key=True, default=uuid.uuid4)
    customer_id: Mapped[int] = mapped_column(ForeignKey("customers.id"), index=True)
    overdraft_limit_cents: Mapped[int] = mapped_column(BigInteger, default=0)


class Transfer(Base):
    __tablename__ = "transfers"

    id: Mapped[uuid.UUID] = mapped_column(Uuid, primary_key=True, default=uuid.uuid4)
    idempotency_key: Mapped[str] = mapped_column(
        String(IDEMPOTENCY_KEY_MAX_LENGTH), unique=True
    )
    request_hash: Mapped[str] = mapped_column(String(REQUEST_HASH_LENGTH))
    created_at: Mapped[datetime] = mapped_column(
        DateTime(timezone=True), server_default=func.now()
    )


class Entry(Base):
    __tablename__ = "entries"
    __table_args__ = (
        CheckConstraint("amount_cents <> 0", name="amount_is_not_zero"),
        CheckConstraint(
            f"amount_cents BETWEEN -{MAX_AMOUNT_CENTS} AND {MAX_AMOUNT_CENTS}",
            name="amount_is_within_bounds",
        ),
    )

    id: Mapped[int] = mapped_column(BigInteger, primary_key=True)
    account_id: Mapped[uuid.UUID] = mapped_column(ForeignKey("accounts.id"), index=True)
    transfer_id: Mapped[uuid.UUID | None] = mapped_column(
        ForeignKey("transfers.id"), nullable=True, index=True
    )
    type: Mapped[EntryType] = mapped_column(Enum(EntryType, name="entry_type"))
    amount_cents: Mapped[int] = mapped_column(BigInteger)
    created_at: Mapped[datetime] = mapped_column(
        DateTime(timezone=True), server_default=func.now()
    )
