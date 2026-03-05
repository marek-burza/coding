# https://leetcode.com/problems/next-permutation/

import unittest


class Solution:
    def nextPermutation(self, nums: list[int]) -> None:
        index1 = -1
        length = len(nums)
        for i in range(length - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                index1 = i
                break
        if index1 == -1:
            nums.reverse()
        else:
            index2 = -1
            for i in range(length - 1, -1, -1):
                if nums[i] > nums[index1]:
                    index2 = i
                    break
            nums[index1], nums[index2] = nums[index2], nums[index1]
            nums[index1 + 1 :] = nums[index1 + 1 :][::-1]


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        nums = [1, 2, 3]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 3, 2], nums)

    def test_example_2(self) -> None:
        nums = [3, 2, 1]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 2, 3], nums)

    def test_example_3(self) -> None:
        nums = [1, 1, 5]
        Solution().nextPermutation(nums)
        self.assertListEqual([1, 5, 1], nums)
