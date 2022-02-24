#!/usr/bin/env python3
# https://leetcode.com/problems/binary-tree-inorder-traversal/

import unittest

from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __preorderTraversal(self, root, result):
        if root is None:
            return
        self.__preorderTraversal(root.left, result)
        result.append(root.val)
        self.__preorderTraversal(root.right, result)

    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result: List[int] = []
        self.__preorderTraversal(root, result)
        return result


class TestCode(unittest.TestCase):
    def test_example(self):
        node1 = TreeNode(1)
        node2 = TreeNode(2)
        node3 = TreeNode(3)
        node1.right = node2
        node2.left = node3
        result = Solution().inorderTraversal(node1)
        expected = [1, 3, 2]
        self.assertListEqual(expected, result)
