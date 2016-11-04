#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/collections-counter

from .helper import io_checker

import collections
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
    for i in range(n):
        request_in = list(map(int, input().strip().split(' ')))
        requests_in.append(request_in)
    print(accountant(sizes_in, requests_in))

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)
