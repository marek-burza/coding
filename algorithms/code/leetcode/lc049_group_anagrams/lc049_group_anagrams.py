#!/usr/bin/env python3
# https://leetcode.com/problems/group-anagrams/

import unittest
from typing import Any, Callable


class Solution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        seen: dict[str, list[str]] = {}
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
    def cmp_to_key(mycmp: Callable) -> Any:  # pragma: no cover
        class K:
            def __init__(self, obj: Any, *_: Any) -> None:
                self.obj = obj

            def __lt__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) < 0

            def __gt__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) > 0

            def __eq__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) == 0

            def __le__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) <= 0

            def __ge__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) >= 0

            def __ne__(self, other: Any) -> bool:
                return mycmp(self.obj, other.obj) != 0

        return K

    @staticmethod
    def orderly_comparator(
        l1: list[str], l2: list[str]
    ) -> int:  # pragma: no cover
        difference = len(l1) - len(l2)
        if difference != 0:
            return difference
        for l1s, l2s in zip(l1, l2, strict=True):
            if l1s < l2s:
                return -1
            if l1s > l2s:
                return 1
        return 0

    def __test(
        self, expected: list[list[str]], result: list[list[str]]
    ) -> None:
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.orderly_comparator)
        )
        self.assertEqual(len(expected), len(result))
        for i, expected_i in enumerate(expected):
            self.assertEqual(len(expected_i), len(result[i]))
            for j, expected_i_j in enumerate(expected_i):
                self.assertEqual(expected_i_j, result[i][j])

    def test_abc_cab_bad_dab_zzz_dot(self) -> None:
        strs = ['abc', 'cab', 'bad', 'dab', 'zzz', 'dot']
        expected = [['dot'], ['zzz'], ['abc', 'cab'], ['bad', 'dab']]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)

    def test_tea_and_ate_eat_den(self) -> None:
        strs = ['tea', 'and', 'ate', 'eat', 'den']
        expected = [['and'], ['den'], ['ate', 'eat', 'tea']]
        result = Solution().groupAnagrams(strs)
        self.__test(expected, result)
