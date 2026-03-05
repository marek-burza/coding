# https://leetcode.com/problems/closest-binary-search-tree-value/
# #google

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
    def closestValue(self, root: TreeNode, target: float) -> int:
        delta = abs(root.val - target)
        if target < root.val and root.left is not None:
            left = self.closestValue(root.left, target)
            if abs(left - target) < delta:
                return left
        if root.val < target and root.right is not None:
            right = self.closestValue(root.right, target)
            if abs(right - target) < delta:
                return right
        return root.val


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        node1 = TreeNode(1)
        node3 = TreeNode(3)
        node2 = TreeNode(2, node1, node3)
        node5 = TreeNode(5)
        node4 = TreeNode(4, node2, node5)
        assert Solution().closestValue(node4, 3.714286) == 4

    def test_other(self) -> None:
        node1 = TreeNode(1)
        node3 = TreeNode(3)
        node2 = TreeNode(2, node1, node3)
        node5 = TreeNode(5)
        node4 = TreeNode(4, node2, node5)
        assert Solution().closestValue(node4, 1.714286) == 2
