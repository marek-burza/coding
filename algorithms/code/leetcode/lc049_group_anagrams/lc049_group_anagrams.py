#!/usr/bin/env python3
# https://leetcode.com/problems/group-anagrams/

import unittest

from typing import List, Dict


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        seen: Dict[str, List[str]] = {}
        for string in strs:
            array = list(string)
            array.sort()
            key = ''.join(array)
            if key in seen:
                seen[key].append(string)
            else:
                group = []
                group.append(string)
                seen[key] = group
        result = []
        for group in seen.values():
            group.sort()
            result.append(group)
        return result


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
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])

    def test_abc_cab_bad_dab_zzz_dot(self):
        strs = ['abc', 'cab', 'bad', 'dab', 'zzz', 'dot']
        expected = [['dot'], ['zzz'], ['abc', 'cab'], ['bad', 'dab']]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)

    def test_tea_and_ate_eat_den(self):
        strs = ['tea', 'and', 'ate', 'eat', 'den']
        expected = [['and'], ['den'], ['ate', 'eat', 'tea']]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)
