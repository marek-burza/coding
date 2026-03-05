# https://leetcode.com/problems/top-k-frequent-elements/

import unittest


class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        frequencies: dict[int, int] = {}
        for value in nums:
            if value in frequencies:
                frequencies[value] = frequencies[value] + 1
            else:
                frequencies[value] = 1
        keys = list(frequencies.keys())
        keys.sort(key=lambda item: frequencies[item], reverse=True)
        selected: list[int] = keys[:k]
        return selected


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        nums = [1, 1, 1, 2, 2, 3]
        expected = [1, 2]
        assert expected == Solution().topKFrequent(nums, 2)
