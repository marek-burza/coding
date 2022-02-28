#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/write-a-function
# #python

import io
import sys
import unittest


def is_leap(year):
    return (year % 4) == 0 and ((year % 100) != 0 or (year % 400) == 0)


def main():
    year_in = int(input().strip())
    print(is_leap(year_in))


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        sys.stdin = open(__file__.replace('.py', f'.{which}.in'), 'r')
        sys.stdout = io.StringIO()
        expected = open(__file__.replace('.py', f'.{which}.out'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_0(self):
        self.generalized_test('0')

    def test_several(self):
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
        for year in lut:
            self.assertEqual(is_leap(year), lut[year])
