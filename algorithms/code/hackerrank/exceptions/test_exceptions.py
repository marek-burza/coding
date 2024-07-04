#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/exceptions
# #python

import io
import sys
import unittest


def exempt(a, b):
    try:
        return int(a) // int(b)
    except ValueError as exception:
        return f'Error Code: {str(exception)}'
    except ZeroDivisionError as exception:
        return f'Error Code: {str(exception)}'


def main():
    n = int(input().strip())
    for _ in range(n):
        a, b = input().strip().split()
        print(exempt(a, b))


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
