from collections.abc import Callable, Iterator
from dataclasses import dataclass
from pathlib import Path

import pytest
from fastapi.testclient import TestClient

from mac import main, sanitization, storage


@dataclass
class Storage:
    uploads: Path
    results: Path

    def add_upload(self, task_id: str, content: bytes) -> Path:
        self.uploads.mkdir(parents=True, exist_ok=True)
        path = self.uploads / task_id
        path.write_bytes(content)
        return path

    def add_result(self, task_id: str, content: bytes) -> Path:
        self.results.mkdir(parents=True, exist_ok=True)
        path = self.results / task_id
        path.write_bytes(content)
        return path


@pytest.fixture(autouse=True)
def patch_storage(tmp_path: Path, monkeypatch: pytest.MonkeyPatch) -> Storage:
    uploads = tmp_path / "uploads"
    results = tmp_path / "results"
    monkeypatch.setattr(storage, "UPLOADS_DIR", uploads)
    monkeypatch.setattr(storage, "RESULTS_DIR", results)
    return Storage(uploads=uploads, results=results)


@pytest.fixture
def client() -> Iterator[TestClient]:
    with TestClient(main.app) as test_client:
        yield test_client


@pytest.fixture
def extract(tmp_path: Path) -> Callable[[bytes], list[bytes]]:

    def run(raw: bytes) -> list[bytes]:
        source = tmp_path / "source"
        destination = tmp_path / "destination"
        source.write_bytes(raw)
        sanitization.write_mac_addresses(source, destination)
        return destination.read_bytes().splitlines()

    return run
