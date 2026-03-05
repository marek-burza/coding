# https://leetcode.com/problems/find-all-duplicates-in-an-array/
# #google

import unittest


class Solution:
    def findDuplicates(self, nums: list[int]) -> list[int]:
        result = set()
        for i, _ in enumerate(nums):
            num = abs(nums[i])
            if nums[num - 1] < 0:
                result.add(num)
            else:
                nums[num - 1] = -nums[num - 1]
        return list(result)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().findDuplicates([4, 3, 2, 7, 8, 2, 3, 1]) == [
            2,
            3,
        ]

    def test_example_2(self) -> None:
        assert Solution().findDuplicates([1, 1, 2]) == [1]

    def test_example_3(self) -> None:
        assert not Solution().findDuplicates([1])
