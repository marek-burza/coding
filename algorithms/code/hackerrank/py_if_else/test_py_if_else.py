#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-if-else
# #python

import io
import sys
import unittest


def is_weird(value):
    if (value % 2) == 1:
        return True
    if 2 <= value <= 5:
        return False
    if 6 <= value <= 20:
        return True
    return False


def main():
    print('Weird' if is_weird(int(input().strip())) else 'Not Weird')


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

    def test_1_to_24(self):
        expected = [
            None,
            True,
            False,
            True,
            False,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            True,
            False,
            True,
            False,
        ]
        result = [None]
        for value in range(1, 25):
            result.append(is_weird(value))
        self.assertEqual(result, expected)
