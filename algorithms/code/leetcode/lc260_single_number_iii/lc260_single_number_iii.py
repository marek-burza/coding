#!/usr/bin/env python3
# https://leetcode.com/problems/single-number-iii/

import unittest
from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        xor = 0
        for value in nums:
            xor ^= value
        mask = xor & ~(xor - 1)
        values = [0, 0]
        for value in nums:
            if (value & mask) == 0:
                values[0] ^= value
            else:
                values[1] ^= value
        return values


class TestCode(unittest.TestCase):
    def test(self):
        result = Solution().singleNumber([1, 2, 1, 3, 2, 5])
        result.sort()
        self.assertListEqual([3, 5], result)
