#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/map-and-lambda-expression
# #python

import io
import sys
import unittest


def fibonacci(n: int) -> list[int]:
    listed = [0, 1]
    while len(listed) < n:
        listed.append(listed[-1] + listed[-2])
    return listed[:n]


def square(n: int) -> list[int]:
    listed = fibonacci(n)
    listed = list(map(lambda x: x * x * x, listed))
    return listed


def main() -> None:
    n = int(input().strip())
    print(square(n))


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
