from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from banking.models import Base

DATABASE_URL = 'sqlite:///./banking.db'

engine = create_engine(
    DATABASE_URL,
    connect_args={
        # Disabling enforcement of use of database connection by only on thread
        # is relevant for SQLite when it is used by a web framework
        # such as FastAPI which can rely on multiple threads or asynchronous
        # coroutines to handle requests simultaneously.
        'check_same_thread': False
    },
)

Session = sessionmaker(autocommit=False, autoflush=False, bind=engine)


def get_session():
    yield Session()


Base.metadata.create_all(bind=engine)
