#!/usr/bin/env python3
# https://leetcode.com/problems/find-k-closest-elements/

import unittest
from typing import List


class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        begin, end = 0, len(arr) - 1 - k
        while begin <= end:
            middle = (begin + end) // 2
            if x - arr[middle] <= arr[middle + k] - x:
                end = middle - 1
            else:
                begin = middle + 1
        return arr[begin : begin + k]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertListEqual(
            [1, 2, 3, 4], Solution().findClosestElements([1, 2, 3, 4, 5], 4, 3)
        )

    def test_example_2(self):
        self.assertListEqual(
            [1, 2, 3, 4],
            Solution().findClosestElements([1, 2, 3, 4, 5], 4, -1),
        )

    def test_other_1(self):
        self.assertListEqual(
            [3, 3, 4],
            Solution().findClosestElements(
                [0, 0, 1, 2, 3, 3, 4, 7, 7, 8], 3, 5
            ),
        )
