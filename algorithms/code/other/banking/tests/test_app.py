from fastapi import status
from fastapi.testclient import TestClient


def test_index_redirect_to_docs(client: TestClient) -> None:
    response = client.get("/")
    assert response.status_code == status.HTTP_200_OK
    assert "openapi.json" in response.content.decode("utf-8")


def test_health(client: TestClient) -> None:
    response = client.get("/health")
    assert response.status_code == status.HTTP_200_OK
    assert response.json() == {"status": "ok"}
