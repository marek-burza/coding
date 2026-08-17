import hashlib
from collections.abc import Iterator

import pytest
from cloudpathlib.local import LocalS3Client, LocalS3Path
from fastapi import status
from fastapi.testclient import TestClient
from test_sample_files import ASSETS

from mac import main, storage

SAMPLE = b"00:0F:AC:15:20:13\n00-0f -ac-15 -20-13\njunk\n00:0F:AC:15:20:14\n"
SAMPLE_ADDRESSES = "00:0F:AC:15:20:13\n00:0F:AC:15:20:14\n"
UNKNOWN_ID = "b" * 64


@pytest.fixture
def s3_client(client: TestClient) -> Iterator[TestClient]:
    LocalS3Client.reset_default_storage_dir()
    bucket = LocalS3Path("s3://mac-test-bucket")
    storage.UPLOADS_DIR = bucket / "uploads"
    storage.RESULTS_DIR = bucket / "results"
    yield client
    LocalS3Client.reset_default_storage_dir()


def upload(client: TestClient, content: bytes = SAMPLE) -> str:
    response = client.post("/v1/filtering", files={"file": ("addresses.txt", content)})
    assert response.status_code == status.HTTP_200_OK
    return response.json()["task_id"]


def test_storage_is_a_cloud_path(s3_client: TestClient):
    assert isinstance(storage.UPLOADS_DIR, LocalS3Path)
    assert isinstance(storage.RESULTS_DIR, LocalS3Path)


def test_upload_writes_an_object_named_by_its_digest(s3_client: TestClient):
    task_id = upload(s3_client)
    assert task_id == hashlib.sha256(SAMPLE).hexdigest()


def test_upload_is_processed_and_the_upload_consumed(s3_client: TestClient):
    task_id = upload(s3_client)
    assert (storage.RESULTS_DIR / task_id).read_text() == SAMPLE_ADDRESSES
    assert not (storage.UPLOADS_DIR / task_id).is_file()


def test_get_result_returns_the_addresses(s3_client: TestClient):
    response = s3_client.get(f"/v1/filtering/{upload(s3_client)}")
    assert response.status_code == status.HTTP_200_OK
    assert response.text == SAMPLE_ADDRESSES


def test_pending_task_reports_accepted(s3_client: TestClient):
    (storage.UPLOADS_DIR / UNKNOWN_ID).write_bytes(SAMPLE)
    response = s3_client.get(f"/v1/filtering/{UNKNOWN_ID}")
    assert response.status_code == status.HTTP_202_ACCEPTED


def test_unknown_task_is_not_found(s3_client: TestClient):
    response = s3_client.get(f"/v1/filtering/{UNKNOWN_ID}")
    assert response.status_code == status.HTTP_404_NOT_FOUND


def test_list_tasks_reads_both_prefixes(s3_client: TestClient):
    finished = upload(s3_client)
    (storage.UPLOADS_DIR / UNKNOWN_ID).write_bytes(SAMPLE)
    assert set(s3_client.get("/v1/filtering").json()["task_ids"]) == {
        finished,
        UNKNOWN_ID,
    }


def test_list_tasks_is_empty_before_anything_is_uploaded(s3_client: TestClient):
    assert s3_client.get("/v1/filtering").json() == {"task_ids": []}


def test_delete_removes_the_object(s3_client: TestClient):
    task_id = upload(s3_client)
    response = s3_client.delete(f"/v1/filtering/{task_id}")
    assert response.status_code == status.HTTP_204_NO_CONTENT
    assert not (storage.RESULTS_DIR / task_id).is_file()
    response = s3_client.get(f"/v1/filtering/{task_id}")
    assert response.status_code == status.HTTP_404_NOT_FOUND


def test_process_file_against_cloud_storage(s3_client: TestClient):
    (storage.UPLOADS_DIR / UNKNOWN_ID).write_bytes(SAMPLE)
    assert main.process_file(UNKNOWN_ID) is True  # Pretend S3 event + SQS queue
    assert (storage.RESULTS_DIR / UNKNOWN_ID).read_text() == SAMPLE_ADDRESSES
    assert not (storage.UPLOADS_DIR / UNKNOWN_ID).is_file()


def test_process_file_reports_a_missing_upload(s3_client: TestClient):
    assert main.process_file(UNKNOWN_ID) is False
