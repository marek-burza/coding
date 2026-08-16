import importlib.metadata
import logging

import uvicorn
from fastapi import FastAPI
from fastapi.responses import RedirectResponse

from banking.api import customers, errors, routes

description = """
An internal HTTP API for bank employees: customers, accounts, balances and
transfers between accounts.

Money is held in integer Euro cents throughout, and crosses this API the same
way, so there is no decimal parsing and no rounding mode anywhere.

Balances are not stored. The ledger is append-only and double-entry: a
transfer writes two entries that sum to zero, a deposit writes a single
positive entry, and a balance is the sum of an account's entries, recomputed
on every read. Nothing updates or deletes an entry, so any reported balance
can be reproduced by replaying the log.

Transfer creation is idempotent on a client-supplied key carried in the
request body. Generate one key per intended transfer: replaying it returns
the original outcome, and reusing it with a different payload is a conflict
rather than a second transfer.
"""

app = FastAPI(
    title="Banking API",
    version=importlib.metadata.version("banking"),
    description=description,
    summary="API for elementary banking operations.",
    license_info={
        "name": importlib.metadata.metadata("banking")["License-Expression"],
    },
    openapi_tags=customers.metadata + routes.metadata,
)
errors.install(app)
app.include_router(customers.router)
app.include_router(routes.router)


@app.get("/", include_in_schema=False)
async def index() -> RedirectResponse:
    return RedirectResponse("/docs")


@app.get("/health", include_in_schema=False, summary="Liveness probe")
async def health() -> dict[str, str]:
    return {"status": "ok"}


def main() -> None:  # pragma: no cover
    pattern = "%(asctime)s - %(name)s - %(levelname)s - %(message)s"
    logging.basicConfig(format=pattern, level=logging.INFO)
    config = uvicorn.config.LOGGING_CONFIG
    del config["loggers"]
    uvicorn.run(app, port=8000, host="0.0.0.0", log_config=config)  # nosec B104


if __name__ == "__main__":  # pragma: no cover
    main()
