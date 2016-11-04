#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-raw-input

from .helper import io_checker

import unittest


def is_weird(value):
    if (value % 2) == 1:
        return True
    elif 2 <= value <= 5:
        return False
    elif 6 <= value <= 20:
        return True
    return False


def main():
    print('Weird' if is_weird(int(input().strip())) else 'Not Weird')

if __name__ == "__main__":
    main()


class TestCode(unittest.TestCase):
    def test_0(self):
        io_checker(self, __file__, '0', main)

    def test_1_to_24(self):
        expected = [
            None, True, False, True, False, True, True, True, True, True,
            True, True, True, True, True, True, True, True, True, True,
            True, True, False, True, False]
        result = [None]
        for value in range(1, 25):
            result.append(is_weird(value))
        self.assertEqual(result, expected)
