# https://leetcode.com/problems/binary-tree-inorder-traversal/

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
        self.__preorderTraversal(root.left, result)
        result.append(root.val)
        self.__preorderTraversal(root.right, result)

    def inorderTraversal(self, root: TreeNode | None) -> list[int]:
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
        result = Solution().inorderTraversal(node1)
        expected = [1, 3, 2]
        assert expected == result
