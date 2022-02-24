#!/usr/bin/env python3

# https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

import unittest

from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        result = []
        current = []
        if root is not None:
            current.append(root)
        while current:
            level: List[int] = []
            result.append(level)
            future = []
            for node in current:
                level.append(node.val)
                if node.left is not None:
                    future.append(node.left)
                if node.right is not None:
                    future.append(node.right)
            current = future
        length = len(result)
        for i in range(length // 2):
            exchange = result[i]
            result[i] = result[length - 1 - i]
            result[length - 1 - i] = exchange
        return result


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertEqual(0, len(Solution().levelOrderBottom(None)))

    def test_example(self):
        n3 = TreeNode(3)
        n9 = TreeNode(9)
        n20 = TreeNode(20)
        n15 = TreeNode(15)
        n7 = TreeNode(7)
        n3.left = n9
        n3.right = n20
        n20.left = n15
        n20.right = n7
        expected = [[15, 7], [9, 20], [3]]
        result = Solution().levelOrderBottom(n3)
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertEqual(len(expected[i]), len(result[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])
