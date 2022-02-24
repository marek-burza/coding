#!/usr/bin/env python3
# https://leetcode.com/problems/binary-search-tree-iterator/

import unittest
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.__stack = []
        while root is not None:
            self.__stack.append(root)
            root = root.left

    def hasNext(self) -> bool:
        return len(self.__stack) != 0

    def next(self) -> int:
        node = self.__stack.pop()
        result = node.val
        node = node.right
        while node is not None:
            self.__stack.append(node)
            node = node.left
        return result


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = TreeNode(1)
        n2 = TreeNode(2)
        n3 = TreeNode(3)
        n4 = TreeNode(4)
        n5 = TreeNode(5)
        n6 = TreeNode(6)
        n7 = TreeNode(7)
        n8 = TreeNode(8)
        n9 = TreeNode(9)
        n10 = TreeNode(10)
        n11 = TreeNode(11)
        n6.left = n2
        n6.right = n10
        n2.left = n1
        n2.right = n4
        n4.left = n3
        n4.right = n5
        n10.left = n9
        n10.right = n11
        n9.left = n8
        n8.left = n7
        iterator = BSTIterator(n6)
        i = 1
        while iterator.hasNext():
            self.assertEqual(i, iterator.next())
            i += 1
