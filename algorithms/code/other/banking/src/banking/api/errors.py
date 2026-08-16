from typing import cast

from fastapi import FastAPI, Request, status
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse

from banking.api.schemas import ErrorResponse
from banking.domain import (
    DomainError,
    IdempotencyConflict,
    InsufficientFunds,
    SelfTransfer,
    UnknownAccount,
    UnknownCustomer,
)

STATUS_BY_ERROR: dict[type[DomainError], int] = {
    UnknownAccount: status.HTTP_404_NOT_FOUND,
    UnknownCustomer: status.HTTP_404_NOT_FOUND,
    InsufficientFunds: status.HTTP_422_UNPROCESSABLE_CONTENT,
    SelfTransfer: status.HTTP_422_UNPROCESSABLE_CONTENT,
    IdempotencyConflict: status.HTTP_409_CONFLICT,
}

DETAIL_BY_ERROR: dict[type[DomainError], str] = {
    UnknownAccount: "No account with this identifier",
    UnknownCustomer: "No customer with this identifier",
    InsufficientFunds: "The funding account would go past its overdraft limit",
    SelfTransfer: "Source and destination account must differ",
    IdempotencyConflict: "This idempotency key was already used with a "
    "different payload",
}

VALIDATION_CODE = "invalid_request"

RESPONSES: dict[int, dict[str, object]] = {
    status.HTTP_404_NOT_FOUND: {
        "model": ErrorResponse,
        "description": "The referenced account or customer does not exist",
    },
    status.HTTP_409_CONFLICT: {
        "model": ErrorResponse,
        "description": "The idempotency key was reused with a different payload",
    },
    status.HTTP_422_UNPROCESSABLE_CONTENT: {
        "model": ErrorResponse,
        "description": "The request is well-formed but cannot be carried out",
    },
}


def responses(*codes: int) -> dict[int | str, dict[str, object]]:
    return {code: RESPONSES[code] for code in codes}


def _body(code: str, detail: str, status_code: int) -> JSONResponse:
    return JSONResponse(
        status_code=status_code,
        content=ErrorResponse(code=code, detail=detail).model_dump(),
    )


async def _domain_error(request: Request, error: Exception) -> JSONResponse:
    domain_error = cast(DomainError, error)
    status_code = STATUS_BY_ERROR[type(domain_error)]
    detail = DETAIL_BY_ERROR[type(domain_error)]
    return _body(domain_error.code, f"{detail}: {domain_error}", status_code)


async def _validation_error(request: Request, error: Exception) -> JSONResponse:
    first = cast(RequestValidationError, error).errors()[0]
    location = ".".join(str(part) for part in first["loc"][1:])
    detail = f"{location}: {first['msg']}" if location else first["msg"]
    return _body(VALIDATION_CODE, detail, status.HTTP_422_UNPROCESSABLE_CONTENT)


def install(app: FastAPI) -> None:
    for error_type in STATUS_BY_ERROR:
        app.add_exception_handler(error_type, _domain_error)
    app.add_exception_handler(RequestValidationError, _validation_error)
