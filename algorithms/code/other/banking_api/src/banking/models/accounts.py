import uuid

from sqlalchemy import Column, ForeignKey, Integer, String

from banking.models import Base


class Account(Base):
    __tablename__ = 'accounts'
    identifier = Column(
        String,
        primary_key=True,
        default=lambda: str(uuid.uuid4()),
        index=True,
    )
    balance = Column(Integer, default=0)
    customer_identifier = Column(Integer, ForeignKey('customers.identifier'))
