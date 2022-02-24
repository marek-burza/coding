#!/usr/bin/env python3
# https://leetcode.com/problems/invert-binary-tree/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if root is not None:
            node = root.left
            root.left = root.right
            root.right = node
            self.invertTree(root.left)
            self.invertTree(root.right)
        return root


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n6 = TreeNode(6)
        n7 = TreeNode(7)
        n9 = TreeNode(9)
        n4.left = n2
        n4.right = n7
        n2.left = n1
        n2.right = n3
        n7.left = n6
        n7.right = n9
        inverted = Solution().invertTree(n4)
        self.assertEqual(4, inverted.val)
        self.assertEqual(7, inverted.left.val)
        self.assertEqual(9, inverted.left.left.val)
        self.assertEqual(6, inverted.left.right.val)
        self.assertEqual(2, inverted.right.val)
        self.assertEqual(3, inverted.right.left.val)
        self.assertEqual(1, inverted.right.right.val)
