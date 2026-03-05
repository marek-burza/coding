# https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

import unittest


class Solution:
    def findMin(self, nums: list[int]) -> int:
        a = 0
        z = len(nums) - 1
        while a < z:
            m = (a + z) >> 1
            if nums[z] < nums[m]:
                a = m + 1
            elif nums[m] < nums[z]:
                z = m
            else:
                z -= 1
        return nums[a]


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        nums = [4, 5, 6, 7, 0, 1, 2]
        assert Solution().findMin(nums) == 0

    def test_trickier(self) -> None:
        nums = [1, 1, 0, 1, 1, 1, 1]
        assert Solution().findMin(nums) == 0
