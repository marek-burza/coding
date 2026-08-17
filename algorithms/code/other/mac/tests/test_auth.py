import base64
import hashlib
import importlib
from collections.abc import Iterator

import pytest
from fastapi import status
from fastapi.testclient import TestClient

from conftest import Storage
from mac import auth, main

PASSWORD = "s3cr3t-token-that-is-not-guessable"  # nosec B105 # test fixture, not a real credential
USERNAME = "mac"
SALT = bytes.fromhex("404e26599ac6378e19b7543f5ac9f72a")
STORED = f"{USERNAME}:{SALT.hex()}:{auth.hash_password(PASSWORD, SALT)}"

SAMPLE = b"00-0f-ac-15-20-13\n"
TASK_ID = hashlib.sha256(SAMPLE).hexdigest()

PROTECTED_ROUTES = [
    pytest.param("POST", "/v1/filtering", id="upload"),
    pytest.param("GET", "/v1/filtering", id="tasks"),
    pytest.param("GET", f"/v1/filtering/{TASK_ID}", id="get-result"),
    pytest.param("DELETE", f"/v1/filtering/{TASK_ID}", id="delete-result"),
]


@pytest.fixture
def secured(
    monkeypatch: pytest.MonkeyPatch, patch_storage: Storage
) -> Iterator[TestClient]:
    monkeypatch.setenv("MAC_BASIC_AUTH", STORED)
    importlib.reload(auth)
    with TestClient(main.app) as test_client:
        yield test_client
    monkeypatch.delenv("MAC_BASIC_AUTH")
    importlib.reload(auth)


def basic(username: str, password: str) -> dict[str, str]:
    token = base64.b64encode(f"{username}:{password}".encode()).decode()
    return {"Authorization": f"Basic {token}"}


class TestDisabledByDefault:
    def test_no_credentials_needed(self, client: TestClient):
        assert auth.BASIC_AUTH == ""
        assert client.get("/v1/filtering").status_code == status.HTTP_200_OK


class TestRejectsAnonymous:
    @pytest.mark.parametrize(("method", "path"), PROTECTED_ROUTES)
    def test_every_public_route_demands_credentials(
        self, secured: TestClient, method: str, path: str
    ):
        response = secured.request(method, path)
        assert response.status_code == status.HTTP_401_UNAUTHORIZED
        assert response.headers["www-authenticate"] == "Basic"


class TestRejectsWrongCredentials:
    def test_wrong_password(self, secured: TestClient):
        response = secured.get("/v1/filtering", headers=basic(USERNAME, "wrong"))
        assert response.status_code == status.HTTP_401_UNAUTHORIZED

    def test_wrong_username(self, secured: TestClient):
        response = secured.get("/v1/filtering", headers=basic("someone", PASSWORD))
        assert response.status_code == status.HTTP_401_UNAUTHORIZED


class TestAcceptsCorrectCredentials:
    @pytest.mark.parametrize(("method", "path"), PROTECTED_ROUTES)
    def test_every_public_route_accepts_them(
        self, secured: TestClient, method: str, path: str
    ):
        response = secured.request(method, path, headers=basic(USERNAME, PASSWORD))
        assert response.status_code != status.HTTP_401_UNAUTHORIZED

    def test_a_full_round_trip(self, secured: TestClient, patch_storage: Storage):
        auth = basic(USERNAME, PASSWORD)
        task_id = secured.post(
            "/v1/filtering", files={"file": ("addresses.txt", SAMPLE)}, headers=auth
        ).json()["task_id"]
        assert (
            secured.get(f"/v1/filtering/{task_id}", headers=auth).text
            == "00:0F:AC:15:20:13\n"
        )


class TestEventsIsNotProtected:
    def test_events_needs_no_credentials_on_the_consumer(
        self, monkeypatch: pytest.MonkeyPatch, patch_storage: Storage
    ):
        monkeypatch.setenv("MAC_BASIC_AUTH", STORED)
        monkeypatch.setenv("MAC_ROLE", "consumer")
        importlib.reload(auth)  # MAC_BASIC_AUTH
        importlib.reload(main)  # MAC_ROLE and registers /events
        try:
            with TestClient(main.app) as client:
                response = client.post("/events", json={"Records": []})
                assert response.status_code == status.HTTP_200_OK
        finally:
            monkeypatch.delenv("MAC_BASIC_AUTH")
            monkeypatch.delenv("MAC_ROLE")
            importlib.reload(auth)
            importlib.reload(main)
