#!/usr/bin/env python3
# https://leetcode.com/problems/sum-of-two-integers/

import unittest


class Solution:
    def getSum(self, a: int, b: int) -> int:
        result = 0
        carry = 0
        mask = 1
        while mask != 0:
            am = a & mask
            bm = b & mask
            result |= am ^ bm ^ carry
            carry = (am & bm) | (bm & carry) | (am & carry)
            carry <<= 1
            mask <<= 1
            mask &= 0xFFFFFFFF
        return result


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertEqual(3, Solution().getSum(1, 2))
