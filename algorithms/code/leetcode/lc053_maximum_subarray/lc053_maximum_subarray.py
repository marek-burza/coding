# https://leetcode.com/problems/maximum-subarray/

import unittest


class Solution:
    def maxSubArray(self, nums: list[int]) -> int:
        summed = 0
        minimum = 0
        maximum = float('-inf')
        i = 0
        while i < len(nums):
            minimum = summed if summed < minimum else minimum
            summed += nums[i]
            delta = summed - minimum
            maximum = delta if delta > maximum else maximum
            i += 1
        return int(maximum)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]) == 6

    def test_example_2(self) -> None:
        assert Solution().maxSubArray([1]) == 1

    def test_example_3(self) -> None:
        assert Solution().maxSubArray([5, 4, -1, 7, 8]) == 23

    def test_Minus2_1(self) -> None:
        assert Solution().maxSubArray([-2, 1]) == 1

    def test_Minus2_Minus1(self) -> None:
        assert Solution().maxSubArray([-2, -1]) == -1
