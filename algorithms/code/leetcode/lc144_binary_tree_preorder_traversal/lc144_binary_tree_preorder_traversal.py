#!/usr/bin/env python3
# https://leetcode.com/problems/binary-tree-preorder-traversal/

import unittest
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __preorderTraversal(self, root, result):
        if root is None:
            return
        result.append(root.val)
        self.__preorderTraversal(root.left, result)
        self.__preorderTraversal(root.right, result)

    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
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
        result = Solution().preorderTraversal(node1)
        expected = [1, 2, 3]
        for i, expected_i in enumerate(expected):
            self.assertEqual(expected_i, result[i])
