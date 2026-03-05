#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/standardize-mobile-number-using-decorators
# #python

import io
import sys
import unittest
from typing import Callable


def wrapper(f: Callable) -> Callable:
    def fun(numbers: list[str]) -> None:
        numbers = [f'+91 {number[-10:-5]} {number[-5:]}' for number in numbers]
        f(numbers)

    return fun


@wrapper
def sort_phone(numbers: list[str]) -> None:
    print(*sorted(numbers), sep='\n')


def main() -> None:
    n = int(input().strip())
    numbers = [input() for _ in range(n)]
    sort_phone(numbers)


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
