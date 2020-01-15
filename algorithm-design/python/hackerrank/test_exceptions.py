#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/exceptions

import io
import sys
import unittest


def exempt(a, b):
    try:
        return int(a) // int(b)
    except ValueError as exception:
        return 'Error Code: %s' % str(exception)
    except ZeroDivisionError as exception:
        return 'Error Code: %s' % str(exception)


def main():
    n = int(input().strip())
    for _ in range(n):
        a, b = input().strip().split()
        print(exempt(a, b))


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
