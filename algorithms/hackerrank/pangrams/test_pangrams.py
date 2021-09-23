#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/pangrams

import io
import sys
import unittest


def pangram(text):
    text = text.lower()
    remaining = {chr(i) for i in range(ord('a'), ord('z') + 1)}
    for key in text:
        if key in remaining:
            remaining.remove(key)
        if len(remaining) == 0:
            return True
    return False


def main():
    print('pangram' if pangram(input().strip()) else 'not pangram')


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

    def test_1(self):
        self.generalized_test('1')
