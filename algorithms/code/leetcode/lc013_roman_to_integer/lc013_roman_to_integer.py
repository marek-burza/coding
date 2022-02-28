#!/usr/bin/env python3
# https://leetcode.com/problems/roman-to-integer/

import unittest


class Solution:
    def romanToInt(self, s: str) -> int:
        if s is None:
            return 0
        result = 0
        previous = 0
        for i in range(len(s) - 1, -1, -1):
            current = {
                'I': 1,
                'V': 5,
                'X': 10,
                'L': 50,
                'C': 100,
                'D': 500,
                'M': 1000,
            }[s[i]]
            current = -current if current < previous else current
            result += current
            previous = current
        return result


class TestCode(unittest.TestCase):
    def test_MCMLIV(self):
        self.assertEqual(Solution().romanToInt('MCMLIV'), 1954)

    def test_nothing(self):
        self.assertEqual(Solution().romanToInt(None), 0)
