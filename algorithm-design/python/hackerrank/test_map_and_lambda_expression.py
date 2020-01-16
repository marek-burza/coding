#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/map-and-lambda-expression

import io
import sys
import unittest


def fibonacci(n):
    listed = [0, 1]
    while len(listed) < n:
        listed.append(listed[-1] + listed[-2])
    return listed[:n]


def square(n):
    listed = fibonacci(n)
    listed = list(map(lambda x: x * x * x, listed))
    return listed


def main():
    n = int(input().strip())
    print(square(n))


if __name__ == '__main__':  # pragma: no cover
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
