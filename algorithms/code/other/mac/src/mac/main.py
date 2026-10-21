import hashlib
import json
import logging
import os
import re
import tempfile
from collections.abc import Iterator
from enum import StrEnum
from pathlib import Path
from typing import Annotated, Any
from urllib.parse import unquote_plus

from fastapi import (
    APIRouter,
    BackgroundTasks,
    Depends,
    FastAPI,
    HTTPException,
    Request,
    Response,
    UploadFile,
    status,
)
from fastapi.exception_handlers import (
    http_exception_handler,
    request_validation_exception_handler,
)
from fastapi.exceptions import RequestValidationError
from fastapi.responses import PlainTextResponse
from pydantic import BaseModel, Field
from starlette.exceptions import HTTPException as StarletteHTTPException

from . import sanitization, storage
from .auth import require_auth
from .patterns import TASK_ID_PATTERN

logger = logging.getLogger("mac")
app = FastAPI()
filtering = APIRouter(prefix="/v1/filtering")
app.include_router(filtering)


TaskId = Annotated[str, Field(pattern=TASK_ID_PATTERN)]


class Role(StrEnum):
    API = "api"
    CONSUMER = "consumer"

    @classmethod
    def _missing_(cls, _: object) -> "Role":
        return cls.API


ROLE = Role(os.environ.get("MAC_ROLE", Role.API).strip().lower())
BACKGROUND_PROCESSING = os.environ.get(
    "MAC_BACKGROUND_PROCESSING", "true"
).strip().lower() in {"1", "true", "yes", "on"}


def process_file(task_id: TaskId) -> bool:
    with tempfile.NamedTemporaryFile(suffix=".tmp", delete=False) as tmp:
        tmp_path = Path(tmp.name)
    try:
        sanitization.write_mac_addresses(storage.UPLOADS_DIR / task_id, tmp_path)
    except BaseException as error:
        tmp_path.unlink(missing_ok=True)
        if isinstance(error, FileNotFoundError):
            return False
        raise

    storage.RESULTS_DIR.mkdir(parents=True, exist_ok=True)
    storage.copy_into_storage(tmp_path, storage.RESULTS_DIR / task_id)
    tmp_path.unlink(missing_ok=True)

    (storage.UPLOADS_DIR / task_id).unlink(missing_ok=True)
    return True


class UploadResponse(BaseModel):
    task_id: TaskId


class TasksResponse(BaseModel):
    task_ids: list[TaskId]


@filtering.post("", dependencies=[Depends(require_auth)])
async def upload_file(file: UploadFile, background: BackgroundTasks) -> UploadResponse:
    digest = hashlib.sha256()
    with tempfile.NamedTemporaryFile(suffix=".tmp") as tmp:
        while chunk := await file.read(storage.UPLOAD_CHUNK_SIZE):
            digest.update(chunk)
            tmp.write(chunk)
        # Later, copy_into_storage reads the path, so flush Python's buffer
        tmp.flush()
        task_id = digest.hexdigest()
        storage.UPLOADS_DIR.mkdir(parents=True, exist_ok=True)
        storage.copy_into_storage(Path(tmp.name), storage.UPLOADS_DIR / task_id)
    if BACKGROUND_PROCESSING:
        # Used only for local development,
        # deployed variant uses `/events` endpoint
        background.add_task(process_file, task_id)
    return UploadResponse(task_id=task_id)


@filtering.get("", dependencies=[Depends(require_auth)])
async def list_tasks() -> TasksResponse:
    deduplicated_task_ids: set[str] = set()
    for directory in (storage.UPLOADS_DIR, storage.RESULTS_DIR):
        if directory.is_dir():
            deduplicated_task_ids.update(
                entry.name for entry in directory.iterdir() if entry.is_file()
            )
    return TasksResponse(task_ids=list(deduplicated_task_ids))


@filtering.get(
    "/{task_id}",
    response_class=PlainTextResponse,
    dependencies=[Depends(require_auth)],
)
async def get_result(task_id: TaskId) -> str:
    result_path = storage.RESULTS_DIR / task_id
    if result_path.is_file():
        return result_path.read_text(encoding="utf-8")
    if (storage.UPLOADS_DIR / task_id).is_file():
        raise HTTPException(
            status.HTTP_202_ACCEPTED, detail="Task is still being processed"
        )
    raise HTTPException(status.HTTP_404_NOT_FOUND, detail="Task not found")


@filtering.delete(
    "/{task_id}",
    status_code=status.HTTP_204_NO_CONTENT,
    dependencies=[Depends(require_auth)],
)
async def delete_result(task_id: TaskId) -> None:
    deleted = False
    for directory in (storage.UPLOADS_DIR, storage.RESULTS_DIR):
        path = directory / task_id
        if path.is_file():
            path.unlink()
            deleted = True
    if not deleted:
        raise HTTPException(status.HTTP_404_NOT_FOUND, detail="Task not found")


class EventsResponse(BaseModel):
    processed: list[TaskId]
    skipped: list[str]


def _task_ids_in_sqs_from_s3(event: dict[str, Any]) -> Iterator[str]:
    for record in event.get("Records", []):
        notification = json.loads(record.get("body", "{}"))
        for s3_record in notification.get("Records", []):
            key = unquote_plus(s3_record["s3"]["object"]["key"])
            yield key.split("/")[-1]


if ROLE is Role.CONSUMER:
    # Excluded from basic auth since not exposed openly and called only by SQS
    @app.post("/events", status_code=status.HTTP_200_OK)
    def process_events(event: dict[str, Any]) -> EventsResponse:
        processed: list[TaskId] = []
        skipped: list[str] = []
        for task_id in _task_ids_in_sqs_from_s3(event):
            # The key comes from our own bucket, but it still reaches the
            # filesystem, so validate it
            if not re.fullmatch(TASK_ID_PATTERN, task_id):
                skipped.append(task_id)
            elif process_file(task_id):
                processed.append(task_id)
            else:
                # Already consumed or deleted - not a failure
                skipped.append(task_id)
        return EventsResponse(processed=processed, skipped=skipped)


@app.exception_handler(StarletteHTTPException)
async def log_http_exception(request: Request, exc: StarletteHTTPException) -> Response:
    if exc.status_code >= status.HTTP_400_BAD_REQUEST:
        level = (
            logging.ERROR
            if exc.status_code >= status.HTTP_500_INTERNAL_SERVER_ERROR
            else logging.WARNING
        )
        logger.log(
            level,
            "%s %s -> %d: %s",
            request.method,
            request.url.path,
            exc.status_code,
            exc.detail,
        )
    return await http_exception_handler(request, exc)


@app.exception_handler(RequestValidationError)
async def log_validation_exception(
    request: Request, exc: RequestValidationError
) -> Response:
    logger.warning("%s %s -> 422: %s", request.method, request.url.path, exc.errors())
    return await request_validation_exception_handler(request, exc)
