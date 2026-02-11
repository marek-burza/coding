#!/usr/bin/env python3
# https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/

import unittest


class Solution:
    def isValidSerialization(self, preorder: str) -> bool:
        if None is preorder or len(preorder) == 0:
            return False
        items = preorder.split(',')
        kids = [1]
        for node in items:
            while kids[-1] == 2:
                kids.pop()
                if len(kids) == 0:
                    return False
            kids.append(kids.pop() + 1)
            if node != '#':
                kids.append(0)
        while len(kids) != 0 and kids[-1] == 2:
            kids.pop()
        return len(kids) == 0


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertTrue(Solution().isValidSerialization('#'))

    def test_empty_but_not_really(self):
        self.assertFalse(Solution().isValidSerialization('#1'))

    def test_example_1(self):
        self.assertTrue(
            Solution().isValidSerialization('9,3,4,#,#,1,#,#,2,#,6,#,#')
        )

    def test_example_2(self):
        self.assertFalse(Solution().isValidSerialization('1,#'))

    def test_example_3(self):
        self.assertFalse(Solution().isValidSerialization('9,#,#,1'))

    def test_nothing(self):
        self.assertFalse(Solution().isValidSerialization(''))
