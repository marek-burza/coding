#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-print

from .helper import io_checker

import sys
import unittest


def printer(n, file):
    list(map(lambda item: print(item, end='', file=file), range(1, n + 1)))


def main():
    printer(int(input().strip()), sys.stdout)
    print('')

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)
