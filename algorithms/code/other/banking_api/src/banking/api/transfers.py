from typing import Annotated, List, cast

from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy import Column, or_
from sqlalchemy.orm import Session

from banking import database
from banking.api.accounts import validate_account_identifier
from banking.models.accounts import Account
from banking.models.transfers import Transfer
from banking.schemas.transfers import TransferCreate, TransferRead

router = APIRouter()

metadata = [
    {
        'name': 'put_transfer',
        'description': 'Execute a transfer between specified accounts',
    },
    {
        'name': 'get_transfers',
        'description': 'Read transfer for specified account',
    },
]

ERROR_INVALID_SOURCE_ACCOUNT_IDENTIFIER = 'Invalid source account identifier'
ERROR_INVALID_DESTINATION_ACCOUNT_IDENTIFIER = (
    'Invalid destination account identifier'
)
ERROR_THE_AMOUNT_MUST_BE_GREATER_THAN_ZERO = (
    'The amount must be greater than 0'
)
ERROR_SOURCE_ACCOUNT_NOT_FOUND = 'Source account not found'
ERROR_INSUFFICIENT_FUNDS = 'Insufficient funds'
ERROR_DESTINATION_ACCOUNT_NOT_FOUND = 'Destination account not found'
ERROR_INVALID_ACCOUNT_IDENTIFIER = 'Invalid account identifier'


def query_and_verify_account_identifier(
    account_identifier: str, error_detail: str, db_session: Session
) -> Account:
    accounts = db_session.query(Account).filter(
        Account.identifier == account_identifier
    )
    if accounts.count() == 0:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND, detail=error_detail
        )
    return cast(Account, accounts.first())


@router.put(
    '/transfers/',
    tags=['put_transfer'],
    response_model=TransferRead,
    responses={
        status.HTTP_400_BAD_REQUEST: {
            'description': 'Issued the amount is less or equal to zero'
        },
        status.HTTP_404_NOT_FOUND: {
            'description': 'Issued when any of the accounts with given identifiers do not exist'  # noqa
        },
        status.HTTP_412_PRECONDITION_FAILED: {
            'description': 'Issued in case of insufficient funds on the source account'  # noqa
        },
        status.HTTP_417_EXPECTATION_FAILED: {
            'description': 'Issued when any of the account identifiers are an invalid UUID'  # noqa
        },
    },
)
async def put_transfer(
    from_account_identifier: Annotated[
        str,
        Query(
            description='UUID identifier of the account to transfer from (where balance >= amount)'  # noqa
        ),
    ],
    to_account_identifier: Annotated[
        str,
        Query(description='UUID identifier of the account to transfer to'),
    ],
    amount: Annotated[
        int,
        Query(
            description='Amount to transfer (origin account must have sufficient funds)'  # noqa
        ),
    ],
    db_session: Session = Depends(database.get_session),  # noqa: B008
) -> TransferRead:
    validate_account_identifier(
        from_account_identifier, ERROR_INVALID_SOURCE_ACCOUNT_IDENTIFIER
    )
    validate_account_identifier(
        to_account_identifier, ERROR_INVALID_DESTINATION_ACCOUNT_IDENTIFIER
    )
    if amount <= 0:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail=ERROR_THE_AMOUNT_MUST_BE_GREATER_THAN_ZERO,
        )
    with db_session.begin():
        from_account = query_and_verify_account_identifier(
            from_account_identifier,
            ERROR_SOURCE_ACCOUNT_NOT_FOUND,
            db_session,
        )
        if from_account.balance < amount:
            raise HTTPException(
                status_code=status.HTTP_412_PRECONDITION_FAILED,
                detail=ERROR_INSUFFICIENT_FUNDS,
            )
        to_account = query_and_verify_account_identifier(
            to_account_identifier,
            ERROR_DESTINATION_ACCOUNT_NOT_FOUND,
            db_session,
        )
        from_account.balance = cast(Column[int], from_account.balance - amount)
        to_account.balance = cast(Column[int], to_account.balance + amount)
        transfer = TransferCreate(
            from_account_identifier=from_account_identifier,
            to_account_identifier=to_account_identifier,
            amount=amount,
        )
        db_transfer = Transfer(**transfer.dict())
        db_session.add(db_transfer)
    return db_transfer


@router.get(
    '/transfers/',
    tags=['get_transfers'],
    response_model=List[TransferRead],
    responses={
        status.HTTP_417_EXPECTATION_FAILED: {
            'description': 'Issued when account identifier is an invalid UUID'
        },
    },
)
async def get_transfers(
    account_identifier: Annotated[
        str,
        Query(
            description='UUID identifier of the account to fetch transfers for'
        ),
    ],
    db_session: Session = Depends(database.get_session),  # noqa: B008
) -> List[TransferRead]:
    validate_account_identifier(
        account_identifier, ERROR_INVALID_ACCOUNT_IDENTIFIER
    )
    return list(
        db_session.query(Transfer)
        .filter(
            or_(
                Transfer.from_account_identifier == account_identifier,
                Transfer.to_account_identifier == account_identifier,
            )
        )
        .all()
    )
