#!/usr/bin/env python3
# https://leetcode.com/problems/symmetric-tree/

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
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if root is None:
            return True
        queue = []
        queue.append((root, root))
        while queue:
            node1, node2 = queue.pop(0)
            if node1.val != node2.val:
                return False
            check_left_to_right = noneCheck(node1.left, node2.right)
            if check_left_to_right == NoneCheckResult.NONE_NONE:
                queue.append((node1.left, node2.right))
            elif check_left_to_right == NoneCheckResult.SOME_NONE:
                return False
            check_right_to_left = noneCheck(node1.right, node2.left)
            if check_right_to_left == NoneCheckResult.NONE_NONE:
                queue.append((node1.right, node2.left))
            elif check_right_to_left == NoneCheckResult.SOME_NONE:
                return False
        return True


class TestCode(unittest.TestCase):
    def test_symmetric(self):
        n0 = TreeNode(0)
        n1a = TreeNode(1)
        n1b = TreeNode(1)
        n0.left = n1a
        n0.right = n1b
        n1a.left = None
        n1a.right = None
        n1b.left = None
        n1b.right = None
        self.assertTrue(Solution().isSymmetric(n0))

    def test_asymmetric(self):
        n0 = TreeNode(0)
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n0.left = n1
        n0.right = n2
        n1.left = None
        n1.right = None
        n2.left = None
        n2.right = None
        self.assertFalse(Solution().isSymmetric(n0))

    def test_empty(self):
        self.assertTrue(Solution().isSymmetric(None))

    def test_left(self):
        an0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.left = an1
        self.assertFalse(Solution().isSymmetric(an0))

    def test_right(self):
        an0 = TreeNode(0)
        an1 = TreeNode(1)
        an0.right = an1
        self.assertFalse(Solution().isSymmetric(an0))

    def test_other(self):
        n2 = TreeNode(2)
        n3l = TreeNode(3)
        n3r = TreeNode(3)
        n4ll = TreeNode(4)
        n5 = TreeNode(5)
        n4rr = TreeNode(4)
        n2.left = n3l
        n2.right = n3r
        n3l.left = n4ll
        n3l.right = n5
        n3r.right = n4rr
        self.assertFalse(Solution().isSymmetric(n2))
