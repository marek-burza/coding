# https://leetcode.com/problems/combination-sum/

from __future__ import annotations

import unittest
from typing import Any, Callable


class Solution:
    # pylint: disable=R0913,R0917
    def __combinationSum(
        self,
        candidates: list[int],
        target: int,
        path: list[int],
        total: int,
        index: int,
        combos: list[list[int]],
    ) -> None:
        if total == target:
            combos.append(path.copy())
            return
        inner = path.copy()
        partial = 0
        while partial <= target - total and index < len(candidates):
            self.__combinationSum(
                candidates, target, inner, total + partial, index + 1, combos
            )
            inner.append(candidates[index])
            partial += candidates[index]

    def combinationSum(
        self, candidates: list[int], target: int
    ) -> list[list[int]]:
        combos: list[list[int]] = []
        self.__combinationSum(candidates, target, [], 0, 0, combos)
        return combos


class TestCode(unittest.TestCase):
    @staticmethod
    def cmp_to_key(
        mycmp: Callable[[list[list[int]], list[list[int]]], int],
    ) -> Any:  # pragma: no cover
        class K:
            def __init__(self, obj: list[list[int]], *_) -> None:
                self.obj = obj

            def __lt__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) < 0

            def __gt__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) > 0

            def __eq__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) == 0

            def __le__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) <= 0

            def __ge__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) >= 0

            def __ne__(self, other: object) -> bool:
                assert isinstance(other, K)
                return mycmp(self.obj, other.obj) != 0

        return K

    @staticmethod
    def deep_comparator(
        list1: list[list[int]], list2: list[list[int]]
    ) -> int:  # pragma: no cover
        if len(list1) < len(list2):
            return -1
        if len(list1) > len(list2):
            return 1
        for list1i, list2i in zip(list1, list2, strict=True):
            if list1i < list2i:
                return -1
            if list1i > list2i:
                return 1
        return 0

    def test_example(self) -> None:
        candidates = [2, 3, 6, 7]
        expected = [[7], [2, 2, 3]]
        combos = Solution().combinationSum(candidates, 7)
        for listed in combos:
            listed.sort()
        combos = sorted(
            combos, key=TestCode.cmp_to_key(TestCode.deep_comparator)
        )
        assert len(expected) == len(combos)
        i = 0
        while i < len(expected):
            assert len(expected[i]) == len(combos[i])
            j = 0
            while j < len(expected[i]):
                assert expected[i][j] == int(combos[i][j])
                j += 1
            i += 1
