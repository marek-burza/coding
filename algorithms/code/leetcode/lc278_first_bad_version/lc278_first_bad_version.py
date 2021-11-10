#!/usr/bin/env python3
# https://leetcode.com/problems/first-bad-version/

import unittest


lc278_first_bad_version = 0


def isBadVersion(version):
    # pylint: disable=W0603
    global lc278_first_bad_version
    return lc278_first_bad_version <= version


class Solution:
    def firstBadVersion(self, n):
        a = 1
        z = n
        while a != z:
            i = (a + z) >> 1
            if not isBadVersion(i):
                a = i + 1
            else:
                z = i
        return a


class TestCode(unittest.TestCase):
    def test_example(self):
        # pylint: disable=W0603
        global lc278_first_bad_version
        solution = Solution()
        lc278_first_bad_version = 456
        self.assertEqual(456, solution.firstBadVersion(8000))

    def test_big_example(self):
        # pylint: disable=W0603
        global lc278_first_bad_version
        solution = Solution()
        lc278_first_bad_version = 1702766719
        self.assertEqual(1702766719, solution.firstBadVersion(2126753390))

    def test_small_example(self):
        # pylint: disable=W0603
        global lc278_first_bad_version
        solution = Solution()
        lc278_first_bad_version = 1
        self.assertEqual(1, solution.firstBadVersion(1))
