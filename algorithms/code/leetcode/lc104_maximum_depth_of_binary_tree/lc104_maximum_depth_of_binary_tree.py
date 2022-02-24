#!/usr/bin/env python3
# https://leetcode.com/problems/maximum-depth-of-binary-tree/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        left = self.maxDepth(root.left)
        right = self.maxDepth(root.right)
        return 1 + max(left, right)


class TestCode(unittest.TestCase):
    def test_example(self):
        n0 = TreeNode(self, 0)
        n1 = TreeNode(self, 1)
        n2 = TreeNode(self, 2)
        n3 = TreeNode(self, 3)
        n0.left = n1
        n0.right = n2
        n1.left = None
        n1.right = n3
        n2.left = None
        n2.right = None
        n3.left = None
        n3.right = None
        self.assertEqual(3, Solution().maxDepth(n0))
