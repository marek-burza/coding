#!/usr/bin/env python3
# https://leetcode.com/problems/the-skyline-problem/

import heapq
import unittest
from typing import Dict, List


class Solution:
    class Building:
        def __init__(self, left, right, height):
            self.left = left
            self.right = right
            self.height = height

        def __lt__(self, other):
            return self.height > other.height

    # pylint: disable=R0914
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        skyline: List[List[int]] = []
        if not buildings:
            return skyline
        # Build list of spots
        spots: Dict[int, List[Solution.Building]] = {}
        for building in buildings:
            if building[0] == building[1]:
                continue
            entry = Solution.Building(*building)
            for spot in building[0:2]:
                if spot not in spots:
                    spots[spot] = []
                spots[spot].append(entry)
        sorted_spots = sorted(spots.keys())
        # Prepare view
        ground = Solution.Building(0, sorted_spots[-1], 0)
        view: List[Solution.Building] = []
        view.append(ground)
        # Check all spots and build skyline
        current = 0
        for at in sorted_spots:
            for building_obj in spots[at]:
                if at == building_obj.left:
                    # Building entering the view
                    heapq.heappush(view, building_obj)
                else:
                    # Building leaving the view
                    index = view.index(building_obj)
                    view[index] = view[-1]
                    view.pop()
                    heapq.heapify(view)
            following = view[0].height
            if current != following:
                point = [at, following]
                skyline.append(point)
            current = following
        return skyline


class TestCode(unittest.TestCase):
    def generic(self, buildings, expected):
        skyline = Solution().getSkyline(buildings)
        self.assertEqual(len(skyline), len(expected))
        for i, expected_i in enumerate(expected):
            self.assertEqual(len(skyline[i]), len(expected_i))
            for j, expected_i_j in enumerate(expected_i):
                self.assertEqual(skyline[i][j], expected_i_j)

    def test_example_1(self):
        buildings = [
            [2, 9, 10],
            [3, 7, 15],
            [5, 12, 12],
            [15, 20, 10],
            [19, 24, 8],
        ]
        expected = [
            [2, 10],
            [3, 15],
            [7, 12],
            [12, 0],
            [15, 10],
            [20, 8],
            [24, 0],
        ]
        self.generic(buildings, expected)

    def test_example_2(self):
        buildings = [[0, 2, 3], [2, 5, 3]]
        expected = [[0, 3], [5, 0]]
        self.generic(buildings, expected)

    def test_coverage_gaps(self):
        buildings = [[0, 2, 3], [2, 5, 3], [0, 0, 10]]
        expected = [[0, 3], [5, 0]]
        self.generic(buildings, expected)
        self.assertEqual(len(Solution().getSkyline([])), 0)
