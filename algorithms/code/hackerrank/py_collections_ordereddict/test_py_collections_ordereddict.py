#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-ordereddict
# #python

import collections
import io
import sys
import unittest


def aggregate(purchases: list[tuple[str, int]]) -> collections.OrderedDict:
    aggregated: collections.OrderedDict[str, int] = collections.OrderedDict()
    for product, payment in purchases:
        if product in aggregated:
            aggregated[product] += payment
        else:
            aggregated[product] = payment
    return aggregated


def main() -> None:
    n = int(input().strip())
    purchases = []
    for _ in range(n):
        items = input().strip().split()
        product = ' '.join(items[:-1])
        payment = int(items[-1])
        purchases.append((product, payment))
    aggregated = aggregate(purchases)
    for product in aggregated:
        print(f'{product} {aggregated[product]}')


if __name__ == '__main__':  # pragma: no cover
    main()


class TestCode(unittest.TestCase):
    def generalized_test(self, which: str) -> None:
        with (
            open(
                __file__.replace('.py', f'.{which}.out'), 'r', encoding='utf-8'
            ) as expected,
            open(
                __file__.replace('.py', f'.{which}.in'), 'r', encoding='utf-8'
            ) as sys.stdin,
            io.StringIO() as sys.stdout,
        ):
            main()
            assert sys.stdout.getvalue() == expected.read()

    def test_0(self) -> None:
        self.generalized_test('0')
