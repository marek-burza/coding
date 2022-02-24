#!/usr/bin/env python3
# https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# #google
# (works for both ordered & unordered trees)

import unittest
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    # pylint: disable=C0301
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:  # noqa
        node = preorder[0]

        index = inorder.index(node)
        left_inorder = inorder[0:index]
        right_inorder = inorder[index+1:]
        left_preorder = preorder[1:len(left_inorder)+1]
        right_preorder = preorder[len(left_inorder)+1:]

        node_obj = TreeNode(node)

        if len(left_preorder) > 0:
            node_obj.left = self.buildTree(left_preorder, left_inorder)
        if len(right_preorder) > 0:
            node_obj.right = self.buildTree(right_preorder, right_inorder)

        return node_obj


class TestCode(unittest.TestCase):
    def test_example(self):
        tree = Solution().buildTree([3, 9, 20, 15, 7], [9, 3, 15, 20, 7])
        self.assertIsNotNone(tree)
        self.assertEqual(3, tree.val)
        self.assertIsNotNone(tree.left)
        self.assertEqual(9, tree.left.val)
        self.assertIsNone(tree.left.left)
        self.assertIsNone(tree.left.right)
        self.assertIsNotNone(tree.right)
        self.assertEqual(20, tree.right.val)
        self.assertIsNotNone(tree.right.left)
        self.assertEqual(15, tree.right.left.val)
        self.assertIsNone(tree.right.left.left)
        self.assertIsNone(tree.right.left.right)
        self.assertIsNotNone(tree.right.right)
        self.assertEqual(7, tree.right.right.val)
        self.assertIsNone(tree.right.right.left)
        self.assertIsNone(tree.right.right.right)
