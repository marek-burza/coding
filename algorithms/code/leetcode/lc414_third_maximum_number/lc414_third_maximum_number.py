# https://leetcode.com/problems/third-maximum-number/

import heapq
import unittest


class Solution:
    def thirdMax(self, nums: list[int]) -> int:
        max_heap: list[int] = []
        for item in set(nums):
            heapq.heappush(max_heap, item)
            if len(max_heap) > 3:
                heapq.heappop(max_heap)
        if len(max_heap) < 3:
            return max(max_heap)
        return min(max_heap)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().thirdMax([3, 2, 1]) == 1

    def test_example_2(self) -> None:
        assert Solution().thirdMax([1, 2]) == 2

    def test_example_3(self) -> None:
        assert Solution().thirdMax([2, 2, 3, 1]) == 1

    def test_other_1(self) -> None:
        assert Solution().thirdMax([1, 2, 2, 5, 3, 5]) == 2
