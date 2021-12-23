#!/usr/bin/env python3
# https://leetcode.com/problems/bitwise-and-of-numbers-range/

import unittest


class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        result = 0
        power = 1
        mask = 0
        for _ in range(0, 32):
            if (left & power) != 0:
                if (power - (left & mask)) > (right - left):
                    result |= power
            power <<= 1
            mask = (mask << 1) | 1
        return result
        # Alternative: Zero all bits after the first difference
        # when checking from highest to lowest bit


class TestCode(unittest.TestCase):
    def test_5__7(self):
        self.assertEqual(4, Solution().rangeBitwiseAnd(5, 7))
