#!/usr/bin/env python3

from .helper import io_checker

import unittest


def main():
    print(input())

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)
