# https://leetcode.com/problems/combinations/

import unittest
from typing import Any, Callable


class Solution:
    # pylint: disable=R0913,R0917
    def __combine(
        self,
        m: int,
        n: int,
        k: int,
        prefix: list[int],
        found: list[list[int]],
    ) -> None:
        for i in range(m, n - (k - 1) + len(prefix) + 1):
            prefix.append(i)
            if len(prefix) == k:
                found.append(prefix.copy())
            else:
                self.__combine(i + 1, n, k, prefix, found)
            prefix.pop()

    def combine(self, n: int, k: int) -> list[list[int]]:
        found: list[list[int]] = []
        self.__combine(1, n, k, [], found)
        return found


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
    def integer_list_comparator(
        l1: list[list[int]], l2: list[list[int]]
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
        expected = [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
        result = Solution().combine(4, 2)
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.integer_list_comparator)
        )
        assert len(expected) == len(result)
        for i, expected_i in enumerate(expected):
            assert len(expected_i) == len(result[i])
            for j, expected_i_j in enumerate(expected_i):
                assert expected_i_j == result[i][j]
