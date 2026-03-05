# https://leetcode.com/problems/count-complete-tree-nodes/

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
    def countNodes(self, root: TreeNode | None) -> int:
        if root is None:
            return 0
        return 1 + self.countNodes(root.left) + self.countNodes(root.right)


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        assert Solution().countNodes(None) == 0

    def test_example_2(self) -> None:
        assert Solution().countNodes(TreeNode(1)) == 1
