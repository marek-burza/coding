#!/usr/bin/env python3
# https://leetcode.com/problems/range-sum-query-immutable/

import unittest
from typing import List


class NumArray:
    def __init__(self, nums: List[int]):
        self.__sums = [0] * len(nums)
        summed = 0
        for i, nums_i in enumerate(nums):
            summed += nums_i
            self.__sums[i] = summed

    def sumRange(self, left: int, right: int) -> int:
        summed = 0
        if left > 0:
            summed = -self.__sums[left - 1]
        summed += self.__sums[right]
        return summed


class TestCode(unittest.TestCase):
    def test_0__2(self):
        nums = [-2, 0, 3, -5, 2, -1]
        self.assertEqual(1, NumArray(nums).sumRange(0, 2))

    def test_2__5(self):
        nums = [-2, 0, 3, -5, 2, -1]
        self.assertEqual(-1, NumArray(nums).sumRange(2, 5))

    def test_0__5(self):
        nums = [-2, 0, 3, -5, 2, -1]
        self.assertEqual(-3, NumArray(nums).sumRange(0, 5))
