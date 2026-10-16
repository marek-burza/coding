# https://leetcode.com/problems/majority-element/

from collections import Counter


class Solution:
    def majorityElement(self, nums: list[int]) -> int:
        frequencies: Counter = Counter()
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


class TestCode:
    def test_1_2_3_1_5_1_6_1(self) -> None:
        nums = [1, 2, 3, 1, 5, 1, 6, 1]
        assert Solution().majorityElement(nums) == 1
