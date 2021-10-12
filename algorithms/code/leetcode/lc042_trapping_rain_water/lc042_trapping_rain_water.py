#!/usr/bin/env python3
# https://leetcode.com/problems/trapping-rain-water/

import operator
import unittest


class Solution:
    def __amount(self, height, from_keyword_conflict, to):
        amount = min(height[from_keyword_conflict],
                     height[to]) * (to - from_keyword_conflict - 1)
        i = from_keyword_conflict + 1
        while i < to:
            amount -= height[i]
            i += 1
        return amount

    def trap(self, height):
        if height is None or len(height) < 3:
            return 0
        # Sort the terrain
        decorated = enumerate(height)
        ordered = sorted(decorated, key=operator.itemgetter(1), reverse=True)
        # Fill from the top
        # (pick highest and then extend "exclusion zone")
        count = 0
        left = ordered[0][0]
        right = ordered[0][0]
        for i, _ in ordered:
            if right < i:
                count += self.__amount(height, right, i)
                right = i
            if i < left:
                count += self.__amount(height, i, left)
                left = i
        return count


class TestCode(unittest.TestCase):
    def test_example(self):
        terrain = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
        self.assertEqual(6, Solution().trap(terrain))

    def test_nothing(self):
        self.assertEqual(0, Solution().trap(None))
        self.assertEqual(0, Solution().trap([0, 1]))
