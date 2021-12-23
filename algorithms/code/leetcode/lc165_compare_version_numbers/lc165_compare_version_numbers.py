#!/usr/bin/env python3
# https://leetcode.com/problems/compare-version-numbers/

import unittest


class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        parts1 = version1.split('.')
        parts2 = version2.split('.')
        for i in range(max(len(parts1), len(parts2))):
            level1 = 0
            if i < len(parts1):
                level1 = int(parts1[i])
            level2 = 0
            if i < len(parts2):
                level2 = int(parts2[i])
            if level1 < level2:
                return -1
            if level1 > level2:
                return 1
        return 0


class TestCode(unittest.TestCase):
    def test_1__1(self):
        self.assertEqual(0, Solution().compareVersion('1', '1'))

    def test_1__1_0(self):
        self.assertEqual(0, Solution().compareVersion('1', '1.0'))

    def test_2__1(self):
        self.assertEqual(1, Solution().compareVersion('2', '1'))

    def test_1__13_1(self):
        self.assertEqual(-1, Solution().compareVersion('1', '13.1'))

    def test_1_0_1__1(self):
        self.assertEqual(1, Solution().compareVersion('1.0.1', '1'))
