#!/usr/bin/env python3
# https://leetcode.com/problems/minimum-depth-of-binary-tree/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    class AnnotatedNode:
        def __init__(self, node, depth):
            self.node = node
            self.depth = depth

    def minDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        queue = []
        queue.append(Solution.AnnotatedNode(root, 1))
        while True:
            annotated = queue.pop(0)
            if annotated.node.left is None and annotated.node.right is None:
                return annotated.depth
            if annotated.node.left is not None:
                queue.append(Solution.AnnotatedNode(
                    annotated.node.left, annotated.depth + 1))
            if annotated.node.right is not None:
                queue.append(Solution.AnnotatedNode(
                    annotated.node.right, annotated.depth + 1))


class TestCode(unittest.TestCase):
    def test_example(self):
        n3 = TreeNode(3)
        n7 = TreeNode(7)
        n9 = TreeNode(9)
        n15 = TreeNode(15)
        n20 = TreeNode(20)
        n3.left = n9
        n3.right = n20
        n20.left = n15
        n20.right = n7
        n7.left = n7.right = n9.left = n9.right = n15.left = n15.right = None
        self.assertEqual(2, Solution().minDepth(n3))

    def test_left_nothing(self):
        root = TreeNode(3)
        right = TreeNode(7)
        root.right = right
        self.assertEqual(2, Solution().minDepth(root))

    def test_right_nothing(self):
        root = TreeNode(3)
        left = TreeNode(7)
        root.right = left
        self.assertEqual(2, Solution().minDepth(root))

    def test_nothing(self):
        self.assertEqual(0, Solution().minDepth(None))
