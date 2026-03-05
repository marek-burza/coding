# https://leetcode.com/problems/maximum-gap/

import unittest


class Solution:
    def maximumGap(self, nums: list[int]) -> int:
        if nums is None or len(nums) == 0:
            return 0
        n = len(nums)
        maxE = max(nums)
        minE = min(nums)
        length = float((maxE - minE)) / float((n - 1))
        maxA = [minE - 1] * n  # Instead of -inf
        minA = [maxE + 1] * n  # Instead of inf
        for num in nums:
            index = int(((num - minE) / length))
            maxA[index] = max(maxA[index], num)
            minA[index] = min(minA[index], num)
        gap = 0
        prev = maxA[0]
        for i in range(1, n):
            if minA[i] == maxE + 1:  # Instead of inf
                continue
            gap = max(gap, minA[i] - prev)
            prev = maxA[i]
        return gap
        # Pigeon hole principle:
        # We keep the biggest and smallest pigeon fitting in the hole
        # and that's enough to find the gap in linear way


class TestCode(unittest.TestCase):
    def test_33_2_100_70(self) -> None:
        nums1 = [33, 2, 100, 70]
        assert Solution().maximumGap(nums1) == 37

    def test_nothing(self) -> None:
        assert Solution().maximumGap([]) == 0
