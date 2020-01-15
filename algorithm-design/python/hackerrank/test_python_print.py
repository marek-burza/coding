#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-print

import io
import sys
import unittest


def printer(n, file):
    list(map(lambda item: print(item, end='', file=file), range(1, n + 1)))


def main():
    printer(int(input().strip()), sys.stdout)
    print('')


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
