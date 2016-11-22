#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/most-commons

import collections


def mangle(s):
    counted = collections.Counter()
    for entry in s:
        counted[entry] += 1
    ordered = sorted(counted.keys(), key=lambda key: (counted[key] << 8) | (256 - ord(key)), reverse=True)
    return ordered, counted


def main():
    s = input().strip()
    ordered, counted = mangle(s)
    for entry in ordered[:3]:
        print('%s %d' % (entry, counted[entry]))

if __name__ == '__main__':
    main()
