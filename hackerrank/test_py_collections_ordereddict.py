#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/py-collections-ordereddict

import collections


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
    for i in range(n):
        items = input().strip().split()
        product = ' '.join(items[:-1])
        payment = int(items[-1])
        purchases.append((product, payment))
    aggregated = aggregate(purchases)
    for product in aggregated:
        print('%s %d' % (product, aggregated[product]))

if __name__ == '__main__':
    main()
