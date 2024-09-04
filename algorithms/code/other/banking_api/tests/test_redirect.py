import unittest

from fastapi import status
from fastapi.testclient import TestClient

from banking.main import app

client = TestClient(app)


class TestIndex(unittest.TestCase):
    def test_index_redirect_to_docs(self) -> None:
        response = client.get('/')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertTrue('openapi.json' in response.content.decode('utf-8'))
