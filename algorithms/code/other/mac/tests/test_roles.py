import hashlib
import importlib
import json
from collections.abc import Iterator

import pytest
from fastapi import status
from fastapi.testclient import TestClient

from conftest import Storage
from mac import main

SAMPLE = b"00:0F:AC:15:20:13\n00-0f -ac-15 -20-13\njunk\n"
SAMPLE_ADDRESSES = "00:0F:AC:15:20:13\n"
TASK_ID = hashlib.sha256(SAMPLE).hexdigest()


def s3_event(*keys: str) -> dict:
    return {
        "Records": [
            {
                "messageId": f"message-{index}",
                "body": json.dumps(
                    {"Records": [{"s3": {"object": {"key": key}}}]},
                ),
            }
            for index, key in enumerate(keys)
        ]
    }


@pytest.fixture
def consumer(
    monkeypatch: pytest.MonkeyPatch, patch_storage: Storage
) -> Iterator[TestClient]:
    monkeypatch.setenv("MAC_ROLE", "consumer")
    importlib.reload(main)
    with TestClient(main.app) as test_client:
        yield test_client
    monkeypatch.delenv("MAC_ROLE")
    importlib.reload(main)


class TestRoleEnum:
    def test_default_is_the_api(self):
        assert main.Role(main.Role.API) is main.Role.API

    @pytest.mark.parametrize("value", ["consumr", "", "CONSUMER ", "api", "nonsense"])
    def test_only_an_exact_consumer_is_the_consumer(self, value: str):
        assert main.Role(value) is main.Role.API

    def test_the_exact_value_resolves(self):
        assert main.Role("consumer") is main.Role.CONSUMER


class TestEventsIsNotPublic:
    def test_route_is_absent_by_default(self, client: TestClient):
        assert main.ROLE is main.Role.API
        response = client.post("/events", json=s3_event())
        assert response.status_code == status.HTTP_404_NOT_FOUND

    def test_route_is_absent_from_the_openapi_schema(self, client: TestClient):
        assert "/events" not in client.get("/openapi.json").json()["paths"]

    def test_route_exists_on_the_consumer(self, consumer: TestClient):
        assert (
            consumer.post("/events", json=s3_event()).status_code == status.HTTP_200_OK
        )


class TestEventsHandling:
    def test_processes_the_task_named_by_the_object_key(
        self, consumer: TestClient, patch_storage: Storage
    ):
        patch_storage.add_upload(TASK_ID, SAMPLE)
        response = consumer.post("/events", json=s3_event(f"uploads/{TASK_ID}"))
        assert response.status_code == status.HTTP_200_OK
        assert response.json() == {"processed": [TASK_ID], "skipped": []}
        assert (patch_storage.results / TASK_ID).read_text() == SAMPLE_ADDRESSES

    def test_a_missing_upload_is_skipped_not_failed(self, consumer: TestClient):
        response = consumer.post("/events", json=s3_event(f"uploads/{TASK_ID}"))
        assert response.status_code == status.HTTP_200_OK
        assert response.json() == {"processed": [], "skipped": [TASK_ID]}

    @pytest.mark.parametrize(
        "key",
        [
            pytest.param("uploads/../../etc/passwd", id="traversal"),
            pytest.param("uploads/not-a-digest", id="not-hex"),
            pytest.param("uploads/" + "A" * 64, id="uppercase"),
            pytest.param("uploads/", id="empty"),
        ],
    )
    def test_keys_that_are_not_task_ids_are_skipped(
        self, consumer: TestClient, key: str
    ):
        assert consumer.post("/events", json=s3_event(key)).json()["processed"] == []

    def test_several_records_in_one_event(
        self, consumer: TestClient, patch_storage: Storage
    ):
        other = hashlib.sha256(SAMPLE + b"00:0F:AC:15:20:14\n").hexdigest()
        patch_storage.add_upload(TASK_ID, SAMPLE)
        patch_storage.add_upload(other, SAMPLE)
        processed = consumer.post(
            "/events", json=s3_event(f"uploads/{TASK_ID}", f"uploads/{other}")
        ).json()["processed"]
        assert sorted(processed) == sorted([TASK_ID, other])
