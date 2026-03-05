# https://leetcode.com/problems/missing-number/

import unittest


class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        expected = len(nums) * (len(nums) + 1) // 2
        summed = 0
        for value in nums:
            summed += value
        return int(expected - summed)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        assert Solution().missingNumber([0, 1, 3]) == 2
