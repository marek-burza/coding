#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/word-order

import collections
import io
import sys
import unittest


def orderly(words):
    unique = []
    counted = collections.Counter()
    for word in words:
        if word not in counted:
            unique.append(word)
        counted[word] += 1
    return unique, counted


def main():
    n = int(input().strip())
    words = []
    for _ in range(n):
        words.append(input().strip())
    unique, counted = orderly(words)
    print(len(unique))
    print(' '.join([str(counted[word]) for word in unique]))


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
