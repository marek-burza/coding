# https://leetcode.com/problems/course-schedule-ii/

import unittest


class Solution:
    def findOrder(
        self, numCourses: int, prerequisites: list[list[int]]
    ) -> list[int]:
        prereq: dict[int, list[int]] = {
            course: [] for course in range(numCourses)
        }
        for course, prerequisite in prerequisites:
            prereq[course].append(prerequisite)
        result: list[int] = []
        visited: set[int] = set()
        cycle: set[int] = set()

        def dfs(course: int) -> bool:
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
    def test_example_1(self) -> None:
        assert sorted(Solution().findOrder(2, [[1, 0]])) == [0, 1]

    def test_example_2(self) -> None:
        assert sorted(
            Solution().findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2]])
        ) == [0, 1, 2, 3]

    def test_example_3(self) -> None:
        assert sorted(Solution().findOrder(1, [])) == [0]

    def test_cycle(self) -> None:
        assert not sorted(Solution().findOrder(2, [[0, 1], [1, 0]]))
