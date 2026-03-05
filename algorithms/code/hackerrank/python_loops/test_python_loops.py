#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-loops
# #python

import io
import sys
import unittest
from typing import Iterator


def squares(n: int) -> Iterator[int]:
    i = 0
    while i < n:
        yield i * i
        i += 1


def main() -> None:
    for square in squares(int(input().strip())):
        print(square)


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which: str) -> None:
        with (
            open(
                __file__.replace('.py', f'.{which}.out'), 'r', encoding='utf-8'
            ) as expected,
            open(
                __file__.replace('.py', f'.{which}.in'), 'r', encoding='utf-8'
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            assert sys.stdout.getvalue() == expected.read()

    def test_0(self) -> None:
        self.generalized_test('0')
