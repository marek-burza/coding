#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-deque
# #python

import collections
import io
import sys
import unittest
from typing import Deque


def operate(operations: list[list[str]]) -> Deque[str]:
    q: Deque[str] = collections.deque()
    for operation in operations:
        if operation[0] == 'append':
            q.append(operation[1])
        elif operation[0] == 'pop':
            q.pop()
        elif operation[0] == 'popleft':
            q.popleft()
        elif operation[0] == 'appendleft':
            q.appendleft(operation[1])
    return q


def main() -> None:
    n = int(input().strip())
    operations = []
    for _ in range(n):
        operation = input().strip().split()
        operations.append(operation)
    print(' '.join(operate(operations)))


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
