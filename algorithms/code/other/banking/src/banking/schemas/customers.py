from pydantic import BaseModel, Field


class CustomerBase(BaseModel):
    identifier: int = Field(
        description='Integer identifier of the customer (must be globally unique)'  # noqa
    )
    name: str = Field(
        description='Name of the customer (sanitized to contain only letters and spaces)'  # noqa
    )


class CustomerCreate(CustomerBase):
    pass


class CustomerRead(CustomerBase):
    class Config:
        from_attributes = True
