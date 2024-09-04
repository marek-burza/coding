from fastapi import HTTPException, status
from pydantic import BaseModel, Field, field_validator
from pydantic_core.core_schema import FieldValidationInfo

ERROR_BALANCE_CANNOT_BE_NEGATIVE = 'Balance cannot be negative'


class AccountBase(BaseModel):
    balance: int = Field(description='Balance of the account (cannot be < 0)')
    customer_identifier: int = Field(
        description='Integer identifier of the associated customer'
    )

    @field_validator('balance', mode='before')
    def validate_balance_is_not_negative(
        self, value: int, info: FieldValidationInfo
    ) -> int:
        if value < 0:
            raise HTTPException(
                status_code=status.HTTP_400_BAD_REQUEST,
                detail=ERROR_BALANCE_CANNOT_BE_NEGATIVE,
            )
        return value


class AccountCreate(AccountBase):
    pass


class AccountRead(AccountBase):
    identifier: str = Field(
        description='Auto-generated UUID identifier of the account (globally unique)'  # noqa
    )

    class Config:
        from_attributes = True
