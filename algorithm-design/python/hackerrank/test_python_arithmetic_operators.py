#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/python-arithmetic-operators

import io
import os
import struct
import sys
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
    def generalized_test(self, which):
        sys.stdin = open(__file__.replace('.py', f'.{which}.in'), 'r')
        sys.stdout = io.StringIO()
        expected = open(__file__.replace('.py', f'.{which}.out'), 'r')
        main()
        self.assertEqual(sys.stdout.getvalue(), expected.read())
        for handle in [sys.stdin, sys.stdout, expected]:
            handle.close()

    def test_0(self):
        self.generalized_test('0')

    def test_random(self):
        first = struct.unpack('<L', os.urandom(4))[0]
        second = struct.unpack('<L', os.urandom(4))[0]
        for _ in range(100):
            added, subtracted, multiplied = mix(first, second)
            self.assertEqual(added, first + second)
            self.assertEqual(subtracted, first - second)
            self.assertEqual(multiplied, first * second)
