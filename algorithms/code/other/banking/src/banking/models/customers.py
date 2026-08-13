from sqlalchemy import Column, Integer, String

from banking.models import Base


class Customer(Base):
    __tablename__ = 'customers'
    identifier = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True, unique=True)
