#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-tuples
# #python

import io
import sys
import unittest


def convert(integers):
    return str(hash(tuple(integers)))


def main():
    n = int(input().strip())
    integers = list(map(int, input().strip().split(' ')))[:n]
    print(convert(integers))


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
