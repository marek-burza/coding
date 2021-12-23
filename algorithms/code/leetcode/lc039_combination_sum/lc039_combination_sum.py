#!/usr/bin/env python3
# https://leetcode.com/problems/combination-sum/

import unittest

from typing import List


class Solution:
    # pylint: disable=R0913
    def __combinationSum(self, candidates, target, path, total, index, combos):
        if total == target:
            combos.append(path.copy())
            return
        inner = path.copy()
        partial = 0
        while partial <= target - total and index < len(candidates):
            self.__combinationSum(
                candidates, target, inner, total + partial, index + 1, combos)
            inner.append(candidates[index])
            partial += candidates[index]

    # pylint: disable=C0301
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:  # noqa
        combos = []
        self.__combinationSum(candidates, target, [], 0, 0, combos)
        return combos


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
    def deep_comparator(list1, list2):  # pragma: no cover
        if len(list1) < len(list2):
            return -1
        if len(list1) > len(list2):
            return 1
        for list1i, list2i in zip(list1, list2):
            if list1i < list2i:
                return -1
            if list1i > list2i:
                return 1
        return 0

    def test_example(self):
        candidates = [2, 3, 6, 7]
        expected = [[7], [2, 2, 3]]
        combos = Solution().combinationSum(candidates, 7)
        for listed in combos:
            listed.sort()
        combos = sorted(
            combos, key=TestCode.cmp_to_key(TestCode.deep_comparator))
        self.assertEqual(len(expected), len(combos))
        i = 0
        while i < len(expected):
            self.assertEqual(len(expected[i]), len(combos[i]))
            j = 0
            while j < len(expected[i]):
                self.assertEqual(expected[i][j], int(combos[i][j]))
                j += 1
            i += 1
