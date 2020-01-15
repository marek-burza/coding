#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-arithmetic-operators

import os
import struct
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
    def test_random(self):
        first = struct.unpack('<L', os.urandom(4))[0]
        second = struct.unpack('<L', os.urandom(4))[0]
        for _ in range(100):
            added, subtracted, multiplied = mix(first, second)
            self.assertEqual(added, first + second)
            self.assertEqual(subtracted, first - second)
            self.assertEqual(multiplied, first * second)
