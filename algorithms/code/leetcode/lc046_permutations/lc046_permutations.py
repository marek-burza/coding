# https://leetcode.com/problems/permutations/
# #medium

from functools import cmp_to_key


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


class TestCode:
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
        result = sorted(result, key=cmp_to_key(TestCode.integer_list_comparator))
        assert expected == result
