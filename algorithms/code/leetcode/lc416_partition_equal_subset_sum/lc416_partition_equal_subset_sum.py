#!/usr/bin/env python3
# https://leetcode.com/problems/partition-equal-subset-sum/

import unittest
from typing import List, Optional


def canPartitionSubset(
    nums: List[int],
    count: int,
    summed: int,
    cache: List[List[Optional[bool]]],
) -> bool:
    if summed == 0:
        return True
    if count == 0:
        return False
    cached = cache[count][summed]
    if cached is not None:
        return cached
    if nums[count - 1] > summed:
        return canPartitionSubset(nums, count - 1, summed, cache)
    cached = (
        canPartitionSubset(nums, count - 1, summed - nums[count - 1], cache)
        or canPartitionSubset(nums, count - 1, summed, cache)
    )
    cache[count][summed] = cached
    return cached


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        count = len(nums)
        summed = sum(nums)
        if summed % 2 != 0:
            return False
        cache: List[List[Optional[bool]]] = [
            [None] * (summed + 1) for _ in range(count + 1)
        ]
        return canPartitionSubset(nums, count, summed // 2, cache)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertTrue(Solution().canPartition([1, 5, 11, 5]))

    def test_example_2(self):
        self.assertFalse(Solution().canPartition([1, 2, 3, 5]))

    def test_longer(self):
        nums = [
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 99, 97,
        ]
        self.assertFalse(Solution().canPartition(nums))
