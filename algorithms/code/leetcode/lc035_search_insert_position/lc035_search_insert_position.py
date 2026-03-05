# https://leetcode.com/problems/search-insert-position/

import unittest


class Solution:
    def searchInsert(self, nums: list[int], target: int) -> int:
        a = 0
        z = len(nums)
        while a != z:
            m = (a + z) >> 1
            if nums[m] < target:
                a = m + 1
            else:
                z = m
        return z


class TestCode(unittest.TestCase):
    def test__empty__5(self) -> None:
        nums: list[int] = []
        assert Solution().searchInsert(nums, 5) == 0

    def test__1_3_5_6__5(self) -> None:
        nums = [1, 3, 5, 6]
        assert Solution().searchInsert(nums, 5) == 2

    def test__1_3_5_6__2(self) -> None:
        nums = [1, 3, 5, 6]
        assert Solution().searchInsert(nums, 2) == 1

    def test__1_3_5_6__7(self) -> None:
        nums = [1, 3, 5, 6]
        assert Solution().searchInsert(nums, 7) == 4

    def test__1_3_5_6__0(self) -> None:
        nums = [1, 3, 5, 6]
        assert Solution().searchInsert(nums, 0) == 0

    def test__1_3_5_6__1(self) -> None:
        nums = [1, 3, 5, 6]
        assert Solution().searchInsert(nums, 1) == 0
