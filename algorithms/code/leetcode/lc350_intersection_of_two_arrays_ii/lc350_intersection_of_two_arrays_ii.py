#!/usr/bin/env python3
# https://leetcode.com/problems/intersection-of-two-arrays-ii/

import unittest
from typing import List


class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        found = []
        i1 = 0
        i2 = 0
        while i1 < len(nums1) and i2 < len(nums2):
            if nums1[i1] < nums2[i2]:
                i1 += 1
                continue
            if nums1[i1] > nums2[i2]:
                i2 += 1
                continue
            found.append(nums1[i1])
            i1 += 1
            i2 += 1
        return found


class TestCode(unittest.TestCase):
    def test_example(self):
        expected = [2, 2]
        result = Solution().intersect([1, 2, 2, 1], [2, 2])
        result.sort()
        self.assertListEqual(expected, result)

    def test_example_flipped(self):
        expected = [2, 2]
        result = Solution().intersect([2, 2], [1, 2, 2, 1])
        result.sort()
        self.assertListEqual(expected, result)

    def test_1_1(self):
        expected = [1]
        result = Solution().intersect([1], [1])
        result.sort()
        self.assertListEqual(expected, result)

    def test_12_11(self):
        expected = [1]
        result = Solution().intersect([1, 2], [1, 1])
        result.sort()
        self.assertListEqual(expected, result)

    def test_495_94985(self):
        expected = [4, 5, 9]
        result = Solution().intersect([4, 9, 5], [9, 4, 9, 8, 5])
        result.sort()
        self.assertListEqual(expected, result)
