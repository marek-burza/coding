#!/usr/bin/env python3
# https://leetcode.com/problems/path-sum/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if root is None:
            return False
        reduced = targetSum - root.val
        if root.left is None and root.right is None:
            return reduced == 0
        leftHasPathSum = self.hasPathSum(root.left, reduced)
        rightHasPathSum = self.hasPathSum(root.right, reduced)
        return leftHasPathSum or rightHasPathSum


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = TreeNode(1, None, None)
        n2 = TreeNode(2, None, None)
        n7 = TreeNode(7, None, None)
        n13 = TreeNode(13, None, None)
        n4a = TreeNode(4, None, n1)
        n8 = TreeNode(8, n13, n4a)
        n11 = TreeNode(11, n7, n2)
        n4b = TreeNode(4, n11, None)
        n5 = TreeNode(5, n4b, n8)
        self.assertTrue(Solution().hasPathSum(n5, 22))

    def test_left_bend(self):
        right = TreeNode(1, None, None)
        left = TreeNode(2, None, right)
        root = TreeNode(3, left, None)
        self.assertTrue(Solution().hasPathSum(root, 6))

    def test_right_bend(self):
        left = TreeNode(1, None, None)
        right = TreeNode(2, left, None)
        root = TreeNode(3, None, right)
        self.assertTrue(Solution().hasPathSum(root, 6))

    def test_no_path(self):
        left = TreeNode(0, None, None)
        right = TreeNode(0, None, None)
        root = TreeNode(0, left, right)
        self.assertFalse(Solution().hasPathSum(root, 6))

    def test_nothing(self):
        self.assertFalse(Solution().hasPathSum(None, 0))
