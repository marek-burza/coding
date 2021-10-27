#!/usr/bin/env python3
# https://leetcode.com/problems/same-tree/

import enum
import unittest

from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class NoneCheckResult(enum.Enum):
    ALL_NONE = 1
    SOME_NONE = 2
    NONE_NONE = 3


def noneCheck(tree1, tree2):
    entries = [tree1, tree2]
    all_none = all(entry is None for entry in entries)
    none_none = all(entry is not None for entry in entries)
    some_none = not all_none and not none_none
    if all_none:
        return NoneCheckResult.ALL_NONE
    if some_none:
        return NoneCheckResult.SOME_NONE
    return NoneCheckResult.NONE_NONE


class Solution:

    # pylint: disable=C0301
    def isSameTree(self, tree1: Optional[TreeNode], tree2: Optional[TreeNode]) -> bool:  # noqa
        check = noneCheck(tree1, tree2)
        if check != NoneCheckResult.NONE_NONE:
            return check == NoneCheckResult.ALL_NONE
        queue = []
        queue.append((tree1, tree2))
        while queue:
            node1, node2 = queue.pop(0)
            if node1.val != node2.val:
                return False
            check_left = noneCheck(node1.left, node2.left)
            if check_left == NoneCheckResult.NONE_NONE:
                queue.append((node1.left, node2.left))
            elif check_left == NoneCheckResult.SOME_NONE:
                return False
            check_right = noneCheck(node1.right, node2.right)
            if check_right == NoneCheckResult.NONE_NONE:
                queue.append((node1.right, node2.right))
            elif check_right == NoneCheckResult.SOME_NONE:
                return False
        return True


class TestCode(unittest.TestCase):
    def test_different(self):
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
        self.assertFalse(Solution().isSameTree(an0, bn0))

    def test_same(self):
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
        self.assertTrue(Solution().isSameTree(an0, bn0))

    def test_one_empty(self):
        tree = TreeNode(0)
        self.assertFalse(Solution().isSameTree(tree, None))
        self.assertFalse(Solution().isSameTree(None, tree))

    def test_both_empty(self):
        self.assertTrue(Solution().isSameTree(None, None))

    def test_left(self):
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.left = an1
        self.assertFalse(Solution().isSameTree(an0, bn0))

    def test_right(self):
        an0 = TreeNode(0)
        bn0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.right = an1
        self.assertFalse(Solution().isSameTree(an0, bn0))
