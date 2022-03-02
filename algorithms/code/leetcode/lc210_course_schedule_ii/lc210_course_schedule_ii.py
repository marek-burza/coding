#!/usr/bin/env python3
# https://leetcode.com/problems/shortest-palindrome/
# #amazon

import unittest
from typing import Dict, List


class Solution:
    def findOrder(
        self, numCourses: int, prerequisites: List[List[int]]
    ) -> List[int]:
        prereq: Dict[int, List[int]] = {
            course: [] for course in range(numCourses)
        }
        for course, prerequisite in prerequisites:
            prereq[course].append(prerequisite)
        result = []
        visited, cycle = set(), set()

        def dfs(course):
            if course in cycle:
                return False
            if course in visited:
                return True
            cycle.add(course)
            for prerequisite in prereq[course]:
                if not dfs(prerequisite):
                    return False
            cycle.remove(course)
            visited.add(course)
            result.append(course)
            return True

        for course in range(numCourses):
            if not dfs(course):
                return []

        return result


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual([0, 1], sorted(Solution().findOrder(2, [[1, 0]])))

    def test_example_2(self):
        self.assertListEqual(
            [0, 1, 2, 3],
            sorted(Solution().findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2]])),
        )

    def test_example_3(self):
        self.assertListEqual([0], sorted(Solution().findOrder(1, [])))

    def test_cycle(self):
        self.assertListEqual(
            [], sorted(Solution().findOrder(2, [[0, 1], [1, 0]]))
        )
