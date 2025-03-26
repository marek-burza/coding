#!/usr/bin/env python3
# https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def lowestCommonAncestor(
        self,
        root: Optional[TreeNode],
        p: Optional[TreeNode],
        q: Optional[TreeNode],
    ) -> Optional[TreeNode]:
        if root is not None:
            left = None
            right = None
            if root.left is not None:
                left = self.lowestCommonAncestor(root.left, p, q)
            if root.right is not None:
                right = self.lowestCommonAncestor(root.right, p, q)
            if left is not None and left is not p and left is not q:
                return left
            if right is not None and right is not p and right is not q:
                return right
            gotP = root is p or left is p or right is p
            gotQ = root is q or left is q or right is q
            if gotP and gotQ:
                return root
            if gotP:
                return p
            if gotQ:
                return q
        return None


class TestCode(unittest.TestCase):
    def test_example(self):
        n3 = TreeNode(3, None, None)
        n5 = TreeNode(5, None, None)
        n4 = TreeNode(4, n3, n5)
        n0 = TreeNode(0, None, None)
        n2 = TreeNode(2, n0, n4)
        n7 = TreeNode(7, None, None)
        n9 = TreeNode(9, None, None)
        n8 = TreeNode(8, n7, n9)
        n6 = TreeNode(6, n2, n8)
        self.assertEqual(6, Solution().lowestCommonAncestor(n6, n2, n8).val)
        self.assertEqual(2, Solution().lowestCommonAncestor(n2, n2, n4).val)

    def test_example_1(self):
        n1 = TreeNode(1, None, None)
        n2 = TreeNode(2, n1, None)
        n4 = TreeNode(4, None, None)
        n3 = TreeNode(3, n2, n4)
        n6 = TreeNode(6, None, None)
        n5 = TreeNode(5, n3, n6)
        self.assertEqual(3, Solution().lowestCommonAncestor(n5, n1, n4).val)

    def test_example_2(self):
        n3 = TreeNode(3, None, None)
        n5 = TreeNode(5, None, None)
        n4 = TreeNode(4, n3, n5)
        n7 = TreeNode(7, None, None)
        n2 = TreeNode(2, n7, n4)
        n9 = TreeNode(9, None, None)
        n8 = TreeNode(8, None, n9)
        n6 = TreeNode(6, n2, n8)
        self.assertEqual(4, Solution().lowestCommonAncestor(n6, n3, n5).val)

    def test_example_3(self):
        n1 = TreeNode(1, None, None)
        n2 = TreeNode(2, n1, None)
        self.assertEqual(2, Solution().lowestCommonAncestor(n2, n2, n1).val)

    def test_example_4(self):
        n2 = TreeNode(2, None, None)
        n3 = TreeNode(3, None, n2)
        self.assertEqual(2, Solution().lowestCommonAncestor(n2, n3, n2).val)

    def test_nothing(self):
        self.assertIsNone(Solution().lowestCommonAncestor(None, None, None))
