#!/usr/bin/env python3
# https://leetcode.com/problems/k-closest-points-to-origin/
# #google

import heapq
import unittest
from typing import List


class Solution:
    class Item:
        def __init__(self, point):
            self.point = point

        def __lt__(self, other):
            other_distance = other.point[0] ** 2 + other.point[1] ** 2
            self_distance = self.point[0] ** 2 + self.point[1] ** 2
            return other_distance > self_distance

    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        heap: List[Solution.Item] = []
        for point in points:
            heapq.heappush(heap, Solution.Item(point))
        result = []
        for _ in range(k):
            result.append(heapq.heappop(heap).point)
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [[-2, 2]], Solution().kClosest([[1, 3], [-2, 2]], 1)
        )

    def test_example_2(self):
        self.assertListEqual(
            [[3, 3], [-2, 4]],
            Solution().kClosest([[3, 3], [5, -1], [-2, 4]], 2),
        )
