#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-ordereddict

import collections
import io
import sys
import unittest


def aggregate(purchases):
    aggregated = collections.OrderedDict()
    for product, payment in purchases:
        if product in aggregated:
            aggregated[product] += payment
        else:
            aggregated[product] = payment
    return aggregated


def main():
    n = int(input().strip())
    purchases = []
    for _ in range(n):
        items = input().strip().split()
        product = ' '.join(items[:-1])
        payment = int(items[-1])
        purchases.append((product, payment))
    aggregated = aggregate(purchases)
    for product in aggregated:
        print('%s %d' % (product, aggregated[product]))


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
