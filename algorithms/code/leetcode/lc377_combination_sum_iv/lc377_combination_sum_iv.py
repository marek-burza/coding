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
        assert Solution().combinationSum4(nums, 4) == 7

    def test_longer_example(self) -> None:
        nums = [4, 2, 1]
        assert Solution().combinationSum4(nums, 32) == 39882198

    def test_with_gaps(self) -> None:
        nums = [3, 2]
        assert Solution().combinationSum4(nums, 15) == 28
