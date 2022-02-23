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
    def __sortedArrayToBST(self, nums, from_here, to_there):
        if from_here > to_there:
            return None
        at = (from_here + to_there) >> 1
        node = TreeNode(nums[at])
        node.left = self.__sortedArrayToBST(nums, from_here, at - 1)
        node.right = self.__sortedArrayToBST(nums, at + 1, to_there)
        return node

    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        return self.__sortedArrayToBST(nums, 0, len(nums) - 1)


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
                if relation(level, extremum):
                    extremum = level
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
        return TestCode.__find_extreme(
            root, float('inf'), lambda level, extremum: level < extremum)

    @staticmethod
    def __max_height(root):
        return TestCode.__find_extreme(
            root, float('-inf'), lambda level, extremum: level > extremum)

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
