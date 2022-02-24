#!/usr/bin/env python3
# https://leetcode.com/problems/binary-tree-level-order-traversal/

import unittest
from typing import List, Optional


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

    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        result: List[List[int]] = []
        queue = []
        if root is not None:
            queue.append(self.AnnotatedNode(root, 1))
        depth = 0
        line: List[int] = None
        while queue:
            annotated = queue.pop(0)
            if depth != annotated.depth:
                depth = annotated.depth
                line = []
                result.append(line)
            # if line is not None:
            line.append(annotated.node.val)
            if annotated.node.left is not None:
                queue.append(self.AnnotatedNode(
                    annotated.node.left, annotated.depth + 1))
            if annotated.node.right is not None:
                queue.append(self.AnnotatedNode(
                    annotated.node.right, annotated.depth + 1))
        return result


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
        expected = [[3], [9, 20], [15, 7]]
        result = Solution().levelOrder(n3)
        self.assertEqual(len(expected), len(result))
        for i, _ in enumerate(expected):
            self.assertEqual(len(expected[i]), len(result[i]))
            for j, _ in enumerate(expected[i]):
                self.assertEqual(expected[i][j], result[i][j])

    def test_nothing(self):
        result = Solution().levelOrder(None)
        self.assertEqual(0, len(result))
