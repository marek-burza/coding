# https://leetcode.com/problems/permutations/

import unittest
from typing import Any, Callable


class Solution:
    def __permute(
        self,
        prefix: list[int],
        remaining: set[int],
        permutations: list[list[int]],
    ) -> None:
        if len(remaining) == 0:
            permutations.append(prefix.copy())
        else:
            for value in remaining:
                prefix.append(value)
                reduced = set(remaining)
                reduced.remove(value)
                self.__permute(prefix, reduced, permutations)
                prefix.pop()

    def permute(self, nums: list[int]) -> list[list[int]]:
        permutations: list[list[int]] = []
        remaining = set(nums)
        self.__permute([], remaining, permutations)
        return permutations


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
    def integer_list_comparator(
        l1: list[int], l2: list[int]
    ) -> int:  # pragma: no cover
        if len(l1) < len(l2):
            return -1
        if len(l1) > len(l2):
            return 1
        for l1i, l2i in zip(l1, l2, strict=True):
            if l1i < l2i:
                return -1
            if l1i > l2i:
                return 1
        return 0

    def test_example(self) -> None:
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
        assert len(expected) == len(result)
        for i, expected_i in enumerate(expected):
            assert len(expected_i) == len(result[i])
            for j, expected_i_j in enumerate(expected_i):
                assert expected_i_j == result[i][j]
