#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-division

from .helper import io_checker

import unittest


def divide(first, second):
    int_val = first // second
    float_val = first / second
    return int_val, float_val


def main():
    first_in = int(input().strip())
    second_in = int(input().strip())
    int_out, float_out = divide(first_in, second_in)
    print(int_out)
    print(float_out)

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)
