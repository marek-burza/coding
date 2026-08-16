"""Initial schema and the seeded customers from the assignment

A single revision creates everything, so `alembic upgrade head` against an
empty database is enough to exercise the API. It is amended in place rather
than corrected by a second revision for as long as no database that cannot be
recreated has run it.

There is deliberately no balance column for a reviewer to find: balances are
derived by summing an account's entries.

Revision ID: 0001
Revises:
Create Date: 2026-07-27

"""

from collections.abc import Sequence

import sqlalchemy as sa
from alembic import op

revision: str = "0001"
down_revision: str | None = None
branch_labels: str | Sequence[str] | None = None
depends_on: str | Sequence[str] | None = None

NAME_MAX_LENGTH = 200
IDEMPOTENCY_KEY_MAX_LENGTH = 200
REQUEST_HASH_LENGTH = 64
MAX_AMOUNT_CENTS = 1_000_000_000_000

SEEDED_CUSTOMERS = (
    "John Doe",
    "Jack Smith",
    "Jane Taylor",
    "Jade Wilson",
)


def upgrade() -> None:
    customers = op.create_table(
        "customers",
        sa.Column("id", sa.Integer(), autoincrement=True, nullable=False),
        sa.Column("name", sa.String(NAME_MAX_LENGTH), nullable=False),
        sa.PrimaryKeyConstraint("id", name="pk_customers"),
    )
    op.create_index("ix_customers_name", "customers", ["name"])

    op.create_table(
        "accounts",
        sa.Column("id", sa.Uuid(), nullable=False),
        sa.Column("customer_id", sa.Integer(), nullable=False),
        sa.Column("overdraft_limit_cents", sa.BigInteger(), nullable=False),
        sa.PrimaryKeyConstraint("id", name="pk_accounts"),
        sa.ForeignKeyConstraint(
            ["customer_id"], ["customers.id"], name="fk_accounts_customer_id_customers"
        ),
        sa.CheckConstraint(
            "overdraft_limit_cents >= 0", name="overdraft_limit_is_not_negative"
        ),
    )
    op.create_index("ix_accounts_customer_id", "accounts", ["customer_id"])

    op.create_table(
        "transfers",
        sa.Column("id", sa.Uuid(), nullable=False),
        sa.Column(
            "idempotency_key",
            sa.String(IDEMPOTENCY_KEY_MAX_LENGTH),
            nullable=False,
        ),
        sa.Column("request_hash", sa.String(REQUEST_HASH_LENGTH), nullable=False),
        sa.Column(
            "created_at",
            sa.DateTime(timezone=True),
            server_default=sa.func.now(),
            nullable=False,
        ),
        sa.PrimaryKeyConstraint("id", name="pk_transfers"),
        sa.UniqueConstraint("idempotency_key", name="uq_transfers_idempotency_key"),
    )

    op.create_table(
        "entries",
        sa.Column("id", sa.BigInteger(), autoincrement=True, nullable=False),
        sa.Column("account_id", sa.Uuid(), nullable=False),
        sa.Column("transfer_id", sa.Uuid(), nullable=True),
        sa.Column(
            "type",
            sa.Enum("DEPOSIT", "TRANSFER", name="entry_type"),
            nullable=False,
        ),
        sa.Column("amount_cents", sa.BigInteger(), nullable=False),
        sa.Column(
            "created_at",
            sa.DateTime(timezone=True),
            server_default=sa.func.now(),
            nullable=False,
        ),
        sa.PrimaryKeyConstraint("id", name="pk_entries"),
        sa.ForeignKeyConstraint(
            ["account_id"], ["accounts.id"], name="fk_entries_account_id_accounts"
        ),
        sa.ForeignKeyConstraint(
            ["transfer_id"], ["transfers.id"], name="fk_entries_transfer_id_transfers"
        ),
        sa.CheckConstraint("amount_cents <> 0", name="amount_is_not_zero"),
        sa.CheckConstraint(
            f"amount_cents BETWEEN -{MAX_AMOUNT_CENTS} AND {MAX_AMOUNT_CENTS}",
            name="amount_is_within_bounds",
        ),
    )
    op.create_index("ix_entries_account_id", "entries", ["account_id"])
    op.create_index("ix_entries_transfer_id", "entries", ["transfer_id"])

    op.bulk_insert(
        customers,
        [{"name": name} for name in SEEDED_CUSTOMERS],
    )


def downgrade() -> None:
    op.drop_table("entries")
    op.drop_table("transfers")
    op.drop_table("accounts")
    op.drop_table("customers")
    sa.Enum(name="entry_type").drop(op.get_bind())
