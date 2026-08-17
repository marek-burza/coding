import hashlib
import re
from pathlib import Path

import pytest
from fastapi import status
from fastapi.testclient import TestClient

ASSETS = Path(__file__).parent / "assets"
SAMPLE_FILES = ASSETS.glob("mac_addresses_*.txt")
REFERENCE = re.compile(rb"(?im)^0[:-]?0[:-]?0[:-]?F[:-]?A[:-]?C(?:[:-]?[0-9A-F]){6}$")


def reference_addresses(raw: bytes) -> list[str]:
    seen: dict[str, None] = {}
    for match in REFERENCE.findall(raw.replace(b" ", b"").replace(b"\r", b"")):
        digits = match.translate(None, b":-").upper()
        address = b":".join(digits[index : index + 2] for index in range(0, 12, 2))
        seen.setdefault(address.decode(), None)
    return list(seen)


@pytest.mark.parametrize("path", SAMPLE_FILES, ids=lambda path: path.stem)
def test_sample_file_round_trip(client: TestClient, path: Path):
    raw = path.read_bytes()

    task_id = client.post("/v1/filtering", files={"file": (path.name, raw)}).json()[
        "task_id"
    ]
    assert task_id == hashlib.sha256(raw).hexdigest()

    response = client.get(f"/v1/filtering/{task_id}")
    assert response.status_code == status.HTTP_200_OK

    addresses = response.text.splitlines()
    assert addresses == reference_addresses(raw)
    assert len(addresses) == len(set(addresses))
    assert all(
        re.fullmatch(r"00:0F:AC(?::[0-9A-F]{2}){3}", address) for address in addresses
    )
