from typing import Annotated, List, Optional

from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm import Session

from banking import database
from banking.models.customers import Customer
from banking.schemas.customers import CustomerCreate, CustomerRead
from banking.utilities import cleanse_customer_name

router = APIRouter()

metadata = [
    {
        'name': 'put_customer',
        'description': 'Create a customer',
    },
    {
        'name': 'get_customers',
        'description': 'Read customer data',
    },
]

ERROR_CUSTOMER_NOT_FOUND = 'Customer not found'
ERROR_CUSTOMER_IDENTIFIER_INTEGRITY_ERROR = (
    'Customer identifier integrity error'
)
ERROR_INVALID_CUSTOMER_NAME = 'Invalid customer name'
ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS = (
    'Customer with this identifier already exists'
)
ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS = (
    'Customer with this name already exists'
)


def query_and_verify_customer_identifier(
    customer_identifier: Optional[int], db_session: Session
) -> List[CustomerRead]:
    customers = db_session.query(Customer)
    if customer_identifier is not None:
        customers = customers.filter(
            Customer.identifier == customer_identifier
        )
        count = customers.count()
        if count == 0:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND,
                detail=ERROR_CUSTOMER_NOT_FOUND,
            )
        if count > 1:  # pragma: no cover
            raise HTTPException(
                status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
                detail=ERROR_CUSTOMER_IDENTIFIER_INTEGRITY_ERROR,
            )
    return list(customers.all())


@router.put(
    '/customers/',
    tags=['put_customer'],
    response_model=CustomerRead,
    responses={
        status.HTTP_409_CONFLICT: {
            'description': 'Issued when customer with the given identifier or name already exists'  # noqa
        },
        status.HTTP_417_EXPECTATION_FAILED: {
            'description': 'Issued when customer name is invalid'
        },
    },
)
async def put_customer(
    customer_identifier: Annotated[
        int,
        Query(
            description='Integer identifier of the customer (must be unique)'
        ),
    ],
    customer_name: Annotated[
        str,
        Query(
            description='Name of the customer (will be sanitized to only letters and spaces'  # noqa
        ),
    ],
    db_session: Session = Depends(database.get_session),  # noqa: B008
) -> CustomerRead:
    customer_name = cleanse_customer_name(customer_name)
    if customer_name == '':
        raise HTTPException(
            status_code=status.HTTP_417_EXPECTATION_FAILED,
            detail=ERROR_INVALID_CUSTOMER_NAME,
        )
    try:
        with db_session.begin():
            customer = CustomerCreate(
                identifier=customer_identifier, name=customer_name
            )
            db_customer = Customer(**customer.dict())
            db_session.add(db_customer)
    except IntegrityError as error:
        if 'customers.identifier' in str(error):
            raise HTTPException(
                status_code=status.HTTP_409_CONFLICT,
                detail=ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS,
            ) from error
        if 'customers.name' in str(error):
            raise HTTPException(
                status_code=status.HTTP_409_CONFLICT,
                detail=ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS,
            ) from error
    return db_customer


@router.get(
    '/customers/',
    tags=['get_customers'],
    response_model=List[CustomerRead],
    responses={
        status.HTTP_404_NOT_FOUND: {
            'description': 'Issued when customer with given identifier does not exist'  # noqa
        },
        status.HTTP_500_INTERNAL_SERVER_ERROR: {
            'description': 'Issued in unlikely case of database integrity breach'  # noqa
        },
    },
)
async def get_customers(
    customer_identifier: Annotated[
        Optional[int],
        Query(description='Integer identifier of the customer'),
    ] = None,
    db_session: Session = Depends(database.get_session),  # noqa: B008
):
    return query_and_verify_customer_identifier(
        customer_identifier, db_session
    )
