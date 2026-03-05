#!/usr/bin/env python3
# https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

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
    def flatten(self, root: TreeNode | None) -> None:
        while root is not None:
            if root.left is not None:
                node = root.left
                while node.right is not None:
                    node = node.right
                node.right = root.right
                root.right = root.left
                root.left = None
            root = root.right


class TestCode(unittest.TestCase):
    def __test(self, expected: TreeNode | None, root: TreeNode | None) -> None:
        if None is expected:
            self.assertEqual(None, root)
        else:
            self.assertIsNotNone(root)
            assert root is not None and expected is not None
            self.assertEqual(expected.val, root.val)
            self.__test(expected.left, root.left)
            self.__test(expected.right, root.right)

    def test_example(self) -> None:
        root = TreeNode(1)
        root.left = TreeNode(2)
        root.left.left = TreeNode(3)
        root.left.right = TreeNode(4)
        root.right = TreeNode(5)
        root.right.right = TreeNode(6)
        expected = TreeNode(1)
        expected.right = TreeNode(2)
        expected.right.right = TreeNode(3)
        expected.right.right.right = TreeNode(4)
        expected.right.right.right.right = TreeNode(5)
        expected.right.right.right.right.right = TreeNode(6)
        Solution().flatten(root)
        self.__test(expected, root)

    def test_other_1(self) -> None:
        root = TreeNode(1)
        root.right = TreeNode(2)
        root.right.left = TreeNode(3)
        expected = TreeNode(1)
        expected.right = TreeNode(2)
        expected.right.right = TreeNode(3)
        Solution().flatten(root)
        self.__test(expected, root)

    def test_other_2(self) -> None:
        root = TreeNode(3)
        root.left = TreeNode(1)
        root.left.left = TreeNode(4)
        root.left.left.right = TreeNode(2)
        expected = TreeNode(3)
        expected.right = TreeNode(1)
        expected.right.right = TreeNode(4)
        expected.right.right.right = TreeNode(2)
        Solution().flatten(root)
        self.__test(expected, root)
