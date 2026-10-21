# https://leetcode.com/problems/subsets-ii/
# #medium

from functools import cmp_to_key


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


class TestCode:
    @staticmethod
    def orderly_comparator(l1: list[int], l2: list[int]) -> int:  # pragma: no cover
        difference = len(l1) - len(l2)
        if difference != 0:
            return difference
        for l1s, l2s in zip(l1, l2, strict=True):
            if l1s < l2s:
                return -1
            if l1s > l2s:
                return 1
        return 0

    def __test(self, expected: list[list[int]], result: list[list[int]]) -> None:
        result = sorted(result, key=cmp_to_key(TestCode.orderly_comparator))
        for result_i in result:
            result_i.sort()
        assert expected == result

    def test_1_2_2(self) -> None:
        listed = Solution().subsetsWithDup([1, 2, 2])
        expected = [[], [1], [2], [1, 2], [2, 2], [1, 2, 2]]
        self.__test(expected, listed)
