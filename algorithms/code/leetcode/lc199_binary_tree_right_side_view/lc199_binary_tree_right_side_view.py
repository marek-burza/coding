# https://leetcode.com/problems/binary-tree-right-side-view/

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
    def __rightSideView(
        self, root: TreeNode | None, level: int, listed: list[int]
    ) -> None:
        if root is not None:
            level += 1
            if level > len(listed):
                listed.append(root.val)
            self.__rightSideView(root.right, level, listed)
            self.__rightSideView(root.left, level, listed)

    def rightSideView(self, root: TreeNode | None) -> list[int]:
        listed: list[int] = []
        self.__rightSideView(root, 0, listed)
        return listed


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n5 = TreeNode(5)
        n6 = TreeNode(6)
        n1.left = n2
        n1.right = n3
        n2.left = None
        n2.right = n5
        n3.left = None
        n3.right = n4
        n4.left = None
        n4.right = None
        n5.left = n6
        n5.right = None
        n6.left = None
        n6.right = None
        expected = [1, 3, 4, 6]
        result = Solution().rightSideView(n1)
        for i, expected_i in enumerate(expected):
            self.assertEqual(expected_i, result[i])
