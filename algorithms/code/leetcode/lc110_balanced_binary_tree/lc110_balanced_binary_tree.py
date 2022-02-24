#!/usr/bin/env python3
# https://leetcode.com/problems/balanced-binary-tree/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __balancedHeight(self, root):
        if root is None:
            return 0
        left = self.__balancedHeight(root.left)
        if left == -1:
            return -1
        right = self.__balancedHeight(root.right)
        if right == -1:
            return -1
        if abs(left - right) > 1:
            return -1
        return 1 + max(left, right)

    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        return self.__balancedHeight(root) != -1


class TestCode(unittest.TestCase):
    def test_balanced(self):
        left = TreeNode(2, TreeNode(1, None, None), TreeNode(3, None, None))
        right = TreeNode(6, TreeNode(5, None, None), TreeNode(7, None, None))
        root = TreeNode(4, left, right)
        self.assertTrue(Solution().isBalanced(root))

    def test_imbalanced_right(self):
        right = TreeNode(6, TreeNode(5, None, None), TreeNode(7, None, None))
        root = TreeNode(4, None, right)
        self.assertFalse(Solution().isBalanced(root))

    def test_imbalanced_left(self):
        left = TreeNode(2, TreeNode(1, None, None), TreeNode(3, None, None))
        root = TreeNode(4, None, left)
        self.assertFalse(Solution().isBalanced(root))

    def test_imbalanced_deep_left(self):
        left = TreeNode(2, TreeNode(1, TreeNode(3, None, None), None), None)
        right = TreeNode(6, TreeNode(5, None, None), TreeNode(7, None, None))
        root = TreeNode(4, left, right)
        self.assertFalse(Solution().isBalanced(root))

    def test_imbalanced_deep_right(self):
        left = TreeNode(2, TreeNode(1, None, None), TreeNode(3, None, None))
        right = TreeNode(6, TreeNode(5, None, TreeNode(7, None, None)), None)
        root = TreeNode(4, left, right)
        self.assertFalse(Solution().isBalanced(root))
