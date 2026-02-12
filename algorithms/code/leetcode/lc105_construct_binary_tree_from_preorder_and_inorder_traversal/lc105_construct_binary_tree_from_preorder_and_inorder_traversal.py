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
    def buildTree(
        self, preorder: List[int], inorder: List[int]
    ) -> Optional[TreeNode]:
        node = preorder[0]

        index = inorder.index(node)
        left_inorder = inorder[0:index]
        right_inorder = inorder[index + 1 :]
        left_preorder = preorder[1 : len(left_inorder) + 1]
        right_preorder = preorder[len(left_inorder) + 1 :]

        node_obj = TreeNode(node)

        if len(left_preorder) > 0:
            node_obj.left = self.buildTree(left_preorder, left_inorder)
        if len(right_preorder) > 0:
            node_obj.right = self.buildTree(right_preorder, right_inorder)

        return node_obj


class TestCode(unittest.TestCase):
    def test_example(self):
        tree = Solution().buildTree([3, 9, 20, 15, 7], [9, 3, 15, 20, 7])
        assert tree is not None
        assert tree.val == 3
        assert tree.left is not None
        assert 9, tree.left.val == 9
        assert tree.left.left is None
        assert tree.left.right is None
        assert tree.right is not None
        assert 20, tree.right.val == 20
        assert tree.right.left is not None
        assert 15, tree.right.left.val == 15
        assert tree.right.left.left is None
        assert tree.right.left.right is None
        assert tree.right.right is not None
        assert 7, tree.right.right.val == 4
        assert tree.right.right.left is None
        assert tree.right.right.right is None
