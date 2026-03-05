# https://leetcode.com/problems/contains-duplicate-ii/

import collections
import unittest


class Solution:
    def containsNearbyDuplicate(self, nums: list[int], k: int) -> bool:
        collected: set[int] = set()
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
    def test_0_5_7__2(self) -> None:
        self.assertFalse(Solution().containsNearbyDuplicate([0, 5, 7], 2))

    def test_0_5_7_5__2(self) -> None:
        self.assertTrue(Solution().containsNearbyDuplicate([0, 5, 7, 5], 2))

    def test_0_5_7_10_5__2(self) -> None:
        self.assertFalse(
            Solution().containsNearbyDuplicate([0, 5, 7, 10, 5], 2)
        )
