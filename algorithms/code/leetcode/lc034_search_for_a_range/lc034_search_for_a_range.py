# https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

import unittest


class Solution:
    def __bsInfimum(self, nums: list[int], target: int) -> int:
        a = 0
        z = len(nums) - 1
        while a < z:
            m = (a + z) >> 1
            if nums[m] < target:
                a = m + 1
            if nums[m] == target:
                z = m
            if nums[m] > target:
                z = m - 1
        if a == z and nums[a] == target:
            return a
        return -1

    def __bsSupremum(self, nums: list[int], target: int) -> int:
        a = 0
        z = len(nums) - 1
        while a < z:
            m = (1 + a + z) >> 1
            if nums[m] < target:
                a = m + 1
            if nums[m] == target:
                a = m
            if nums[m] > target:
                z = m - 1
        if a == z and nums[a] == target:
            return a
        return -1

    def searchRange(self, nums: list[int], target: int) -> list[int]:
        if len(nums) == 0:
            return [-1, -1]
        infimum = self.__bsInfimum(nums, target)
        supremum = self.__bsSupremum(nums, target)
        return [infimum, supremum]


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        nums = [5, 7, 7, 8, 8, 10]
        expected = [3, 4]
        assert expected == Solution().searchRange(nums, 8)

    def test_other(self) -> None:
        nums = [5, 7, 7, 8, 8, 10]
        expected = [-1, -1]
        assert expected == Solution().searchRange(nums, 6)

    def test_another(self) -> None:
        nums = [2, 2]
        expected = [-1, -1]
        assert expected == Solution().searchRange(nums, 3)

    def test_nothing(self) -> None:
        expected = [-1, -1]
        assert expected == Solution().searchRange([], 3)
