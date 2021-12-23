#!/usr/bin/env python3
# https://leetcode.com/problems/single-number-ii/

import unittest


class Solution:
    def singleNumber(self, nums):
        counters = [0] * 32
        for num in nums:
            for i, _ in enumerate(counters):
                counters[i] += num & 1
                num >>= 1
        result = 0
        mask = 1
        for i, _ in enumerate(counters):
            if counters[i] % 3 != 0:
                result |= mask
            mask <<= 1
        return result


class TestCode(unittest.TestCase):
    def test_1112(self):
        nums = [1, 1, 1, 2]
        self.assertEqual(2, Solution().singleNumber(nums))

    def test_4344533(self):
        nums = [4, 3, 4, 4, 5, 3, 3]
        self.assertEqual(5, Solution().singleNumber(nums))
