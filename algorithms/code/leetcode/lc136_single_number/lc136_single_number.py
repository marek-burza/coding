# https://leetcode.com/problems/single-number/

import unittest


class Solution:
    def singleNumber(self, nums: list[int]) -> int:
        result = 0
        for value in nums:
            result ^= value
        return result


class TestCode(unittest.TestCase):
    def test_1(self) -> None:
        self.assertEqual(1, Solution().singleNumber([1]))

    def test_1_2_1(self) -> None:
        self.assertEqual(2, Solution().singleNumber([1, 2, 1]))
