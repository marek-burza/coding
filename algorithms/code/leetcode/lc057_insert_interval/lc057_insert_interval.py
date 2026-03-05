# https://leetcode.com/problems/insert-interval/

import unittest


class Solution:
    def insert(
        self,
        intervals: list[list[int]],
        newInterval: list[int],
    ) -> list[list[int]]:
        start = newInterval[0]
        end = newInterval[1]
        before: list[list[int]] = []
        after: list[list[int]] = []
        for interval in intervals:
            interval_start = interval[0]
            interval_end = interval[1]
            if interval_start > end:
                after.append(interval)
            elif interval_end < start:
                before.append(interval)
            else:
                start = min(start, interval_start)
                end = max(end, interval_end)
        return before + [[start, end]] + after


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().insert([[1, 3], [6, 9]], [2, 5]) == [[1, 5], [6, 9]]

    def test_example_2(self) -> None:
        assert Solution().insert(
            [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]],
            [4, 8],
        ) == [[1, 2], [3, 10], [12, 16]]
