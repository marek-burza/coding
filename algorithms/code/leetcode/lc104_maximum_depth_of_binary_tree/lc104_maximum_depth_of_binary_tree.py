# https://leetcode.com/problems/maximum-depth-of-binary-tree/

from __future__ import annotations

import unittest


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: TreeNode | None = None,
        right: TreeNode | None = None,
    ):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxDepth(self, root: TreeNode | None) -> int:
        if root is None:
            return 0
        left = self.maxDepth(root.left)
        right = self.maxDepth(root.right)
        return 1 + max(left, right)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        n0 = TreeNode(0)
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n0.left = n1
        n0.right = n2
        n1.left = None
        n1.right = n3
        n2.left = None
        n2.right = None
        n3.left = None
        n3.right = None
        assert Solution().maxDepth(n0) == 3
