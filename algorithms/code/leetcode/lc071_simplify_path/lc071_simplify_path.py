#!/usr/bin/env python3
# https://leetcode.com/problems/simplify-path/

import unittest
from typing import List


class Solution:
    def simplifyPath(self, path: str) -> str:
        filtered: List[str] = []
        for item in path.split('/'):
            if item in ['', '.']:
                continue
            if item == '..':
                if filtered:
                    filtered.pop()
            else:
                filtered.append(item)
        return '/' + '/'.join(filtered)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual('/home', Solution().simplifyPath('/home/'))

    def test_example_2(self):
        self.assertEqual('/home/foo', Solution().simplifyPath('/home//foo/'))

    def test_example_3(self):
        self.assertEqual(
            '/home/user/Pictures',
            Solution().simplifyPath('/home/user/Documents/../Pictures'),
        )

    def test_example_4(self):
        self.assertEqual('/', Solution().simplifyPath('/../'))

    def test_example_5(self):
        self.assertEqual(
            '/.../b/d', Solution().simplifyPath('/.../a/../b/c/../d/./')
        )
