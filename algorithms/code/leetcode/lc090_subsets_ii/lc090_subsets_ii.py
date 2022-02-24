#!/usr/bin/env python3
# https://leetcode.com/problems/subsets-ii/

import unittest

from typing import List


class Solution:
    def __subsets(self, nums, offset, current, listed):
        listed.append(current.copy())
        i = offset
        while i < len(nums):
            count = 1
            j = i + 1
            while j < len(nums) and nums[j - 1] == nums[j]:
                count += 1
                j += 1
            for j in range(count):
                current.append(nums[i])
                self.__subsets(nums, i + count, current, listed)
            for j in range(count):
                current.pop()
            i += count

    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        listed: List[List[int]] = []
        self.__subsets(nums, 0, [], listed)
        return listed


class TestCode(unittest.TestCase):
    @staticmethod
    def cmp_to_key(mycmp):  # pragma: no cover
        class K:
            def __init__(self, obj, *_):
                self.obj = obj

            def __lt__(self, other):
                return mycmp(self.obj, other.obj) < 0

            def __gt__(self, other):
                return mycmp(self.obj, other.obj) > 0

            def __eq__(self, other):
                return mycmp(self.obj, other.obj) == 0

            def __le__(self, other):
                return mycmp(self.obj, other.obj) <= 0

            def __ge__(self, other):
                return mycmp(self.obj, other.obj) >= 0

            def __ne__(self, other):
                return mycmp(self.obj, other.obj) != 0
        return K

    @staticmethod
    def orderly_comparator(l1, l2):  # pragma: no cover
        difference = len(l1) - len(l2)
        if difference != 0:
            return difference
        for l1s, l2s in zip(l1, l2):
            if l1s < l2s:
                return -1
            if l1s > l2s:
                return 1
        return 0

    def __test(self, expected, result):
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.orderly_comparator))
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertEqual(len(expected[i]), len(result[i]))
            result[i].sort()
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])

    def test_1_2_2(self):
        listed = Solution().subsetsWithDup([1, 2, 2])
        expected = [[], [1], [2], [1, 2], [2, 2], [1, 2, 2]]
        self.__test(expected, listed)
