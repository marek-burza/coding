#!/usr/bin/env python3
# https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

import unittest

from typing import List


class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        indices = [0] * 2
        if numbers is None or len(numbers) < 2:
            return indices
        a = 0
        z = len(numbers) - 1
        while a < z:
            v = numbers[a] + numbers[z]
            if v == target:
                indices[0] = a + 1
                indices[1] = z + 1
                break
            if v > target:
                z -= 1
            else:
                a += 1
        return indices


class TestCode(unittest.TestCase):
    def test_example(self):
        self.assertListEqual([1, 2], Solution().twoSum([2, 7, 11, 15], 9))

    def test_other_example(self):
        self.assertListEqual([0, 0], Solution().twoSum([1, 5, 6, 9], 9))

    def test_nothing(self):
        self.assertListEqual([0, 0], Solution().twoSum(None, 0))
        self.assertListEqual([0, 0], Solution().twoSum([0], 0))
