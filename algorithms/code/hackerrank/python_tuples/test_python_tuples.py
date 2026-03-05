#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-tuples
# #python

import io
import sys
import unittest


def convert(integers: list[int]) -> str:
    return str(hash(tuple(integers)))


def main() -> None:
    n = int(input().strip())
    integers = list(map(int, input().strip().split(' ')))[:n]
    print(convert(integers))


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
