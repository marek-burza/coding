#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/incorrect-regex
# #python

import io
import re
import sre_constants
import sys
import unittest


def check(pattern):
    try:
        re.compile(pattern)
        return True
    except sre_constants.error:  # type: ignore[no-member]
        return False


def main():
    n = int(input().strip())
    for _ in range(n):
        print(check(input().strip()))


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
