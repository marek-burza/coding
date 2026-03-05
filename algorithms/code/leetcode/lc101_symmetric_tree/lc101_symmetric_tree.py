#!/usr/bin/env python3
# https://leetcode.com/problems/symmetric-tree/

from __future__ import annotations

import unittest


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: 'TreeNode | None' = None,
        right: 'TreeNode | None' = None,
    ) -> None:
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetric(self, root: TreeNode | None) -> bool:
        if root is None:
            return True
        queue: list[tuple[TreeNode, TreeNode]] = []
        queue.append((root, root))
        while queue:
            node1, node2 = queue.pop(0)
            if node1.val != node2.val:
                return False
            node1_left, node2_right = node1.left, node2.right
            if node1_left is not None and node2_right is not None:
                queue.append((node1_left, node2_right))
            elif not (node1_left is None and node2_right is None):
                return False
            node1_right, node2_left = node1.right, node2.left
            if node1_right is not None and node2_left is not None:
                queue.append((node1_right, node2_left))
            elif not (node1_right is None and node2_left is None):
                return False
        return True


class TestCode(unittest.TestCase):
    def test_symmetric(self) -> None:
        n0 = TreeNode(0)
        n1a = TreeNode(1)
        n1b = TreeNode(1)
        n0.left = n1a
        n0.right = n1b
        n1a.left = None
        n1a.right = None
        n1b.left = None
        n1b.right = None
        self.assertTrue(Solution().isSymmetric(n0))

    def test_asymmetric(self) -> None:
        n0 = TreeNode(0)
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n0.left = n1
        n0.right = n2
        n1.left = None
        n1.right = None
        n2.left = None
        n2.right = None
        self.assertFalse(Solution().isSymmetric(n0))

    def test_empty(self) -> None:
        self.assertTrue(Solution().isSymmetric(None))

    def test_left(self) -> None:
        an0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.left = an1
        self.assertFalse(Solution().isSymmetric(an0))

    def test_right(self) -> None:
        an0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.right = an1
        self.assertFalse(Solution().isSymmetric(an0))

    def test_other(self) -> None:
        n2 = TreeNode(2)
        n3l = TreeNode(3)
        n3r = TreeNode(3)
        n4ll = TreeNode(4)
        n5 = TreeNode(5)
        n4rr = TreeNode(4)
        n2.left = n3l
        n2.right = n3r
        n3l.left = n4ll
        n3l.right = n5
        n3r.right = n4rr
        self.assertFalse(Solution().isSymmetric(n2))
