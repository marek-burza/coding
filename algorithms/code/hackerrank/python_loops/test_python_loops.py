#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-loops
# #python

import io
import sys
import unittest


def squares(n):
    i = 0
    while i < n:
        yield i * i
        i += 1


def main():
    for square in squares(int(input().strip())):
        print(square)


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
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
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_0(self):
        self.generalized_test('0')
