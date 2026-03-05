# https://leetcode.com/problems/house-robber-iii/

from __future__ import annotations

import unittest
from functools import cache


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


@cache
def rob_cached(root: TreeNode | None) -> int:
    if root is None:
        return 0
    excl = rob_cached(root.left) + rob_cached(root.right)
    incl = root.val
    if root.left is not None:
        incl += rob_cached(root.left.left) + rob_cached(root.left.right)
    if root.right is not None:
        incl += rob_cached(root.right.left) + rob_cached(root.right.right)
    return max(incl, excl)


class Solution:
    def rob(self, root: TreeNode | None) -> int:
        return rob_cached(root)

    # # Bottom-up
    # def rob(self, root: TreeNode | None) -> int:
    #     return max(self.rob_incl_excl(root))

    # def rob_incl_excl(self, root: TreeNode | None) -> list[int]:
    #     if root is None:
    #         return [0, 0]
    #     incl_l, excl_l = self.rob_incl_excl(root.left)
    #     incl_r, excl_r = self.rob_incl_excl(root.right)
    #     incl = root.val + excl_l + excl_r
    #     excl = max(incl_l, excl_l) + max(incl_r, excl_r)
    #     return [incl, excl]


class TestCode(unittest.TestCase):
    def test_example_1(self) -> None:
        t3 = TreeNode(3)
        l2 = TreeNode(2)
        lr3 = TreeNode(3)
        r3 = TreeNode(3)
        rr1 = TreeNode(1)
        t3.left = l2
        t3.right = r3
        t3.left.right = lr3
        t3.right.right = rr1
        assert Solution().rob(t3) == 7

    def test_example_2(self) -> None:
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
        assert Solution().rob(t3) == 9
