#!/usr/bin/env python3
# https://leetcode.com/problems/kth-largest-element-in-an-array/

import heapq
import unittest

from typing import List


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        heap = []
        for num in nums:
            if len(heap) < k or num > heap[0]:
                heapq.heappush(heap, num)
                if len(heap) > k:
                    heapq.heappop(heap)
        return heapq.heappop(heap)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums = [3, 2, 1, 5, 6, 4]
        self.assertEqual(5, Solution().findKthLargest(nums, 2))

    def test_example_2(self):
        nums = [2, 1]
        self.assertEqual(1, Solution().findKthLargest(nums, 2))
