# https://leetcode.com/problems/two-sum/

import unittest


class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        mapped: dict[int, int] = {}
        for i, num in enumerate(nums):
            expected = target - num
            if expected in mapped:
                found = mapped[expected]
                return [found, i]
            mapped[num] = i
        return []


class TestCode(unittest.TestCase):
    def test_00(self) -> None:
        self.assertListEqual(Solution().twoSum([], 0), [])

    def test_1(self) -> None:
        self.assertListEqual(Solution().twoSum([2, 7, 11, 15], 9), [0, 1])

    def test_2(self) -> None:
        self.assertListEqual(Solution().twoSum([3, 2, 4], 6), [1, 2])

    def test_3(self) -> None:
        self.assertListEqual(Solution().twoSum([3, 3], 6), [0, 1])
