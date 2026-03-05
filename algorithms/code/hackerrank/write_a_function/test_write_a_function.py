#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/write-a-function
# #python

import io
import sys
import unittest


def is_leap(year: int) -> bool:
    return (year % 4) == 0 and ((year % 100) != 0 or (year % 400) == 0)


def main() -> None:
    year_in = int(input().strip())
    print(is_leap(year_in))


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

    def test_several(self) -> None:
        lut = {
            1800: False,
            1900: False,
            2000: True,
            2100: False,
            2200: False,
            2300: False,
            2400: True,
            2500: False,
        }
        for year, lut_year in lut.items():
            assert is_leap(year) == lut_year
