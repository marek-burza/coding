#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-tuples

import io
import sys
import unittest


def convert(integers):
    return '%d' % hash(tuple(integers))


def main():
    n = int(input().strip())
    integers = list(map(int, input().strip().split(' ')))[:n]
    print(convert(integers))


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
