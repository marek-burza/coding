#!/usr/bin/env python3
# https://leetcode.com/problems/binary-tree-right-side-view/

import unittest

from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __rightSideView(self, root, level, listed):
        if root is not None:
            level += 1
            if level > len(listed):
                listed.append(root.val)
            self.__rightSideView(root.right, level, listed)
            self.__rightSideView(root.left, level, listed)

    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        listed = []
        self.__rightSideView(root, 0, listed)
        return listed


class TestCode(unittest.TestCase):
    def test_example(self):
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
        for i, _ in enumerate(expected):
            self.assertEqual(expected[i], result[i])
