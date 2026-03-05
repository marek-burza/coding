# https://leetcode.com/problems/single-number-ii/
# #google

import unittest


class Solution:
    def singleNumber(self, nums: list[int]) -> int:
        counters = [0] * 32
        for num in nums:
            for i, _ in enumerate(counters):
                counters[i] += num & 1
                num >>= 1
        result = 0
        mask = 1
        for i, _ in enumerate(counters):
            if counters[i] % 3 != 0:
                result |= mask
            mask <<= 1
        return result


class TestCode(unittest.TestCase):
    def test_1112(self) -> None:
        nums = [1, 1, 1, 2]
        assert Solution().singleNumber(nums) == 2

    def test_4344533(self) -> None:
        nums = [4, 3, 4, 4, 5, 3, 3]
        assert Solution().singleNumber(nums) == 5
