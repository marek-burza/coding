# https://leetcode.com/problems/kth-largest-element-in-an-array/

import heapq
import unittest


class Solution:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        heap: list[int] = []
        for num in nums:
            if len(heap) < k or num > heap[0]:
                heapq.heappush(heap, num)
                if len(heap) > k:
                    heapq.heappop(heap)
        return heapq.heappop(heap)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        nums = [3, 2, 1, 5, 6, 4]
        assert Solution().findKthLargest(nums, 2) == 5

    def test_example_2(self) -> None:
        nums = [2, 1]
        assert Solution().findKthLargest(nums, 2) == 1
