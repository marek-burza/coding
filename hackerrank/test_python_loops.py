#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-loops

from .helper import io_checker

import unittest


def squares(n):
    i = 0
    while i < n:
        yield i * i
        i += 1


def main():
    for square in squares(int(input().strip())):
        print(square)

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)
