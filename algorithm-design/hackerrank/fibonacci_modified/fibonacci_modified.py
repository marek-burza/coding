#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/fibonacci-modified

import unittest


def fibonacci_modified(t1, t2, n):
    tn1 = t1
    tn2 = t2
    while n >= 3:
        result = 0
        result += tn2
        result *= result
        result += tn1
        tn1 = tn2
        tn2 = result
        n -= 1
    return tn2


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(5, fibonacci_modified(0, 1, 5))

    def test_example_2(self):
        self.assertEqual(84266613096281243382112, fibonacci_modified(0, 1, 10))
