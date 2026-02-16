import re
import sys
import tempfile
import unittest
from pathlib import Path
from typing import Iterator


def read_next_aminoacid(gcg_path: Path) -> Iterator[str]:
    with gcg_path.open() as handle:
        while (line := handle.readline()) != '':
            if line.startswith('     '):
                line = re.sub(r'[^acgt]+', '', line)
                yield from line


def read_next_quartet(packed_path: Path) -> Iterator[int]:
    with packed_path.open('rb') as handle:
        while quartet := handle.read(1):
            yield quartet[0]


def encode_quartet(quartet: str) -> int:
    encoded = 0
    for aminoacid in quartet:
        encoded = (encoded << 2) | 'acgt'.index(aminoacid)
    return encoded


def decode_quartet(quartet: int) -> str:
    decoded = ''
    for shift in range(6, -2, -2):
        aminoacid = (quartet >> shift) & 3
        decoded += 'acgt'[aminoacid]
    return decoded


def encode_file(path_in: Path, path_out: Path) -> None:
    with path_out.open('wb') as handle:
        handle.write(bytes([0]))
        quartet = ''
        for aminoacid in read_next_aminoacid(path_in):
            quartet += aminoacid
            if len(quartet) == 4:
                handle.write(bytes([encode_quartet(quartet)]))
                quartet = ''
        busy = len(quartet)
        if busy == 0:
            busy = 4
        else:
            while len(quartet) < 4:
                quartet += 'a'
            handle.write(bytes([encode_quartet(quartet)]))
        handle.seek(0)
        handle.write(bytes([busy]))


def decode_file(path_in: Path, path_out: Path) -> None:
    with path_out.open('w') as handle:
        size = path_in.stat().st_size
        busy = None
        for index, quartet in enumerate(read_next_quartet(path_in)):
            if busy is None:
                busy = quartet
            else:
                aminoacids = decode_quartet(quartet)
                if index == size - 1:
                    aminoacids = aminoacids[:busy]
                handle.write(aminoacids)


def main(path_in: Path, path_out: Path) -> None:
    if path_in.name.endswith('gcg'):
        encode_file(path_in, path_out)
    else:
        decode_file(path_in, path_out)


class TestCode(unittest.TestCase):
    def test_quartet(self) -> None:
        self.assertEqual(decode_quartet(encode_quartet('tcag')), 'tcag')

    def generic_equivalence(self, original_path: Path) -> None:
        with (
            tempfile.NamedTemporaryFile(suffix='.packed') as encoded_path,
            tempfile.NamedTemporaryFile(suffix='.txt') as decoded_path,
        ):
            main(original_path, Path(encoded_path.name))
            main(Path(encoded_path.name), Path(decoded_path.name))
            self.assertEqual(
                Path(decoded_path.name).read_text(encoding='utf-8'),
                ''.join(read_next_aminoacid(original_path)),
            )

    def test_encoding_and_decoding(self) -> None:
        original_path = Path(__file__).parent / 'input4.gcg'
        self.generic_equivalence(original_path)

    def test_encoding_and_decoding_padded(self):
        original_path = Path(__file__).parent / 'input1.gcg'
        self.generic_equivalence(original_path)


if __name__ == '__main__':  # pragma: no cover
    if len(sys.argv) >= 3:
        main(Path(sys.argv[1]), Path(sys.argv[2]))
