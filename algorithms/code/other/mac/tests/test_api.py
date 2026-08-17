import hashlib

import pytest
from conftest import Storage
from fastapi import status
from fastapi.testclient import TestClient

STANDARD = b"00:0F:AC:15:20:13"
DASHED = b"00-0f-ac-15-20-13"
OTHER = b"00:0F:AC:15:20:14"

SAMPLE = STANDARD + b"\n" + DASHED + b"\n" + b"junk\n" + OTHER + b"\n"
SAMPLE_ID = hashlib.sha256(SAMPLE).hexdigest()
SAMPLE_ADDRESSES = "00:0F:AC:15:20:13\n00:0F:AC:15:20:14\n"

UNKNOWN_ID = "a" * 64
MALFORMED_IDS = [
    pytest.param("too-short", id="not-hex"),
    pytest.param("A" * 64, id="uppercase-hex"),
    pytest.param("f" * 63, id="63-chars"),
    pytest.param("f" * 65, id="65-chars"),
    pytest.param("." * 64, id="dots"),
]

TRAVERSAL_IDS = [
    pytest.param("../../etc/passwd", id="raw"),
    pytest.param("%2e%2e%2f%2e%2e%2fetc%2fpasswd", id="url-encoded"),
]


def upload(client: TestClient, content: bytes = SAMPLE) -> str:
    response = client.post("/v1/filtering", files={"file": ("addresses.txt", content)})
    assert response.status_code == status.HTTP_200_OK
    return response.json()["task_id"]


class TestUpload:
    def test_returns_the_sha256_of_the_content(self, client: TestClient):
        assert upload(client) == SAMPLE_ID

    def test_identical_content_yields_the_same_task_id(self, client: TestClient):
        assert upload(client) == upload(client)

    def test_different_content_yields_different_task_ids(self, client: TestClient):
        assert upload(client) != upload(client, SAMPLE + b"00:0F:AC:15:20:15\n")

    def test_processes_the_file_in_the_background(
        self, client: TestClient, patch_storage: Storage
    ):
        task_id = upload(client)
        assert (patch_storage.results / task_id).read_text() == SAMPLE_ADDRESSES
        assert not (patch_storage.uploads / task_id).exists()


class TestGetResult:
    def test_returns_the_addresses_as_plain_text(self, client: TestClient):
        response = client.get(f"/v1/filtering/{upload(client)}")
        assert response.status_code == status.HTTP_200_OK
        assert response.text == SAMPLE_ADDRESSES
        assert response.headers["content-type"].startswith("text/plain")

    def test_reports_a_pending_task_as_accepted(
        self, client: TestClient, patch_storage: Storage
    ):
        patch_storage.add_upload(UNKNOWN_ID, SAMPLE)
        response = client.get(f"/v1/filtering/{UNKNOWN_ID}")
        assert response.status_code == status.HTTP_202_ACCEPTED

    def test_unknown_task_is_not_found(self, client: TestClient):
        response = client.get(f"/v1/filtering/{UNKNOWN_ID}")
        assert response.status_code == status.HTTP_404_NOT_FOUND

    @pytest.mark.parametrize("task_id", MALFORMED_IDS)
    def test_rejects_a_malformed_task_id(self, client: TestClient, task_id: str):
        response = client.get(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT


class TestDeleteResult:
    def test_deletes_a_finished_result(
        self, client: TestClient, patch_storage: Storage
    ):
        task_id = upload(client)
        response = client.delete(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_204_NO_CONTENT
        assert not (patch_storage.results / task_id).exists()
        response = client.get(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_404_NOT_FOUND

    def test_deletes_from_both_directories(
        self, client: TestClient, patch_storage: Storage
    ):
        patch_storage.add_upload(UNKNOWN_ID, SAMPLE)
        patch_storage.add_result(UNKNOWN_ID, SAMPLE)
        response = client.delete(f"/v1/filtering/{UNKNOWN_ID}")
        assert response.status_code == status.HTTP_204_NO_CONTENT
        assert not (patch_storage.uploads / UNKNOWN_ID).exists()
        assert not (patch_storage.results / UNKNOWN_ID).exists()

    def test_unknown_task_is_not_found(self, client: TestClient):
        response = client.delete(f"/v1/filtering/{UNKNOWN_ID}")
        assert response.status_code == status.HTTP_404_NOT_FOUND

    def test_deleting_twice_is_not_found_the_second_time(self, client: TestClient):
        task_id = upload(client)
        response = client.delete(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_204_NO_CONTENT
        response = client.delete(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_404_NOT_FOUND

    @pytest.mark.parametrize("task_id", MALFORMED_IDS)
    def test_rejects_a_malformed_task_id(self, client: TestClient, task_id: str):
        response = client.delete(f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT


class TestListTasks:
    def test_empty_when_nothing_has_been_uploaded(self, client: TestClient):
        assert client.get("/v1/filtering").json() == {"task_ids": []}

    def test_lists_finished_and_pending_tasks(
        self, client: TestClient, patch_storage: Storage
    ):
        finished = upload(client)
        patch_storage.add_upload(UNKNOWN_ID, SAMPLE)
        assert set(client.get("/v1/filtering").json()["task_ids"]) == {
            finished,
            UNKNOWN_ID,
        }

    def test_task_in_both_directories_is_listed_once(
        self, client: TestClient, patch_storage: Storage
    ):
        patch_storage.add_upload(UNKNOWN_ID, SAMPLE)
        patch_storage.add_result(UNKNOWN_ID, SAMPLE)
        assert client.get("/v1/filtering").json() == {"task_ids": [UNKNOWN_ID]}


class TestPathTraversal:
    @pytest.mark.parametrize("task_id", TRAVERSAL_IDS)
    @pytest.mark.parametrize("method", ["GET", "DELETE"])
    def test_traversal_is_never_served(
        self, client: TestClient, method: str, task_id: str
    ):
        response = client.request(method, f"/v1/filtering/{task_id}")
        assert response.status_code == status.HTTP_404_NOT_FOUND


class TestOpenApiSchema:
    def test_documents_the_response_bodies(self, client: TestClient):
        schemas = client.get("/openapi.json").json()["components"]["schemas"]
        assert "task_id" in schemas["UploadResponse"]["properties"]
        assert "task_ids" in schemas["TasksResponse"]["properties"]

    def test_documents_the_task_id_format(self, client: TestClient):
        schema = client.get("/openapi.json").json()["components"]["schemas"]
        assert schema["UploadResponse"]["properties"]["task_id"]["pattern"] == (
            r"^[0-9a-f]{64}$"
        )
