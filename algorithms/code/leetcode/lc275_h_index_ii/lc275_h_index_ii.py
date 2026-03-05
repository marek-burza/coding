# https://leetcode.com/problems/h-index-ii/

import unittest


class Solution:
    def hIndex(self, citations: list[int]) -> int:
        n = len(citations)
        a = 0
        z = n
        while a < z:
            m = (a + z) >> 1
            if citations[m] == n - m:
                return n - m
            if citations[m] < n - m:
                a = m + 1
            else:
                z = m
        return n - a


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        citations = [0, 1, 3, 5, 6]
        self.assertEqual(3, Solution().hIndex(citations))

    def test_none(self) -> None:
        citations = [0, 0, 0, 0, 0]
        self.assertEqual(0, Solution().hIndex(citations))

    def test_100(self) -> None:
        citations = [100]
        self.assertEqual(1, Solution().hIndex(citations))
