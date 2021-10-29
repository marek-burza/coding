#!/usr/bin/env python3
# https://leetcode.com/problems/binary-tree-paths/

import unittest

from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __binaryTreePaths(self, root, prefix, result):
        prefix += ('' if len(prefix) == 0 else '->') + str(root.val)
        if root.left is None and root.right is None:
            result.append(prefix)
        else:
            if root.left is not None:
                self.__binaryTreePaths(root.left, prefix, result)
            if root.right is not None:
                self.__binaryTreePaths(root.right, prefix, result)

    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        result = []
        if root is not None:
            self.__binaryTreePaths(root, '', result)
        return result


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n5 = TreeNode(5)
        n1.left = n2
        n1.right = n3
        n2.right = n5
        expected = ['1->2->5', '1->3']
        result = Solution().binaryTreePaths(n1)
        result.sort()
        self.assertListEqual(expected, result)

    def test_example_mirrored(self):
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n5 = TreeNode(5)
        n1.right = n2
        n1.left = n3
        n2.left = n5
        expected = ['1->2->5', '1->3']
        result = Solution().binaryTreePaths(n1)
        result.sort()
        self.assertListEqual(expected, result)

    def test_nothing(self):
        self.assertListEqual([], Solution().binaryTreePaths(None))
