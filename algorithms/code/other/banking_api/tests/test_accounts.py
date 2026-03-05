import unittest

from banking.api.accounts import ERROR_INVALID_ACCOUNT_IDENTIFIER
from banking.main import app
from banking.schemas.accounts import ERROR_BALANCE_CANNOT_BE_NEGATIVE
from banking.utilities import is_identifier_valid
from fastapi import status
from fastapi.testclient import TestClient

client = TestClient(app)


class TestAccounts(unittest.TestCase):
    def test_successful_account_creation(self) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 10, 'customer_name': 'Bob Tenth'},
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/accounts/', params={'customer_identifier': 10, 'balance': 10000}
        )
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert is_identifier_valid(parsed_response['identifier'])
        assert parsed_response['balance'] == 10000
        assert parsed_response['customer_identifier'] == 10

    def test_unsuccessful_account_creation_with_negative_balance(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 11,
                'customer_name': 'Bob Eleventh',
            },
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/accounts/', params={'customer_identifier': 11, 'balance': -1}
        )
        assert response.status_code == status.HTTP_400_BAD_REQUEST
        assert response.json()['detail'] == ERROR_BALANCE_CANNOT_BE_NEGATIVE

    def test_unsuccessful_account_creation_without_customer(self) -> None:
        response = client.put('/accounts/', params={'customer_identifier': -1})
        assert response.status_code == status.HTTP_404_NOT_FOUND

    def test_successful_account_read_with_customer_and_account_ids(
        self,
    ) -> None:
        response = client.put(
            '/customers/',
            params={'customer_identifier': 12, 'customer_name': 'Bob Twelfth'},
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/accounts/', params={'customer_identifier': 12, 'balance': 12000}
        )
        assert response.status_code == status.HTTP_200_OK
        account_identifier = response.json()['identifier']
        response = client.get(
            '/accounts/',
            params={
                'customer_identifier': 12,
                'account_identifier': account_identifier,
            },
        )
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert len(parsed_response) == 1
        assert parsed_response[0]['identifier'] == account_identifier
        assert parsed_response[0]['customer_identifier'] == 12
        assert parsed_response[0]['balance'] == 12000

    def test_successful_account_read_with_customer_id_only(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 13,
                'customer_name': 'Bob Thirteenth',
            },
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/accounts/', params={'customer_identifier': 13, 'balance': 13000}
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.put(
            '/accounts/', params={'customer_identifier': 13, 'balance': 31000}
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.get('/accounts/', params={'customer_identifier': 13})
        assert response.status_code == status.HTTP_200_OK
        parsed_response = response.json()
        assert len(parsed_response) == 2
        assert sum(account['balance'] for account in parsed_response) == 44000

    def test_unsuccessful_account_read_with_invalid_account_id(self) -> None:
        response = client.put(
            '/customers/',
            params={
                'customer_identifier': 14,
                'customer_name': 'Bob Fourteenth',
            },
        )
        assert response.status_code == status.HTTP_200_OK
        response = client.get(
            '/accounts/',
            params={
                'customer_identifier': 14,
                'account_identifier': 'not-a-uuid',
            },
        )
        assert response.status_code == status.HTTP_417_EXPECTATION_FAILED
        assert response.json()['detail'] == ERROR_INVALID_ACCOUNT_IDENTIFIER

    def test_unsuccessful_account_read_with_non_existing_customer_id(
        self,
    ) -> None:
        response = client.get('/accounts/', params={'customer_identifier': -1})
        assert response.status_code == status.HTTP_404_NOT_FOUND
