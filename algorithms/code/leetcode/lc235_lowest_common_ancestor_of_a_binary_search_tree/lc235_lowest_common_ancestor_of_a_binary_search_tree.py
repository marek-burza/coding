#!/usr/bin/env python3
# https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

import unittest


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:  # noqa
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
        tree = TreeNode(6)
        tree.left = TreeNode(2)
        tree.right = TreeNode(8)
        tree.left.left = TreeNode(0)
        tree.left.right = TreeNode(4)
        tree.left.right.left = TreeNode(3)
        tree.left.right.right = TreeNode(5)
        tree.right.left = TreeNode(7)
        tree.right.right = TreeNode(9)
        self.assertEqual(tree, Solution().lowestCommonAncestor(
            tree, tree.left, tree.right))
        self.assertEqual(tree.left, Solution().lowestCommonAncestor(
            tree, tree.left, tree.left.right))

    def test_example_1(self):
        tree = TreeNode(5)
        tree.left = TreeNode(3)
        tree.right = TreeNode(6)
        tree.left.left = TreeNode(2)
        tree.left.right = TreeNode(4)
        tree.left.left.left = TreeNode(1)
        self.assertEqual(3, Solution().lowestCommonAncestor(
            tree, tree.left.left.left, tree.left.right).val)

    def test_example_2(self):
        tree = TreeNode(6)
        tree.left = TreeNode(2)
        tree.right = TreeNode(8)
        tree.left.left = TreeNode(0)
        tree.left.right = TreeNode(4)
        tree.left.left = TreeNode(7)
        tree.right.right = TreeNode(9)
        tree.left.right.left = TreeNode(3)
        tree.left.right.right = TreeNode(5)
        self.assertEqual(4, Solution().lowestCommonAncestor(
            tree, tree.left.right.left, tree.left.right.right).val)

    def test_example_3(self):
        tree = TreeNode(2)
        tree.left = TreeNode(1)
        self.assertEqual(2, Solution().lowestCommonAncestor(
            tree, tree, tree.left).val)

    def test_example_4(self):
        tree = TreeNode(2)
        tree.right = TreeNode(3)
        self.assertEqual(2, Solution().lowestCommonAncestor(
            tree, tree.right, tree).val)
