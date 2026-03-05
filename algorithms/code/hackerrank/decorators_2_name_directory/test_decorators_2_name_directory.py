#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/decorators-2-name-directory
# #python

import io
import operator
import sys
import unittest
from typing import Any, Callable


def title(gender: str) -> str:
    return 'Mr.' if gender == 'M' else 'Ms.'


def person_lister(f: Callable) -> Callable:
    def inner(people: list[list[Any]]) -> list[str]:
        people.sort(key=operator.itemgetter(2))
        return [f(person) for person in people]

    return inner


@person_lister
def name_format(person: list[Any]) -> str:
    return ' '.join([title(person[3]), person[0], person[1]])


def main() -> None:
    n = int(input().strip())
    people = [input().strip().split() for _ in range(n)]
    print(*name_format(people), sep='\n')


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
