#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-print
# #python

import io
import sys
import unittest


def printer(n, file):
    list(map(lambda item: print(item, end='', file=file), range(1, n + 1)))


def main():
    printer(int(input().strip()), sys.stdout)
    print('')


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
