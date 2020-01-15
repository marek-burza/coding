#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-deque

import collections
import io
import sys
import unittest


def operate(operations):
    q = collections.deque()
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


def main():
    n = int(input().strip())
    operations = []
    for _ in range(n):
        operation = input().strip().split()
        operations.append(operation)
    print(' '.join(operate(operations)))


if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which):
        sys.stdin = open(__file__.replace('.py', f'.{which}.in'), 'r')
        sys.stdout = io.StringIO()
        expected = open(__file__.replace('.py', f'.{which}.out'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_0(self):
        self.generalized_test('0')
