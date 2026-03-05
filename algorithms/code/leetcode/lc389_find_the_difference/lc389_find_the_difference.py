# https://leetcode.com/problems/find-the-difference/

import unittest
from collections import Counter


class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        count_t = Counter(t)
        count_s = Counter(s)
        result = ''
        for key, count in count_t.items():
            if count_s[key] != count:
                result = key
                break
        return result


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().findTheDifference('abcd', 'abcde') == 'e'

    def test_example_2(self) -> None:
        assert Solution().findTheDifference('', 'y') == 'y'
