#!/usr/bin/env python3
# https://leetcode.com/problems/house-robber-iii/

import unittest

from typing import Optional
# from typing import List
from functools import cache


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    @cache
    def rob(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        excl = self.rob(root.left) + self.rob(root.right)
        incl = root.val
        if root.left is not None:
            incl += self.rob(root.left.left) + self.rob(root.left.right)
        if root.right is not None:
            incl += self.rob(root.right.left) + self.rob(root.right.right)
        return max(incl, excl)

    # # Bottom-up
    # def rob(self, root: Optional[TreeNode]) -> int:
    #     return max(self.rob_incl_excl(root))

    # def rob_incl_excl(self, root: Optional[TreeNode]) -> List[int]:
    #     if root is None:
    #         return [0, 0]
    #     incl_l, excl_l = self.rob_incl_excl(root.left)
    #     incl_r, excl_r = self.rob_incl_excl(root.right)
    #     incl = root.val + excl_l + excl_r
    #     excl = max(incl_l, excl_l) + max(incl_r, excl_r)
    #     return [incl, excl]


class TestCode(unittest.TestCase):
    def test_example_1(self):
        t3 = TreeNode(3)
        l2 = TreeNode(2)
        lr3 = TreeNode(3)
        r3 = TreeNode(3)
        rr1 = TreeNode(1)
        t3.left = l2
        t3.right = r3
        t3.left.right = lr3
        t3.right.right = rr1
        self.assertEqual(Solution().rob(t3), 7)

    def test_example_2(self):
        t3 = TreeNode(3)
        l4 = TreeNode(4)
        ll1 = TreeNode(1)
        lr3 = TreeNode(3)
        r5 = TreeNode(5)
        rr1 = TreeNode(1)
        t3.left = l4
        t3.right = r5
        t3.left.left = ll1
        t3.left.right = lr3
        t3.right.right = rr1
        self.assertEqual(Solution().rob(t3), 9)
