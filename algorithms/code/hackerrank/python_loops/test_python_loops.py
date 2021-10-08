#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-loops
# #python

import io
import sys
import unittest


def squares(n):
    i = 0
    while i < n:
        yield i * i
        i += 1


def main():
    for square in squares(int(input().strip())):
        print(square)


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
