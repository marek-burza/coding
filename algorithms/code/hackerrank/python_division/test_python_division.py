#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-division
# #python

import io
import sys
import unittest


def divide(first: int, second: int) -> tuple[int, float]:
    int_val = first // second
    float_val = first / second
    return int_val, float_val


def main() -> None:
    first_in = int(input().strip())
    second_in = int(input().strip())
    int_out, float_out = divide(first_in, second_in)
    print(int_out)
    print(float_out)


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
            self.assertEqual(sys.stdout.getvalue(), expected.read())

    def test_0(self) -> None:
        self.generalized_test('0')
