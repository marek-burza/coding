#!/usr/bin/env python3
# https://leetcode.com/problems/container-with-most-water/

import unittest

from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        maximum = 0
        low = 0
        high = len(height) - 1
        while low < high:
            top = min(height[low], height[high])
            maximum = max(maximum, top * (high - low))
            if height[low] <= height[high]:
                low += 1
            else:
                high -= 1
        return maximum


class TestCode(unittest.TestCase):
    def test_1_2_1(self):
        test = [1, 2, 1]
        self.assertEqual(Solution().maxArea(test), 2)

    def test_1_3_5_2(self):
        test = [1, 3, 5, 2]
        self.assertEqual(Solution().maxArea(test), 4)

    def test_oversized(self):
        test = 15000 * [0]
        for i in range(15000):
            test[i] = i + 1
        self.assertEqual(Solution().maxArea(test), 56250000)

    def test_huh(self):
        test = [1, 2, 1, 15, 15, 1, 2, 1]
        self.assertEqual(Solution().maxArea(test), 15)
