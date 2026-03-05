# https://leetcode.com/problems/sum-root-to-leaf-numbers/

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
    def __sumNumbers(self, root: TreeNode | None, prefix: int) -> int:
        if root is None:
            return 0
        prefix = prefix * 10 + root.val
        if root.left is None and root.right is None:
            return prefix
        left = self.__sumNumbers(root.left, prefix)
        right = self.__sumNumbers(root.right, prefix)
        return left + right

    def sumNumbers(self, root: TreeNode | None) -> int:
        return self.__sumNumbers(root, 0)


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        self.assertEqual(25, Solution().sumNumbers(root))

    def test_nothing(self) -> None:
        self.assertEqual(0, Solution().sumNumbers(None))

    def test_left(self) -> None:
        root = TreeNode(1)
        root.left = TreeNode(2)
        self.assertEqual(12, Solution().sumNumbers(root))

    def test_right(self) -> None:
        root = TreeNode(1)
        root.right = TreeNode(3)
        self.assertEqual(13, Solution().sumNumbers(root))
