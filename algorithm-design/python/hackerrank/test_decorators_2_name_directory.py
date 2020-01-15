#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/decorators-2-name-directory

import io
import operator
import sys
import unittest


def title(gender):
    return 'Mr.' if gender == 'M' else 'Ms.'


def decorate_gender(entries):
    def applier():
        items = [[title(entry[3]), entry[0], entry[1]] for entry in entries]
        return [' '.join(item) for item in items]
    return applier


def standardize(entries):
    entries.sort(key=operator.itemgetter(2))
    decorated = decorate_gender(entries)
    return decorated()


def main():
    n = int(input().strip())
    entries = []
    for _ in range(n):
        entries.append(input().strip().split())
    for entry in standardize(entries):
        print(entry)


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
