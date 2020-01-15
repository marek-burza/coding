#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/most-commons

import collections
import io
import sys
import unittest


def mangle(s):
    counted = collections.Counter()
    for entry in s:
        counted[entry] += 1
    ordered = sorted(
        counted.keys(),
        key=lambda key: (counted[key] << 8) | (256 - ord(key)),
        reverse=True)
    return ordered, counted


def main():
    s = input().strip()
    ordered, counted = mangle(s)
    for entry in ordered[:3]:
        print('%s %d' % (entry, counted[entry]))


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
