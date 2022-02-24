#!/usr/bin/env python3

# https://leetcode.com/problems/combinations/

import unittest

from typing import List


class Solution:
    # pylint: disable=R0913
    def __combine(self, m, n, k, prefix, found):
        for i in range(m, n - (k - 1) + len(prefix) + 1):
            prefix.append(i)
            if len(prefix) == k:
                found.append(prefix.copy())
            else:
                self.__combine(i + 1, n, k, prefix, found)
            prefix.pop()

    def combine(self, n: int, k: int) -> List[List[int]]:
        found: List[List[int]] = []
        self.__combine(1, n, k, [], found)
        return found


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
        expected = [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
        result = Solution().combine(4, 2)
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.integer_list_comparator))
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertEqual(len(expected[i]), len(result[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])
