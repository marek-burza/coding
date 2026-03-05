# https://leetcode.com/problems/combination-sum-iv/

import unittest


class Solution:
    def combinationSum4(self, nums: list[int], target: int) -> int:
        cache = [0] * (target + 1)
        cache[0] = 1
        for i in range(target):
            if not cache[i]:
                continue
            for num in nums:
                if i + num <= target:
                    cache[i + num] += cache[i]
        return cache[target]


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        nums = [1, 2, 3]
        self.assertEqual(7, Solution().combinationSum4(nums, 4))

    def test_longer_example(self) -> None:
        nums = [4, 2, 1]
        self.assertEqual(39882198, Solution().combinationSum4(nums, 32))

    def test_with_gaps(self) -> None:
        nums = [3, 2]
        self.assertEqual(28, Solution().combinationSum4(nums, 15))
