#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/pangrams
# #python

import io
import sys
import unittest


def pangram(text: str) -> bool:
    text = text.lower()
    remaining = {chr(i) for i in range(ord('a'), ord('z') + 1)}
    for key in text:
        if key in remaining:
            remaining.remove(key)
        if len(remaining) == 0:
            return True
    return False


def main() -> None:
    print('pangram' if pangram(input().strip()) else 'not pangram')


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

    def test_1(self) -> None:
        self.generalized_test('1')
