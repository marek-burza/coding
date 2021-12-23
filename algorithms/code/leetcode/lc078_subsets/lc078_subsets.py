#!/usr/bin/env python3
# https://leetcode.com/problems/subsets/

import unittest

from typing import List


class Solution:
    def __subsets(self, nums, offset, current, listed):
        listed.append(current.copy())
        for i in range(offset, len(nums)):
            current.append(nums[i])
            self.__subsets(nums, i + 1, current, listed)
            current.pop()

    def subsets(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        listed = []
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

    def test_1_2_3(self):
        listed = Solution().subsets([1, 2, 3])
        expected = [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
        self.__test(expected, listed)
