#!/usr/bin/env python3
# https://leetcode.com/problems/contains-duplicate-iii/

import bisect
import unittest

from typing import List


class Solution:
    @staticmethod
    def __binary_search(items, value):
        index = bisect.bisect_left(items, value)
        if index != len(items) and items[index] == value:
            return index
        return -1

    # pylint: disable=C0301
    def containsNearbyAlmostDuplicate(self, nums: List[int], k: int, t: int) -> bool:  # noqa
        sorted_set = []
        ordered = []
        for num in nums:
            ceiling = bisect.bisect_left(sorted_set, num)
            floor = bisect.bisect_right(sorted_set, num)
            ceiling_ok = ceiling < len(sorted_set)
            ceiling_ok = ceiling_ok and sorted_set[ceiling] - num <= t
            floor_ok = floor != 0 and floor - 1 < len(sorted_set)
            floor_ok = floor_ok and num - sorted_set[floor - 1] <= t
            if ceiling_ok or floor_ok:
                return True
            ordered.append(num)
            if self.__binary_search(sorted_set, num) == -1:
                bisect.insort(sorted_set, num)
            if len(ordered) > k:
                index = self.__binary_search(sorted_set, ordered.pop(0))
                sorted_set.pop(index)
        return False


class TestCode(unittest.TestCase):
    def test_1_10_20_2(self):
        nums = [1, 10, 20, 2]
        self.assertTrue(Solution().containsNearbyAlmostDuplicate(nums, 3, 2))

    def test_1_10_20_4(self):
        nums = [1, 10, 20, 4]
        self.assertFalse(Solution().containsNearbyAlmostDuplicate(nums, 3, 2))

    def test_1_10_20_30_2(self):
        nums = [1, 10, 20, 30, 2]
        self.assertFalse(Solution().containsNearbyAlmostDuplicate(nums, 3, 2))

    def test_8_7_15_1_6_1_9_15__1__3(self):
        nums = [8, 7, 15, 1, 6, 1, 9, 15]
        self.assertTrue(Solution().containsNearbyAlmostDuplicate(nums, 1, 3))

    def test_2147483640_2147483641__1__100(self):
        nums = [2147483640, 2147483641]
        self.assertTrue(Solution().containsNearbyAlmostDuplicate(nums, 1, 100))
