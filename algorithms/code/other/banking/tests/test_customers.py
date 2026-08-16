from fastapi import status
from fastapi.testclient import TestClient

from conftest import SEEDED_CUSTOMERS


def test_successful_customer_creation(client: TestClient) -> None:
    response = client.post("/customers", json={"name": "Alice First"})
    assert response.status_code == status.HTTP_201_CREATED
    body = response.json()
    assert body["name"] == "Alice First"
    assert body["id"] > len(SEEDED_CUSTOMERS)


def test_two_customers_may_share_a_name(client: TestClient) -> None:
    first = client.post("/customers", json={"name": "Alice Repeated"})
    second = client.post("/customers", json={"name": "Alice Repeated"})
    assert first.status_code == status.HTTP_201_CREATED
    assert second.status_code == status.HTTP_201_CREATED
    assert first.json()["id"] != second.json()["id"]


def test_customer_name_is_stored_exactly_as_sent(client: TestClient) -> None:
    response = client.post("/customers", json={"name": "Síne O'Brien-Ng"})
    assert response.status_code == status.HTTP_201_CREATED
    assert response.json()["name"] == "Síne O'Brien-Ng"


def test_unstorable_customer_name_is_rejected(client: TestClient) -> None:
    for name in ("", "   ", "Alice\x00Bob"):
        response = client.post("/customers", json={"name": name})
        assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
        assert response.json()["code"] == "invalid_request"


def test_seeded_customers_are_present_after_migration(client: TestClient) -> None:
    response = client.get("/customers")
    assert response.status_code == status.HTTP_200_OK
    names = [customer["name"] for customer in response.json()]
    assert names == list(SEEDED_CUSTOMERS)


def test_created_customer_is_listed(client: TestClient) -> None:
    created = client.post("/customers", json={"name": "Alice Sixth"}).json()
    response = client.get("/customers")
    assert response.status_code == status.HTTP_200_OK
    assert any(customer["id"] == created["id"] for customer in response.json())
