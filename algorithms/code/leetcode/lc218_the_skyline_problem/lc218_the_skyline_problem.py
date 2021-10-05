#!/usr/bin/env python3
# https://leetcode.com/problems/the-skyline-problem/

import heapq
import unittest

from typing import List


class Solution:
    class Building:
        def __init__(self, left, right, height):
            self.left = left
            self.right = right
            self.height = height

        def __lt__(self, other):
            return self.height > other.height

    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        skyline = []
        if not buildings:
            return skyline
        # Build list of spots
        spots = {}
        for building in buildings:
            if building[0] == building[1]:
                continue
            entry = Solution.Building(*building)
            for spot in building[0: 2]:
                if spot not in spots:
                    spots[spot] = []
                spots[spot].append(entry)
        sorted_spots = sorted(spots.keys())
        # Prepare view
        ground = Solution.Building(0, sorted_spots[-1], 0)
        view = []
        view.append(ground)
        # Check all spots and build skyline
        current = 0
        for at in sorted_spots:
            for building in spots[at]:
                if at == building.left:
                    # Building entering the view
                    heapq.heappush(view, building)
                else:
                    # Building leaving the view
                    index = view.index(building)
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
        for i, _ in enumerate(expected):
            self.assertEqual(len(skyline[i]), len(expected[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(skyline[i][j], expected[i][j])

    def test_example_1(self):
        buildings = [
            [2, 9, 10], [3, 7, 15],
            [5, 12, 12], [15, 20, 10], [19, 24, 8]
        ]
        expected = [
            [2, 10], [3, 15], [7, 12],
            [12, 0], [15, 10], [20, 8], [24, 0]
        ]
        self.generic(buildings, expected)

    def test_example_2(self):
        buildings = [
            [0, 2, 3], [2, 5, 3]
        ]
        expected = [
            [0, 3], [5, 0]
        ]
        self.generic(buildings, expected)

    def test_coverage_gaps(self):
        buildings = [
            [0, 2, 3], [2, 5, 3], [0, 0, 10]
        ]
        expected = [
            [0, 3], [5, 0]
        ]
        self.generic(buildings, expected)
        self.assertEqual(len(Solution().getSkyline([])), 0)
        self.assertEqual(len(Solution().getSkyline(None)), 0)
