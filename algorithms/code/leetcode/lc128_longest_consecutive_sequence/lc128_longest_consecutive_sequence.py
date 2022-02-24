#!/usr/bin/env python3
# https://leetcode.com/problems/longest-consecutive-sequence/

import unittest

from typing import List, Dict


class Solution:
    class Range:
        def __init__(self, a, z):
            self.a = a
            self.z = z

    def longestConsecutive(self, nums: List[int]) -> int:
        seen = set()
        mapped: Dict[int, Solution.Range] = {}
        length = 0
        for num in nums:
            if num in seen:
                continue
            seen.add(num)
            less = num - 1 in mapped
            more = num + 1 in mapped
            ante = None
            post = None
            if less:
                ante = mapped[num - 1]
            if more:
                post = mapped[num + 1]
            a = num
            z = num
            if less and more:
                a = ante.a
                z = post.z
            if less:
                a = ante.a
            if more:
                z = post.z
            ranged = self.Range(a, z)
            mapped[a] = ranged
            mapped[z] = ranged
            span = z - a + 1
            if span > length:
                length = span
        return length
        # This can be simplified by storing only the length of the range
        # in the hash table instead of range itself


class TestCode(unittest.TestCase):
    def test_100_4_200_1_3_2(self):
        nums1 = [100, 4, 200, 1, 3, 2]
        self.assertEqual(4, Solution().longestConsecutive(nums1))

    def test_longer(self):
        nums2 = [4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9,
                 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2, -9, 1]
        self.assertEqual(8, Solution().longestConsecutive(nums2))
