#!/usr/bin/env python3
# https://leetcode.com/problems/h-index/

import unittest
from typing import List


class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        counts = [0] * (n + 1)
        for citation in citations:
            if citation > n:
                counts[n] += 1
            else:
                counts[citation] += 1
        counted = 0
        i = n
        while True:
            counted += counts[i]
            if counted >= i:
                return i
            i -= 1


class TestCode(unittest.TestCase):
    def test_example(self):
        citations = [3, 0, 6, 1, 5]
        self.assertEqual(3, Solution().hIndex(citations))

    def test_none(self):
        citations = [0, 0, 0, 0, 0]
        self.assertEqual(0, Solution().hIndex(citations))

    def test_100(self):
        citations = [100]
        self.assertEqual(1, Solution().hIndex(citations))
