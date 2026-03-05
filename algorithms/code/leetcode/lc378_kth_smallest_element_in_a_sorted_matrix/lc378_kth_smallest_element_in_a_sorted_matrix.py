#!/usr/bin/env python3
# https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

import heapq
import unittest
from typing import Any


class Solution:
    class Item:
        def __init__(self, value: int) -> None:
            self.value = value

        def __lt__(self, other: Any) -> bool:
            return other.value < self.value

    def kthSmallest(self, matrix: list[list[int]], k: int) -> int:
        heap: list[Solution.Item] = []
        for row in matrix:
            for cell in row:
                heapq.heappush(heap, Solution.Item(cell))
                if len(heap) > k:
                    heapq.heappop(heap)
        return heapq.heappop(heap).value


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]]
        self.assertEqual(13, Solution().kthSmallest(matrix, 8))
