#!/usr/bin/env python3
# https://leetcode.com/problems/contains-duplicate-ii/

import collections
import unittest

from typing import List, Set


class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        collected: Set[int] = set()
        ordered: collections.deque = collections.deque()
        for num in nums:
            if num in collected:
                return True
            ordered.append(num)
            collected.add(num)
            if len(ordered) > k:
                collected.remove(ordered.popleft())
        return False


class TestCode(unittest.TestCase):
    def test_0_5_7__2(self):
        self.assertFalse(Solution().containsNearbyDuplicate([0, 5, 7], 2))

    def test_0_5_7_5__2(self):
        self.assertTrue(Solution().containsNearbyDuplicate([0, 5, 7, 5], 2))

    def test_0_5_7_10_5__2(self):
        self.assertFalse(
            Solution().containsNearbyDuplicate([0, 5, 7, 10, 5], 2))
