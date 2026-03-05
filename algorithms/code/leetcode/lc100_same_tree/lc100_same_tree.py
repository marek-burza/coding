# https://leetcode.com/problems/same-tree/

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
    def isSameTree(
        self, tree1: TreeNode | None, tree2: TreeNode | None
    ) -> bool:
        if tree1 is None or tree2 is None:
            return tree1 is None and tree2 is None
        queue: list[tuple[TreeNode, TreeNode]] = []
        queue.append((tree1, tree2))
        while queue:
            node1, node2 = queue.pop(0)
            if node1.val != node2.val:
                return False
            node1_left, node2_left = node1.left, node2.left
            if node1_left is not None and node2_left is not None:
                queue.append((node1_left, node2_left))
            elif not (node1_left is None and node2_left is None):
                return False
            node1_right, node2_right = node1.right, node2.right
            if node1_right is not None and node2_right is not None:
                queue.append((node1_right, node2_right))
            elif not (node1_right is None and node2_right is None):
                return False
        return True


class TestCode(unittest.TestCase):
    def test_different(self) -> None:
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        bn1 = TreeNode(1)
        an2 = TreeNode(2)
        bn2 = TreeNode(3)
        an0.left = an1
        an0.right = an2
        bn0.left = bn1
        bn0.right = bn2
        an1.left = None
        an2.right = None
        bn1.left = None
        bn2.right = None
        assert not Solution().isSameTree(an0, bn0)

    def test_same(self) -> None:
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        bn1 = TreeNode(1)
        an2 = TreeNode(2)
        bn2 = TreeNode(2)
        an0.left = an1
        an0.right = an2
        bn0.left = bn1
        bn0.right = bn2
        an1.left = None
        an2.right = None
        bn1.left = None
        bn2.right = None
        assert Solution().isSameTree(an0, bn0)

    def test_one_empty(self) -> None:
        tree = TreeNode(0)
        assert not Solution().isSameTree(tree, None)
        assert not Solution().isSameTree(None, tree)

    def test_both_empty(self) -> None:
        assert Solution().isSameTree(None, None)

    def test_left(self) -> None:
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.left = an1
        assert not Solution().isSameTree(an0, bn0)

    def test_right(self) -> None:
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.right = an1
        assert not Solution().isSameTree(an0, bn0)
