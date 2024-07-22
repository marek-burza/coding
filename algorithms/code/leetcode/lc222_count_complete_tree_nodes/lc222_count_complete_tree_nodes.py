#!/usr/bin/env python3
# https://leetcode.com/problems/count-complete-tree-nodes/

import unittest
from typing import Optional


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: Optional['TreeNode'] = None,
        right: Optional['TreeNode'] = None,
    ):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        return 1 + self.countNodes(root.left) + self.countNodes(root.right)


class TestCode(unittest.TestCase):
    def test_example_1(self):
        self.assertEqual(0, Solution().countNodes(None))

    def test_example_2(self):
        self.assertEqual(1, Solution().countNodes(TreeNode(1)))
