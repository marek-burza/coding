#!/usr/bin/env python3

# https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

import collections
import unittest
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    @staticmethod
    def __sortedArrayToBST(
        nums: List[int], head: int, tail: int
    ) -> Optional[TreeNode]:
        if head >= tail:
            return None
        length = tail - head
        half = head + (length >> 1)
        root_left = Solution.__sortedArrayToBST(nums, head, half)
        root_right = Solution.__sortedArrayToBST(nums, half + 1, tail)
        return TreeNode(nums[half], root_left, root_right)

    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        return Solution.__sortedArrayToBST(nums, 0, len(nums))


class TestCode(unittest.TestCase):  # pragma: no cover
    @staticmethod
    def __find_extreme(root, init, relation):
        if root is None:
            return 0
        nodes = collections.deque()
        nodes.append(root)
        levels = collections.deque()
        levels.append(1)
        extremum = init
        while nodes:
            node = nodes.popleft()
            level = levels.popleft()
            if node.left is None and node.right is None:
                extremum = relation(level, extremum)
            else:
                if node.left is not None:
                    nodes.append(node.left)
                    levels.append(level + 1)
                if node.right is not None:
                    nodes.append(node.right)
                    levels.append(level + 1)
        return extremum

    @staticmethod
    def __min_height(root):
        return TestCode.__find_extreme(root, float('inf'), min)

    @staticmethod
    def __max_height(root):
        return TestCode.__find_extreme(root, float('-inf'), max)

    @staticmethod
    def __reconstruct(root, listed):
        if root is None:
            return
        if root.left is not None:
            TestCode.__reconstruct(root.left, listed)
        listed.append(root.val)
        if root.right is not None:
            TestCode.__reconstruct(root.right, listed)

    @staticmethod
    def __is_bst(root):
        listed = []
        TestCode.__reconstruct(root, listed)
        previous = float('-inf')
        for value in listed:
            if previous > value:
                return False
            previous = value
        return True

    def test_empty(self):
        self.assertEqual(None, Solution().sortedArrayToBST([]))

    def test_depth_and_ordering(self):
        nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        root = Solution().sortedArrayToBST(nums)
        difference = TestCode.__max_height(root) - TestCode.__min_height(root)
        self.assertTrue(0 <= difference <= 1)
        self.assertTrue(TestCode.__is_bst(root))
