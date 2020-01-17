#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/standardize-mobile-number-using-decorators

import io
import sys
import unittest


def decorate_numbers(numbers):
    def applier():
        return [f'+91 {number[-10:-5]} {number[-5:]}' for number in numbers]
    return applier


def standardize(numbers):
    numbers = [number[-10:] for number in numbers]
    numbers = sorted(numbers)
    decorated = decorate_numbers(numbers)
    return decorated()


def main():
    n = int(input().strip())
    numbers = []
    for _ in range(n):
        numbers.append(input().strip())
    for number in standardize(numbers):
        print(number)


if __name__ == '__main__':  # pragma: no cover
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
