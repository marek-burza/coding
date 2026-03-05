import unittest

from banking.api.customers import (
    ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS,
    ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS,
    ERROR_INVALID_CUSTOMER_NAME,
)
from banking.main import app
from fastapi import status
from fastapi.testclient import TestClient

client = TestClient(app)


class TestCustomers(unittest.TestCase):
    def test_successful_customer_creation_and_sanitization(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 0, 'customer_name': 'Alice : "树"'},
        )
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert parsed_response['identifier'] == 0
        assert parsed_response['name'] == 'Alice 树'

    def test_unsuccessful_customer_creation_with_same_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 1, 'customer_name': 'Alice First'},
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/customers/',
            params={'customer_identifier': 1, 'customer_name': 'Alice Second'},
        )
        assert response.status_code == status.HTTP_409_CONFLICT
        assert (
            response.json()['detail']
            == ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS
        )

    def test_unsuccessful_customer_creation_with_same_name(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 2,
                'customer_name': 'Alice Repeated',
            },
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 3,
                'customer_name': 'Alice Repeated',
            },
        )
        assert response.status_code == status.HTTP_409_CONFLICT
        assert (
            response.json()['detail']
            == ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS
        )

    def test_unsuccessful_customer_creation_with_garbled_name(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 4, 'customer_name': '"/:.'},
        )
        assert response.status_code == status.HTTP_417_EXPECTATION_FAILED
        assert response.json()['detail'] == ERROR_INVALID_CUSTOMER_NAME

    def test_successful_customer_read_with_known_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 5, 'customer_name': 'Alice Fifth'},
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.get('/customers/', params={'customer_identifier': 5})
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert len(parsed_response) == 1
        assert parsed_response[0]['identifier'] == 5
        assert parsed_response[0]['name'] == 'Alice Fifth'

    def test_successful_customer_read_without_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 6, 'customer_name': 'Alice Sixth'},
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.get('/customers/')
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert len(parsed_response) > 0
        assert any(customer['identifier'] == 6 for customer in parsed_response)

    def test_unsuccessful_customer_read_with_unknown_id(self) -> None:
        response = client.get(
            '/customers/', params={'customer_identifier': -1}
        )
        assert response.status_code == status.HTTP_404_NOT_FOUND
