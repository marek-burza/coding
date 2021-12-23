#!/usr/bin/env python3
# https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

import unittest

from typing import Optional


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
    def __length(self, head):
        count = 0
        while head is not None:
            count += 1
            head = head.next
        return count

    def __generate(self, head, length):
        if head is None or length <= 0:
            return None
        half = length >> 1
        middle = head
        for _ in range(half):
            middle = middle.next
        root = TreeNode(middle.val)
        root.left = self.__generate(head, half)
        root.right = self.__generate(middle.next, length - half - 1)
        return root

    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        return self.__generate(head, self.__length(head))


class TestCode(unittest.TestCase):
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

    def __iterated(self, minimum, maximum):
        array = [0] * (maximum - minimum + 1)
        for i, _ in enumerate(array):
            array[i] = minimum + i
        length = len(array)
        listed = ListNode(0)
        node = listed
        for i in range(length):
            node.next = ListNode(array[i])
            node = node.next
        return listed.next

    def __test(self, root, listed):
        if root is None:
            return listed
        listed = self.__test(root.left, listed)
        self.assertEqual(listed.val, root.val)
        listed = listed.next
        listed = self.__test(root.right, listed)
        return listed

    def test_bigger(self):
        listed = self.__iterated(-999, 15340)
        root = Solution().sortedListToBST(listed)
        depths = self.MinMax()
        self.__depth(root, 0, depths)
        self.assertTrue(depths.max - depths.min < 2)
        self.assertEqual(None, self.__test(root, listed))
