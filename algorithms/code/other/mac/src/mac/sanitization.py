from pathlib import Path

from .patterns import (
    ALLOWED_BYTES,
    MAC_ADDRESS,
    MAX_CANDIDATE_LENGTH,
    OCTET_DIGITS,
    STANDARD_SEPARATOR,
)
from .storage import StoragePath


def _standardized(address: bytes) -> bytes:
    digits = address.upper()
    return STANDARD_SEPARATOR.join(
        digits[index : index + OCTET_DIGITS]
        for index in range(0, len(digits), OCTET_DIGITS)
    )


def write_mac_addresses(source: StoragePath, destination: Path) -> None:
    seen: set[bytes] = set()
    line = b""

    def collect_once(candidate: bytes) -> None:
        if MAC_ADDRESS.fullmatch(candidate):
            address = _standardized(candidate)
            if address not in seen:
                dst.write(address + b"\n")
                seen.add(address)

    with source.open("rb") as src, destination.open("wb") as dst:
        while byte := src.read(1):
            if byte[0] not in ALLOWED_BYTES:
                continue
            if byte != b"\n":
                if len(line) <= MAX_CANDIDATE_LENGTH:
                    line += byte
                continue
            collect_once(line)
            line = b""
        collect_once(line)
