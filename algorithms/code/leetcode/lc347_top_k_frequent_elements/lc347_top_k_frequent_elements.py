#!/usr/bin/env python3
# https://leetcode.com/problems/top-k-frequent-elements/

import unittest

from typing import List, Dict


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        frequencies: Dict[int, int] = {}
        for value in nums:
            if value in frequencies:
                frequencies[value] = frequencies[value] + 1
            else:
                frequencies[value] = 1
        keys = list(frequencies.keys())
        keys.sort(key=lambda item: frequencies[item], reverse=True)
        selected: List[int] = []
        for value in keys:
            if len(selected) >= k:
                break
            selected.append(value)
        return selected


class TestCode(unittest.TestCase):
    def test_example(self):
        nums = [1, 1, 1, 2, 2, 3]
        expected = [1, 2]
        self.assertListEqual(expected, Solution().topKFrequent(nums, 2))
