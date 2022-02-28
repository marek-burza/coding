#!/usr/bin/env python3
# https://leetcode.com/problems/permutations/

import unittest
from typing import List


class Solution:
    def __permute(self, prefix, remaining, permutations):
        if len(remaining) == 0:
            permutations.append(prefix.copy())
        else:
            for value in remaining:
                prefix.append(value)
                reduced = set(remaining)
                reduced.remove(value)
                self.__permute(prefix, reduced, permutations)
                prefix.pop()

    def permute(self, nums: List[int]) -> List[List[int]]:
        permutations: List[List[int]] = []
        remaining = set(nums)
        self.__permute([], remaining, permutations)
        return permutations


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
    def integer_list_comparator(l1, l2):  # pragma: no cover
        if len(l1) < len(l2):
            return -1
        if len(l1) > len(l2):
            return 1
        for l1i, l2i in zip(l1, l2):
            if l1i < l2i:
                return -1
            if l1i > l2i:
                return 1
        return 0

    def test_example(self):
        nums = [1, 2, 3]
        expected = [
            [1, 2, 3],
            [1, 3, 2],
            [2, 1, 3],
            [2, 3, 1],
            [3, 1, 2],
            [3, 2, 1],
        ]
        result = Solution().permute(nums)
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.integer_list_comparator)
        )
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertEqual(len(expected[i]), len(result[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])
