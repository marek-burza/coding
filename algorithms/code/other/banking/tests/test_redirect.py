import unittest

from banking.main import app
from fastapi import status
from fastapi.testclient import TestClient

client = TestClient(app)


class TestIndex(unittest.TestCase):
    def test_index_redirect_to_docs(self) -> None:
        response = client.get('/')
        assert response.status_code == status.HTTP_200_OK
        assert 'openapi.json' in response.content.decode('utf-8')
