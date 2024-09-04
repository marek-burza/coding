#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import importlib.metadata
import logging

import uvicorn
from fastapi import FastAPI
from fastapi.responses import RedirectResponse

from banking.api import accounts, customers, transfers
from banking.database import engine
from banking.models import Base

Base.metadata.create_all(bind=engine)


description = """
This is a basic bank API that allows bank employees to create accounts,
transfer funds between accounts, and retrieve account details.

There are three basic API endpoints: customers, accounts & transfers.

The "customers" API endpoint allows to create new customers and list them.

The "accounts" API endpoint allows to create new accounts for a specified
customer, list the accounts for any customer along with their associated
balances.

The "transfers" API endpoint allows to execute a new transfer and list
transfers associated with a specified account.
"""
app = FastAPI(
    title='Banking API',
    version=importlib.metadata.version('banking'),
    description=description,
    summary='API for elementary banking operations.',
    license_info={
        'name': importlib.metadata.metadata('banking')['License'],
    },
    openapi_tags=customers.metadata + accounts.metadata + transfers.metadata,
)
app.include_router(customers.router)
app.include_router(accounts.router)
app.include_router(transfers.router)


@app.get('/', include_in_schema=False)
async def index():
    return RedirectResponse('/docs')


def main():  # pragma: no cover
    pattern = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
    logging.basicConfig(format=pattern, level=logging.INFO)
    config = uvicorn.config.LOGGING_CONFIG
    del config['loggers']
    uvicorn.run(app, port=80, host='0.0.0.0', log_config=config)  # nosec B104


if __name__ == '__main__':  # pragma: no cover
    main()
