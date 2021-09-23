#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/collections-counter

import collections
import io
import sys
import unittest


def accountant(sizes, requests):
    counted = collections.Counter()
    counted.update(sizes)
    haul = 0
    for size, price in requests:
        if counted[size] > 0:
            haul += price
            counted[size] -= 1
    return haul


def main():
    input()
    sizes_in = list(map(int, input().strip().split(' ')))
    requests_in = []
    n = int(input().strip())
    for _ in range(n):
        request_in = list(map(int, input().strip().split(' ')))
        requests_in.append(request_in)
    print(accountant(sizes_in, requests_in))


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
