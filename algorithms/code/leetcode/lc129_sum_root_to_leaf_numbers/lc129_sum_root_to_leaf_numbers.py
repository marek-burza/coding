#!/usr/bin/env python3
# https://leetcode.com/problems/sum-root-to-leaf-numbers/

import unittest

from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __sumNumbers(self, root, prefix):
        if root is None:
            return 0
        prefix = prefix * 10 + root.val
        if root.left is None and root.right is None:
            return prefix
        left = self.__sumNumbers(root.left, prefix)
        right = self.__sumNumbers(root.right, prefix)
        return left + right

    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        return self.__sumNumbers(root, 0)


class TestCode(unittest.TestCase):
    def test_example(self):
        root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        self.assertEqual(25, Solution().sumNumbers(root))

    def test_nothing(self):
        self.assertEqual(0, Solution().sumNumbers(None))

    def test_left(self):
        root = TreeNode(1)
        root.left = TreeNode(2)
        self.assertEqual(12, Solution().sumNumbers(root))

    def test_right(self):
        root = TreeNode(1)
        root.right = TreeNode(3)
        self.assertEqual(13, Solution().sumNumbers(root))
