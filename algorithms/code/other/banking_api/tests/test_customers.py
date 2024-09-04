import unittest

from fastapi import status
from fastapi.testclient import TestClient

from banking.api.customers import (
    ERROR_INVALID_CUSTOMER_NAME,
    ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS,
    ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS,
)
from banking.main import app

client = TestClient(app)


class TestCustomers(unittest.TestCase):
    def test_successful_customer_creation_and_sanitization(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 0, 'customer_name': 'Alice : "树"'},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        parsed_response = response.json()
        self.assertEqual(parsed_response['identifier'], 0)
        self.assertEqual(parsed_response['name'], 'Alice 树')

    def test_unsuccessful_customer_creation_with_same_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 1, 'customer_name': 'Alice First'},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.put(
            '/customers/',
            params={'customer_identifier': 1, 'customer_name': 'Alice Second'},
        )
        self.assertEqual(response.status_code, status.HTTP_409_CONFLICT)
        self.assertEqual(
            response.json()['detail'],
            ERROR_CUSTOMER_WITH_THIS_IDENTIFIER_ALREADY_EXISTS,
        )

    def test_unsuccessful_customer_creation_with_same_name(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 2,
                'customer_name': 'Alice Repeated',
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 3,
                'customer_name': 'Alice Repeated',
            },
        )
        self.assertEqual(response.status_code, status.HTTP_409_CONFLICT)
        self.assertEqual(
            response.json()['detail'],
            ERROR_CUSTOMER_WITH_THIS_NAME_ALREADY_EXISTS,
        )

    def test_unsuccessful_customer_creation_with_garbled_name(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 4, 'customer_name': '"/:.'},
        )
        self.assertEqual(
            response.status_code, status.HTTP_417_EXPECTATION_FAILED
        )
        self.assertEqual(
            response.json()['detail'], ERROR_INVALID_CUSTOMER_NAME
        )

    def test_successful_customer_read_with_known_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 5, 'customer_name': 'Alice Fifth'},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.get('/customers/', params={'customer_identifier': 5})
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        parsed_response = response.json()
        self.assertEqual(len(parsed_response), 1)
        self.assertEqual(parsed_response[0]['identifier'], 5)
        self.assertEqual(parsed_response[0]['name'], 'Alice Fifth')

    def test_successful_customer_read_without_id(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 6, 'customer_name': 'Alice Sixth'},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.get('/customers/')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        parsed_response = response.json()
        self.assertGreater(len(parsed_response), 0)
        self.assertTrue(
            any(customer['identifier'] == 6 for customer in parsed_response)
        )

    def test_unsuccessful_customer_read_with_unknown_id(self) -> None:
        response = client.get(
            '/customers/', params={'customer_identifier': -1}
        )
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)
