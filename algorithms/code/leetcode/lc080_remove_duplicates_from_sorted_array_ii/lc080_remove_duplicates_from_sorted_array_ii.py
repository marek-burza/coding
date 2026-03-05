# https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

import unittest


class Solution:
    def removeDuplicates(self, nums: list[int]) -> int:
        i = 0
        for n in nums:
            if i < 2 or n > nums[i - 2]:
                nums[i] = n
                i += 1
        return i


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        nums = [1, 1, 1, 2, 2, 3]
        expected = [1, 1, 2, 2, 3]
        assert Solution().removeDuplicates(nums) == 5
        assert expected == nums[: len(expected)]

    def test_1_1_1_1_3_3(self) -> None:
        nums = [1, 1, 1, 1, 3, 3]
        expected = [1, 1, 3, 3]
        assert Solution().removeDuplicates(nums) == 4
        assert expected == nums[: len(expected)]

    def test_1_1(self) -> None:
        nums = [1, 1]
        expected = [1, 1]
        assert Solution().removeDuplicates(nums) == 2
        assert expected == nums[: len(expected)]

    def test_1_2_2(self) -> None:
        nums = [1, 2, 2]
        expected = [1, 2, 2]
        assert Solution().removeDuplicates(nums) == 3
        assert expected == nums[: len(expected)]
