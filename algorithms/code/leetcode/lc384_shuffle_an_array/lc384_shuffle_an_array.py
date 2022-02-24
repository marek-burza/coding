#!/usr/bin/env python3
# https://leetcode.com/problems/shuffle-an-array/

import random
import unittest
from typing import List


class Solution:
    def __init__(self, nums: List[int]):
        self.__nums = nums

    def reset(self) -> List[int]:
        return self.__nums.copy()

    def shuffle(self) -> List[int]:
        if self.__nums is None:
            return None
        result = self.__nums.copy()
        for i in range(len(self.__nums) - 1, 0, -1):
            j = random.SystemRandom().randint(0, i)
            result[i], result[j] = result[j], result[i]
        return result


class TestCode(unittest.TestCase):
    def __test(self, nums):
        solution = Solution(nums.copy())
        result = solution.shuffle()
        reset = solution.reset()
        self.assertListEqual(nums, reset)
        nums.sort()
        result.sort()
        self.assertListEqual(nums, result)

    def test_example(self):
        nums = [1, 2, 3]
        self.__test(nums)
        # Should use Chi-squared test

    def test_nothing(self):
        solution = Solution(None)
        self.assertIsNone(solution.shuffle())
