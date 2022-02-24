#!/usr/bin/env python3
# https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

import heapq
import unittest
from typing import List


class Solution:
    class Item:
        def __init__(self, value):
            self.value = value

        def __lt__(self, other):
            return other.value < self.value

    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        heap: List[Solution.Item] = []
        for row in matrix:
            for cell in row:
                heapq.heappush(heap, Solution.Item(cell))
                if len(heap) > k:
                    heapq.heappop(heap)
        return heapq.heappop(heap).value


class TestCode(unittest.TestCase):
    def test_example(self):
        matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]]
        self.assertEqual(13, Solution().kthSmallest(matrix, 8))
