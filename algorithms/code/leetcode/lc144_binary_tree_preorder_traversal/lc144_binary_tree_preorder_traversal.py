# https://leetcode.com/problems/binary-tree-preorder-traversal/

from __future__ import annotations

import unittest


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: TreeNode | None = None,
        right: TreeNode | None = None,
    ) -> None:
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __preorderTraversal(
        self, root: TreeNode | None, result: list[int]
    ) -> None:
        if root is None:
            return
        result.append(root.val)
        self.__preorderTraversal(root.left, result)
        self.__preorderTraversal(root.right, result)

    def preorderTraversal(self, root: TreeNode | None) -> list[int]:
        result: list[int] = []
        self.__preorderTraversal(root, result)
        return result


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        node1 = TreeNode(1)
        node2 = TreeNode(2)
        node3 = TreeNode(3)
        node1.right = node2
        node2.left = node3
        result = Solution().preorderTraversal(node1)
        expected = [1, 2, 3]
        for i, expected_i in enumerate(expected):
            assert expected_i == result[i]
