#!/usr/bin/env python3
# https://leetcode.com/problems/first-unique-character-in-a-string/

import unittest


class Solution:
    def firstUniqChar(self, s: str) -> int:
        size = ord('z') - ord('a') + 1
        count = [0] * size
        index = [0] * size
        length = len(s)
        for i in range(length - 1, -1, -1):
            key = ord(s[i]) - ord('a')
            index[key] = i
            count[key] += 1
        minimum = -1
        for i in range(size):
            if count[i] == 1 and (minimum == -1 or index[i] < minimum):
                minimum = index[i]
        return minimum


class TestCode(unittest.TestCase):
    def test_leetcode(self):
        self.assertEqual(0, Solution().firstUniqChar('leetcode'))

    def test_loveleetcode(self):
        self.assertEqual(2, Solution().firstUniqChar('loveleetcode'))

    def test_empty(self):
        self.assertEqual(-1, Solution().firstUniqChar(''))
