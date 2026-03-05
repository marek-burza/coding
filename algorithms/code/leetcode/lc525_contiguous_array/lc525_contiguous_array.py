# https://leetcode.com/problems/contiguous-array/

import unittest


class Solution:
    def findMaxLength(self, nums: list[int]) -> int:
        sums_before = {0: 0}  # Sum 0 was at index 0
        maximum = running_sum = 0
        for i, value in enumerate(nums):
            running_sum += 1 if value == 1 else -1
            if running_sum in sums_before:
                maximum = max(maximum, i + 1 - sums_before[running_sum])
            else:
                sums_before[running_sum] = i + 1
        return maximum


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().findMaxLength([0, 1]) == 2

    def test_example_2(self) -> None:
        assert Solution().findMaxLength([0, 1, 0]) == 2

    def test_other_1(self) -> None:
        nums = [0, 0, 1, 0, 0, 0, 1, 1]
        assert Solution().findMaxLength(nums) == 6
