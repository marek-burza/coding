#!/usr/bin/env python3
# https://leetcode.com/problems/merge-sorted-array/

import unittest


class Solution:
    def merge(self, nums1, m, nums2, n):
        i = m + n - 1
        m -= 1
        n -= 1
        while m >= 0 and n >= 0:
            if nums1[m] > nums2[n]:
                nums1[i] = nums1[m]
                m -= 1
            else:
                nums1[i] = nums2[n]
                n -= 1
            i -= 1
        while n >= 0:
            nums1[i] = nums2[n]
            n -= 1
            i -= 1


class TestCode(unittest.TestCase):
    def test_example_1(self):
        nums1 = [1, 2, 3, 0, 0, 0]
        nums2 = [2, 5, 6]
        expected = [1, 2, 2, 3, 5, 6]
        Solution().merge(nums1, 3, nums2, 3)
        self.assertListEqual(expected, nums1)

    def test_example_2(self):
        nums1 = [1]
        nums2 = []
        expected = [1]
        Solution().merge(nums1, 1, nums2, 0)
        self.assertListEqual(expected, nums1)

    def test_example_3(self):
        nums1 = [0]
        nums2 = [1]
        expected = [1]
        Solution().merge(nums1, 0, nums2, 1)
        self.assertListEqual(expected, nums1)

    def test_1_3_7_11_0_0_0__4__4_6_20__3(self):
        nums1 = [1, 3, 7, 11, 0, 0, 0]
        nums2 = [4, 6, 20]
        expected = [1, 3, 4, 6, 7, 11, 20]
        Solution().merge(nums1, 4, nums2, 3)
        self.assertListEqual(expected, nums1)
