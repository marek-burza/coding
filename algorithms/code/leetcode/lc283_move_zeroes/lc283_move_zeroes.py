# https://leetcode.com/problems/move-zeroes/

import unittest


class Solution:
    def moveZeroes(self, nums: list[int]) -> None:
        target = 0
        for index, _ in enumerate(nums):
            nums[target] = nums[index]
            if nums[index] != 0:
                target += 1
        for index in range(target, len(nums)):
            nums[index] = 0


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        nums = [0, 1, 0, 3, 12]
        Solution().moveZeroes(nums)
        expected = [1, 3, 12, 0, 0]
        assert expected == nums
