import unittest

from banking.api.transfers import (
    ERROR_DESTINATION_ACCOUNT_NOT_FOUND,
    ERROR_INSUFFICIENT_FUNDS,
    ERROR_INVALID_ACCOUNT_IDENTIFIER,
    ERROR_INVALID_DESTINATION_ACCOUNT_IDENTIFIER,
    ERROR_INVALID_SOURCE_ACCOUNT_IDENTIFIER,
    ERROR_SOURCE_ACCOUNT_NOT_FOUND,
    ERROR_THE_AMOUNT_MUST_BE_GREATER_THAN_ZERO,
)
from banking.main import app
from fastapi import status
from fastapi.testclient import TestClient

client = TestClient(app)


class TestTransfers(unittest.TestCase):
    def test_finally_successful_transfer_execution(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 15,
                'customer_name': 'Alice Fifteenth',
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 16,
                'customer_name': 'Bob Sixteenth',
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.put(
            '/accounts/',
            params={'customer_identifier': 15, 'balance': 10000},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        from_account_identifier = response.json()['identifier']
        response = client.put('/accounts/', params={'customer_identifier': 16})
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        to_account_identifier = response.json()['identifier']
        # Attempt transfer non-positive amount
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': from_account_identifier,
                'to_account_identifier': to_account_identifier,
                'amount': -1,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
        self.assertEqual(
            response.json()['detail'],
            ERROR_THE_AMOUNT_MUST_BE_GREATER_THAN_ZERO,
        )
        # Attempt transfer too high amount
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': from_account_identifier,
                'to_account_identifier': to_account_identifier,
                'amount': 20000,
            },
        )
        self.assertEqual(
            response.status_code, status.HTTP_412_PRECONDITION_FAILED
        )
        self.assertEqual(response.json()['detail'], ERROR_INSUFFICIENT_FUNDS)
        # Attempt transfer with invalid account identifiers
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': 'not-a-valid-uuid',
                'to_account_identifier': to_account_identifier,
                'amount': 1,
            },
        )
        self.assertEqual(
            response.status_code, status.HTTP_417_EXPECTATION_FAILED
        )
        self.assertEqual(
            response.json()['detail'],
            ERROR_INVALID_SOURCE_ACCOUNT_IDENTIFIER,
        )
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': from_account_identifier,
                'to_account_identifier': 'not-a-valid-uuid',
                'amount': 1,
            },
        )
        self.assertEqual(
            response.status_code, status.HTTP_417_EXPECTATION_FAILED
        )
        self.assertEqual(
            response.json()['detail'],
            ERROR_INVALID_DESTINATION_ACCOUNT_IDENTIFIER,
        )
        # Attempt transfer with between non-existing accounts
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': (
                    '00000000-0000-0000-0000-000000000000'
                ),
                'to_account_identifier': to_account_identifier,
                'amount': 1,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)
        self.assertEqual(
            response.json()['detail'], ERROR_SOURCE_ACCOUNT_NOT_FOUND
        )
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': from_account_identifier,
                'to_account_identifier': (
                    '00000000-0000-0000-0000-000000000000'
                ),
                'amount': 1,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)
        self.assertEqual(
            response.json()['detail'], ERROR_DESTINATION_ACCOUNT_NOT_FOUND
        )
        # Finally a valid transfer
        response = client.put(
            '/transfers/',
            params={
                'from_account_identifier': from_account_identifier,
                'to_account_identifier': to_account_identifier,
                'amount': 5000,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = client.get(
            '/accounts/',
            params={
                'customer_identifier': 15,
                'account_identifier': from_account_identifier,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json()[0]['balance'], 5000)
        response = client.get(
            '/accounts/',
            params={
                'customer_identifier': 16,
                'account_identifier': to_account_identifier,
            },
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json()[0]['balance'], 5000)
        # Attempt fetching transfers with invalid account identifier
        response = client.get(
            '/transfers/',
            params={'account_identifier': 'not-a-valid-uuid'},
        )
        self.assertEqual(
            response.status_code, status.HTTP_417_EXPECTATION_FAILED
        )
        self.assertEqual(
            response.json()['detail'], ERROR_INVALID_ACCOUNT_IDENTIFIER
        )
        # Check transfers
        response = client.get(
            '/transfers/', params={'account_identifier': to_account_identifier}
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        list1 = [transfer['identifier'] for transfer in response.json()]
        response = client.get(
            '/transfers/',
            params={'account_identifier': from_account_identifier},
        )
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        list2 = [transfer['identifier'] for transfer in response.json()]
        self.assertListEqual(list1, list2)
