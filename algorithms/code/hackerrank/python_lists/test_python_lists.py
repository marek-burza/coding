#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-lists
# #python

import io
import sys
import unittest


def process(operations):
    listed = []
    for operation in operations:
        if operation[0] == 'insert':
            listed.insert(operation[1], operation[2])
        elif operation[0] == 'print':
            print(listed)
        elif operation[0] == 'remove':
            listed.remove(operation[1])
        elif operation[0] == 'append':
            listed.append(operation[1])
        elif operation[0] == 'sort':
            listed.sort()
        elif operation[0] == 'pop':
            listed.pop()
        elif operation[0] == 'reverse':
            listed.reverse()


def main():
    n = int(input().strip())
    operations = []
    for _ in range(n):
        operation = input().strip().split(' ')
        if operation[0] in ['insert', 'remove', 'append']:
            operation[1] = int(operation[1])
            if operation[0] == 'insert':
                operation[2] = int(operation[2])
        operations.append(operation)
    process(operations)


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
