# https://leetcode.com/problems/find-peak-element/

import unittest


class Solution:
    def findPeakElement(self, nums: list[int]) -> int:
        for i in range(1, len(nums) + 1):
            postFalling = None
            postFalling = i == len(nums) or nums[i - 1] > nums[i]
            if postFalling:
                return i - 1
        return -1


class TestCode(unittest.TestCase):
    def test_1_2_3_1(self) -> None:
        assert Solution().findPeakElement([1, 2, 3, 1]) == 2

    def test_1_2_3_4(self) -> None:
        assert Solution().findPeakElement([1, 2, 3, 4]) == 3

    def test_nothing(self) -> None:
        assert Solution().findPeakElement([]) == -1
