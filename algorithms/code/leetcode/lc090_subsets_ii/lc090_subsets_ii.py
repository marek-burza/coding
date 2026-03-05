# https://leetcode.com/problems/subsets-ii/

import unittest
from typing import Any, Callable


class Solution:
    def __subsets(
        self,
        nums: list[int],
        offset: int,
        current: list[int],
        listed: list[list[int]],
    ) -> None:
        listed.append(current.copy())
        i = offset
        while i < len(nums):
            count = 1
            j = i + 1
            while j < len(nums) and nums[j - 1] == nums[j]:
                count += 1
                j += 1
            for _ in range(count):
                current.append(nums[i])
                self.__subsets(nums, i + count, current, listed)
            for _ in range(count):
                current.pop()
            i += count

    def subsetsWithDup(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        listed: list[list[int]] = []
        self.__subsets(nums, 0, [], listed)
        return listed


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
        l1: list[int], l2: list[int]
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
        self, expected: list[list[int]], result: list[list[int]]
    ) -> None:
        result = sorted(
            result, key=TestCode.cmp_to_key(TestCode.orderly_comparator)
        )
        assert len(expected) == len(result)
        for i, expected_i in enumerate(expected):
            assert len(expected_i) == len(result[i])
            result[i].sort()
            for j, expected_i_j in enumerate(expected_i):
                assert expected_i_j == result[i][j]

    def test_1_2_2(self) -> None:
        listed = Solution().subsetsWithDup([1, 2, 2])
        expected = [[], [1], [2], [1, 2], [2, 2], [1, 2, 2]]
        self.__test(expected, listed)
