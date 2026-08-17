import tracemalloc
from collections.abc import Callable
from pathlib import Path

import pytest

from mac import sanitization

Extract = Callable[[bytes], list[bytes]]


STANDARD = b"00:0F:AC:15:20:13"
DASHED = b"00-0f-ac-15-20-13"
BARE = b"000fac152013"
OTHER = b"00:0F:AC:15:20:14"


@pytest.mark.parametrize(
    ("raw", "expected"),
    [
        pytest.param(STANDARD + b"\n", [STANDARD], id="standard-form"),
        pytest.param(DASHED + b"\n", [STANDARD], id="dash-separated"),
        pytest.param(BARE + b"\n", [STANDARD], id="no-separators"),
        pytest.param(
            b"00:0fAC-15:2013\n", [STANDARD], id="separators-off-octet-boundaries"
        ),
        pytest.param(b"", [], id="empty-file"),
        pytest.param(b"\n\n\n", [], id="blank-lines"),
        pytest.param(
            STANDARD + b"\n" + OTHER + b"\n",
            [STANDARD, OTHER],
            id="several-addresses",
        ),
    ],
)
def test_recognises_valid_addresses(
    extract: Extract, raw: bytes, expected: list[bytes]
):
    assert extract(raw) == expected


@pytest.mark.parametrize(
    ("raw", "reason"),
    [
        pytest.param(b"00:0F:AC:15:20:1\n", "11 digits", id="too-few-digits"),
        pytest.param(b"00:0F:AC:15:20:134\n", "13 digits", id="too-many-digits"),
        pytest.param(b"00:0F:AB:15:20:13\n", "not the 802.11 OUI", id="wrong-prefix"),
        pytest.param(b"15:20:13:44:55:66\n", "no OUI prefix at all", id="missing-oui"),
        pytest.param(
            b"00:0F:AC:15:20:1G\n", "G is not hexadecimal", id="non-hex-digit"
        ),
        pytest.param(
            b"000FAC152013000FAC152014\n",
            "two addresses mashed together; the line as a whole is not an address",
            id="concatenated-addresses",
        ),
    ],
)
def test_rejects_invalid_addresses(extract: Extract, raw: bytes, reason: str):
    assert extract(raw) == [], reason


@pytest.mark.parametrize(
    ("raw", "expected"),
    [
        pytest.param(b"00 :0F: AC:15 :2 0:1 3\n", [STANDARD], id="spaces-inside"),
        pytest.param(
            b"xyz" + STANDARD + b"tuv\n",
            [STANDARD],
            id="letters-around-address",
        ),
        pytest.param(STANDARD + b"\r\n", [STANDARD], id="carriage-return-stripped"),
        pytest.param(
            b"\xff\xfe" + STANDARD + b"\n", [STANDARD], id="undecodable-bytes"
        ),
    ],
)
def test_skips_noise_around_and_inside_addresses(
    extract: Extract, raw: bytes, expected: list[bytes]
):
    assert extract(raw) == expected


@pytest.mark.parametrize(
    ("raw", "expected"),
    [
        pytest.param(STANDARD, [STANDARD], id="single-line-no-newline"),
        pytest.param(
            STANDARD + b"\n" + OTHER,
            [STANDARD, OTHER],
            id="last-line-no-newline",
        ),
        pytest.param(b"00:0F:AC:15:20:1", [], id="invalid-at-eof"),
    ],
)
def test_eof_terminates_an_address_like_an_eol(
    extract: Extract, raw: bytes, expected: list[bytes]
):
    assert extract(raw) == expected


class TestDuplicates:
    def test_repeated_address_written_once(self, extract: Extract):
        assert extract(STANDARD + b"\n" + STANDARD + b"\n" + STANDARD + b"\n") == [
            STANDARD
        ]

    def test_every_spelling_is_the_same_address(self, extract: Extract):
        assert extract(STANDARD + b"\n" + DASHED + b"\n" + BARE + b"\n") == [STANDARD]

    def test_duplicate_arriving_at_eof(self, extract: Extract):
        assert extract(STANDARD + b"\n" + STANDARD) == [STANDARD]

    def test_distinct_addresses_are_all_kept(self, extract: Extract):
        assert extract(STANDARD + b"\n" + OTHER + b"\n") == [STANDARD, OTHER]


def peak_bytes(tmp_path: Path, payload: bytes) -> tuple[int, bytes]:
    source = tmp_path / f"source-{len(payload)}"
    destination = tmp_path / f"destination-{len(payload)}"
    source.write_bytes(payload)

    sanitization.write_mac_addresses(source, destination)  # Warm-up
    tracemalloc.start()
    try:
        sanitization.write_mac_addresses(source, destination)
        _, peak = tracemalloc.get_traced_memory()
    finally:
        tracemalloc.stop()

    return peak, destination.read_bytes()


class TestLongLines:
    def test_overlong_line_is_ignored(self, extract: Extract):
        assert extract(b"9" * 100_000 + b"\n") == []

    def test_recovers_on_the_next_line(self, extract: Extract):
        assert extract(b"9" * 100_000 + b"\n" + STANDARD + b"\n") == [STANDARD]

    def test_address_buried_in_an_overlong_line_is_not_extracted(
        self, extract: Extract
    ):
        assert extract(b"9" * 100_000 + STANDARD + b"\n") == []

    def test_one_enormous_line_does_not_grow_memory(self, tmp_path: Path):
        small, out = peak_bytes(tmp_path, b"9" * 30_000)
        large, _ = peak_bytes(tmp_path, b"9" * 300_000)

        assert out == b""
        assert large < small * 2, f"10x the input cost {large / small:.1f}x the memory"
