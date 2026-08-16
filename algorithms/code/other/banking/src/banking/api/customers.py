from typing import Annotated

from fastapi import APIRouter, Depends, Response, status
from sqlalchemy import select
from sqlalchemy.orm import Session

from banking import database
from banking.api.schemas import CustomerCreate, CustomerRead
from banking.models import Customer

router = APIRouter(prefix="/customers", tags=["customers"])

metadata = [
    {
        "name": "customers",
        "description": "The reference set of bank customers.",
    },
]


@router.post(
    "",
    status_code=status.HTTP_201_CREATED,
    response_model=CustomerRead,
    summary="Create a customer",
)
def create_customer(
    body: CustomerCreate,
    response: Response,
    session: Annotated[Session, Depends(database.get_session)],
) -> Customer:
    customer = Customer(name=body.name)
    session.add(customer)
    session.commit()
    session.refresh(customer)
    response.headers["Location"] = router.prefix
    return customer


@router.get("", response_model=list[CustomerRead], summary="List the customers")
def list_customers(
    session: Annotated[Session, Depends(database.get_session)],
) -> list[Customer]:
    return list(session.execute(select(Customer).order_by(Customer.id)).scalars().all())
