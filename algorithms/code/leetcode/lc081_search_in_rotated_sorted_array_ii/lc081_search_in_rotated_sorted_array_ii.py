# https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

import bisect
import unittest


class Solution:
    @staticmethod
    def __binary_search(
        array: list[int], begin: int, end: int, value: int
    ) -> int:
        index = bisect.bisect_left(array, value, begin, end)
        if index != len(array) and array[index] == value:
            return index
        return -1

    def search(self, nums: list[int], target: int) -> bool:
        for i in range(1, len(nums)):
            if nums[i - 1] > nums[i]:
                ante = self.__binary_search(nums, 0, i, target) >= 0
                post = self.__binary_search(nums, i, len(nums), target) >= 0
                return ante or post
        return self.__binary_search(nums, 0, len(nums), target) >= 0


class TestCode(unittest.TestCase):
    def test_simple_example(self) -> None:
        nums = [4, 5, 6, 6, 7, 0, 1, 2]
        self.assertTrue(Solution().search(nums, 1))

    def test_tricky_example(self) -> None:
        nums = [1, 1, 3, 1, 1, 1, 1, 1]
        self.assertFalse(Solution().search(nums, 2))

    def test_1_and_1(self) -> None:
        nums = [1]
        self.assertTrue(Solution().search(nums, 1))

    def test_1_and_0(self) -> None:
        nums = [1]
        self.assertFalse(Solution().search(nums, 0))

    def test_2_5_6_0_0_1_2__3(self) -> None:
        nums = [2, 5, 6, 0, 0, 1, 2]
        self.assertFalse(Solution().search(nums, 3))

    def test_2_5_6_0_0_1_2__0(self) -> None:
        nums = [2, 5, 6, 0, 0, 1, 2]
        self.assertTrue(Solution().search(nums, 0))

    def test_2_2_2_3_2_2_2__3(self) -> None:
        nums = [2, 2, 2, 3, 2, 2, 2]
        self.assertTrue(Solution().search(nums, 3))
