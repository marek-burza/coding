#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-arithmetic-operators

from .helper import io_checker

import random
import unittest


def mix(first, second):
    added = first + second
    subtracted = first - second
    multiplied = first * second
    return added, subtracted, multiplied


def main():
    first_in = int(input().strip())
    second_in = int(input().strip())
    added_out, subtracted_out, multiplied_out = mix(first_in, second_in)
    print(added_out)
    print(subtracted_out)
    print(multiplied_out)

if __name__ == '__main__':
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)

    def test_random(self):
        first = random.randint(1, 10000000000)
        second = random.randint(1, 10000000000)
        for i in range(100):
            added, subtracted, multiplied = mix(first, second)
            self.assertEqual(added, first + second)
            self.assertEqual(subtracted, first - second)
            self.assertEqual(multiplied, first * second)
