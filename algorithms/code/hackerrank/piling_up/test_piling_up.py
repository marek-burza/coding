#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/piling-up
# #python

import collections
import io
import sys
import unittest


def check(cubes):
    q = collections.deque(cubes)
    previous = None
    while q:
        left = q[0]
        right = q[-1]
        if left < right:
            current = q.pop()
        else:
            current = q.popleft()
        if previous is not None and previous < current:
            return False
        previous = current
    return True


def main():
    t = int(input().strip())
    for _ in range(t):
        n = int(input().strip())
        cubes = list(map(int, input().strip().split()))[:n]
        print('Yes' if check(cubes) else 'No')


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
