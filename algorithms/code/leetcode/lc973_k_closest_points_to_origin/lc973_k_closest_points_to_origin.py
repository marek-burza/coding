# https://leetcode.com/problems/k-closest-points-to-origin/
# #google

import heapq
import unittest
from typing import Any


class Solution:
    class Item:
        def __init__(self, point: list[int]) -> None:
            self.point = point

        def __lt__(self, other: Any) -> bool:
            other_distance = other.point[0] ** 2 + other.point[1] ** 2
            self_distance = self.point[0] ** 2 + self.point[1] ** 2
            return other_distance > self_distance

    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        heap: list[Solution.Item] = []
        for point in points:
            heapq.heappush(heap, Solution.Item(point))
        result = []
        for _ in range(k):
            result.append(heapq.heappop(heap).point)
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        self.assertListEqual(
            [[-2, 2]], Solution().kClosest([[1, 3], [-2, 2]], 1)
        )

    def test_example_2(self) -> None:
        self.assertListEqual(
            [[3, 3], [-2, 4]],
            Solution().kClosest([[3, 3], [5, -1], [-2, 4]], 2),
        )
