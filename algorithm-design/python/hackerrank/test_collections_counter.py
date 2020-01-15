#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/collections-counter

import collections


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

if __name__ == '__main__':
    main()
