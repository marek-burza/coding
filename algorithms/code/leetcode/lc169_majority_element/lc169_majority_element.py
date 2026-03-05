# https://leetcode.com/problems/majority-element/

import collections
import unittest


class Solution:
    def majorityElement(self, nums: list[int]) -> int:
        frequencies: collections.Counter = collections.Counter()
        for value in nums:
            frequencies[value] += 1
        result = 0  # Instead of -inf
        count = 0  # Instead of -inf
        for value, frequencies_value in frequencies.items():
            other = frequencies_value
            if count <= other:
                result = value
                count = other
        return result


class TestCode(unittest.TestCase):
    def test_1_2_3_1_5_1_6_1(self) -> None:
        nums = [1, 2, 3, 1, 5, 1, 6, 1]
        self.assertEqual(1, Solution().majorityElement(nums))
