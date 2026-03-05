# https://leetcode.com/problems/first-bad-version/

import unittest

lc278_first_bad_version = 0


def isBadVersion(version: int) -> bool:
    # pylint: disable=W0602,W0603
    global lc278_first_bad_version  # noqa: F824
    return lc278_first_bad_version <= version


class Solution:
    def firstBadVersion(self, n: int) -> int:
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
    def generic(self, n: int, expected: int) -> None:
        # pylint: disable=W0603
        global lc278_first_bad_version  # noqa: F824
        lc278_first_bad_version = expected
        self.assertEqual(expected, Solution().firstBadVersion(n))

    def test_example(self) -> None:
        self.generic(8000, 456)

    def test_big_example(self) -> None:
        self.generic(2126753390, 1702766719)

    def test_small_example(self) -> None:
        self.generic(1, 1)
