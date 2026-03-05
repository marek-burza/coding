#!/usr/bin/env python3
# https://leetcode.com/problems/kth-smallest-element-in-a-bst/

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
    def __kthSmallest(self, root: TreeNode, k: int, counter: TreeNode) -> int:
        if root.left is not None:
            result = self.__kthSmallest(root.left, k, counter)
            if counter.val == k:
                return result
        counter.val += 1
        if counter.val == k:
            return root.val
        if root.right is not None:
            result = self.__kthSmallest(root.right, k, counter)
            if counter.val == k:
                return result
        return 0

    def kthSmallest(self, root: TreeNode | None, k: int) -> int:
        counter = TreeNode(0)
        assert root is not None
        return self.__kthSmallest(root, k, counter)


class TestCode(unittest.TestCase):
    def test_left(self) -> None:
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n4.left = n3
        n3.left = n2
        n2.left = n1
        self.assertEqual(2, Solution().kthSmallest(n4, 2))

    def test_right(self) -> None:
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n1.right = n2
        n2.right = n3
        n3.right = n4
        self.assertEqual(2, Solution().kthSmallest(n1, 2))

    def test_coverage(self) -> None:
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n5 = TreeNode(5)
        n6 = TreeNode(6)
        n2.left = n1
        n2.right = n3
        n4.left = n2
        n4.right = n5
        n5.right = n6
        self.assertEqual(5, Solution().kthSmallest(n4, 5))
