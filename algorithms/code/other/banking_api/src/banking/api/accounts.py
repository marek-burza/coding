from typing import Annotated, List, Optional

from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy.orm import Session

from banking import database
from banking.api.customers import query_and_verify_customer_identifier
from banking.models.accounts import Account
from banking.schemas.accounts import AccountCreate, AccountRead
from banking.utilities import is_identifier_valid

router = APIRouter()

metadata = [
    {
        'name': 'put_account',
        'description': 'Create an account for a specified customer',
    },
    {
        'name': 'get_accounts',
        'description': 'Read account data for a specified customer',
    },
]

ERROR_INVALID_ACCOUNT_IDENTIFIER = 'Invalid account identifier'


def validate_account_identifier(
    account_identifier: str, error_detail: str
) -> None:
    if not is_identifier_valid(account_identifier):
        raise HTTPException(
            status_code=status.HTTP_417_EXPECTATION_FAILED, detail=error_detail
        )


@router.put(
    '/accounts/',
    tags=['put_account'],
    response_model=AccountRead,
    responses={
        status.HTTP_400_BAD_REQUEST: {
            'description': 'Issued when balance < 0'
        },
        status.HTTP_417_EXPECTATION_FAILED: {
            'description': 'Issued when account identifier is an invalid UUID'
        },
        status.HTTP_500_INTERNAL_SERVER_ERROR: {
            'description': 'Issued in unlikely case of database integrity breach'  # noqa
        },
    },
)
async def put_account(
    customer_identifier: Annotated[
        int,
        Query(description='Integer identifier of the associated customer'),
    ],
    balance: Annotated[
        int,
        Query(
            description='Initial balance for the account (cannot be < 0)',
        ),
    ] = 0,
    db_session: Session = Depends(database.get_session),  # noqa: B008
) -> AccountRead:
    with db_session.begin():
        query_and_verify_customer_identifier(customer_identifier, db_session)
        account = AccountCreate(
            customer_identifier=customer_identifier, balance=balance
        )
        db_account = Account(**account.dict())
        db_session.add(db_account)
    return db_account


@router.get(
    '/accounts/',
    tags=['get_accounts'],
    response_model=List[AccountRead],
    responses={
        status.HTTP_404_NOT_FOUND: {
            'description': 'Issued when customer with given identifier does not exist'  # noqa
        },
        status.HTTP_417_EXPECTATION_FAILED: {
            'description': 'Issued when account identifier is an invalid UUID'
        },
        status.HTTP_500_INTERNAL_SERVER_ERROR: {
            'description': 'Issued in unlikely case of database integrity breach'  # noqa
        },
    },
)
async def get_accounts(
    customer_identifier: Annotated[
        Optional[int],
        Query(description='Integer identifier of the associated customer'),
    ] = None,
    account_identifier: Annotated[
        Optional[str],
        Query(
            description='UUID identifier of the account (to fetch one account)'
        ),
    ] = None,
    db_session: Session = Depends(database.get_session),  # noqa: B008
) -> List[AccountRead]:
    with db_session.begin():
        accounts = db_session.query(Account)
        if customer_identifier is not None:
            query_and_verify_customer_identifier(
                customer_identifier, db_session
            )
            accounts = accounts.filter(
                Account.customer_identifier == customer_identifier
            )
        if account_identifier is not None:
            validate_account_identifier(
                account_identifier, ERROR_INVALID_ACCOUNT_IDENTIFIER
            )
            accounts = accounts.filter(
                Account.identifier == account_identifier
            )
        return list(accounts.all())
