#!/usr/bin/env python3
# https://leetcode.com/problems/super-ugly-number/

import heapq
import unittest


class Solution:
    def nthUglyNumber(self, n: int) -> int:
        if n == 1:
            return 1
        uglies = [1]
        for _ in range(1, n):
            smallest = heapq.heappop(uglies)
            while uglies and uglies[0] == smallest:
                heapq.heappop(uglies)
            heapq.heappush(uglies, smallest * 2)
            heapq.heappush(uglies, smallest * 3)
            heapq.heappush(uglies, smallest * 5)
        return uglies[0]


class TestCode(unittest.TestCase):
    def test_example(self):
        expected = [1, 2, 3, 4, 5, 6, 8, 9, 10, 12]
        for i, expected_i in enumerate(expected):
            self.assertEqual(expected_i, Solution().nthUglyNumber(i + 1))

    def test_bigger(self):
        self.assertEqual(536870912, Solution().nthUglyNumber(1407))
