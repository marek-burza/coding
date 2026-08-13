from pydantic import BaseModel, Field


class TransferBase(BaseModel):
    amount: int = Field(description='Amount to be transferred (must be > 0)')
    from_account_identifier: str = Field(
        description='UUID identifier of the account to transfer from (where balance >= amount)'  # noqa
    )
    to_account_identifier: str = Field(
        description='UUID identifier of the account to transfer to'
    )


class TransferCreate(TransferBase):
    pass


class TransferRead(TransferBase):
    identifier: str = Field(
        'Auto-generated UUID identifier of the account (globally unique)'
    )

    class Config:
        from_attributes = True
