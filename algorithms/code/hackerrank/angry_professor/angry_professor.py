#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/angry-professor

import unittest


def angry_professor(k, a):
    absent = 0
    n = len(a)
    threshold = n - k
    for arrival in a:
        if arrival > 0:
            absent += 1
        if threshold < absent:
            return 'YES'
    return 'NO'


class TestCode(unittest.TestCase):
    def run_tests(self, tests):
        for test in tests:
            k = test['k']
            a = test['a']
            expected = test['expected']
            result = angry_professor(k, a)
            self.assertEqual(result, expected)

    def test_example(self):
        tests = [
            {'k': 3, 'a': [-1, -3, 4, 2], 'expected': 'YES'},
            {'k': 2, 'a': [0, -1, 2, 1], 'expected': 'NO'},
        ]
        self.run_tests(tests)

    def test_01(self):
        tests = [
            {
                'k': 4,
                'a': [-93, -86, 49, -62, -90, -63, 40, 72, 11, 67],
                'expected': 'NO'
            },
            {
                'k': 10,
                'a': [23, -35, -2, 58, -67, -56, -42, -73, -19, 37],
                'expected': 'YES'
            },
            {
                'k': 9,
                'a': [13, 91, 56, -62, 96, -5, -84, -36, -46, -13],
                'expected': 'YES'
            },
            {
                'k': 8,
                'a': [45, 67, 64, -50, -8, 78, 84, -51, 99, 60],
                'expected': 'YES'
            },
            {
                'k': 7,
                'a': [26, 94, -95, 34, 67, -97, 17, 52, 1, 86],
                'expected': 'YES'
                },
            {
                'k': 2,
                'a': [19, 29, -17, -71, -75, -27, -56, -53, 65, 83],
                'expected': 'NO'
            },
            {
                'k': 10,
                'a': [-32, -3, -70, 8, -40, -96, -18, -46, -21, -79],
                'expected': 'YES'
            },
            {
                'k': 9,
                'a': [-50, 0, 64, 14, -56, -91, -65, -36, 51, -28],
                'expected': 'YES'
            },
            {
                'k': 6,
                'a': [-58, -29, -35, -18, 43, -28, -76, 43, -13, 6],
                'expected': 'NO'
            },
            {
                'k': 1,
                'a': [88, -17, -96, 43, 83, 99, 25, 90, -39, 86],
                'expected': 'NO'
            }
        ]
        self.run_tests(tests)
