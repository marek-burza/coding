#!/usr/bin/env python3
# https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

import unittest
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    @staticmethod
    def linked_to_listed(linked: Optional[ListNode]) -> List:
        listed: List = []
        while linked is not None:
            listed.append(linked.val)
            linked = linked.next
        return listed

    @staticmethod
    def __generate(listed: List, head: int, tail: int) -> Optional[TreeNode]:
        if head >= tail:
            return None
        length = tail - head
        half = head + (length >> 1)
        root_left = Solution.__generate(listed, head, half)
        root_right = Solution.__generate(listed, half + 1, tail)
        return TreeNode(listed[half], root_left, root_right)

    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        listed = Solution.linked_to_listed(head)
        return Solution.__generate(listed, 0, len(listed))


class TestCode(unittest.TestCase):
    @staticmethod
    def listed_to_linked(listed: List) -> Optional[ListNode]:
        linked: Optional[ListNode] = None
        for value in listed[::-1]:
            linked = ListNode(value, linked)
        return linked

    class MinMax:
        def __init__(self):
            self.min = float('inf')
            self.max = float('-inf')

    def __depth(self, root, level, depths):
        if root is None:
            depths.max = level if depths.max < level else depths.max
            depths.min = level if depths.min > level else depths.min
        else:
            self.__depth(root.left, level + 1, depths)
            self.__depth(root.right, level + 1, depths)

    def __test(self, root, linked):
        if root is not None:
            linked = self.__test(root.left, linked)
            self.assertEqual(linked.val, root.val)
            linked = linked.next
            linked = self.__test(root.right, linked)
        return linked

    def test_bigger(self):
        listed = list(range(-999, 15340 + 1))
        linked = self.listed_to_linked(listed)
        root = Solution().sortedListToBST(linked)
        depths = self.MinMax()
        self.__depth(root, 0, depths)
        self.assertTrue(depths.max - depths.min < 2)
        self.assertEqual(None, self.__test(root, linked))
