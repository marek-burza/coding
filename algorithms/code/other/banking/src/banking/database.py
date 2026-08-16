import os
from collections.abc import Iterator

from sqlalchemy import create_engine
from sqlalchemy.exc import IntegrityError
from sqlalchemy.orm import Session as SessionType
from sqlalchemy.orm import sessionmaker
from sqlalchemy.pool import NullPool

DEFAULT_DATABASE_URL = "postgresql+psycopg://banking:banking@localhost:5432/banking"
DATABASE_URL = os.environ.get("BANKING_DATABASE_URL", DEFAULT_DATABASE_URL)

engine = create_engine(DATABASE_URL, poolclass=NullPool)

Session = sessionmaker(autocommit=False, autoflush=False, bind=engine)


def get_session() -> Iterator[SessionType]:
    with Session() as session:
        yield session


def violated_constraint(error: IntegrityError) -> str | None:
    diagnostic = getattr(error.orig, "diag", None)
    return getattr(diagnostic, "constraint_name", None)
