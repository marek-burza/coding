import time
import uuid

from sqlalchemy import Column, ForeignKey, Integer, String

from banking.models import Base


class Transfer(Base):
    __tablename__ = 'transfers'
    identifier = Column(
        String, primary_key=True, default=lambda: str(uuid.uuid4()), index=True
    )
    timestamp = Column(Integer, default=lambda: time.time())
    amount = Column(Integer)
    from_account_identifier = Column(String, ForeignKey('accounts.identifier'))
    to_account_identifier = Column(String, ForeignKey('accounts.identifier'))
