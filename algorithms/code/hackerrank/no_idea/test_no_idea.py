#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/no-idea
# #python

import io
import sys
import unittest


def calculate_happiness(array, a_in, b_in):
    happiness = 0
    for item in array:
        if item in a_in:
            happiness += 1
        elif item in b_in:
            happiness -= 1
    return happiness


def main():
    input()  # n_in, m_in = list(map(int, input().strip().split(' ')))
    array = input().strip().split(' ')
    a_in = set(input().strip().split(' '))
    b_in = set(input().strip().split(' '))
    print(calculate_happiness(array, a_in, b_in))


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

    def test_01(self):
        self.generalized_test('01')
