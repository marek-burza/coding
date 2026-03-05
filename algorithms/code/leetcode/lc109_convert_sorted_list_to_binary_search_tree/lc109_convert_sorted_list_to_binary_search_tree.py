# https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: 'ListNode | None' = None
    ) -> None:
        self.val = val
        self.next = following


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: 'TreeNode | None' = None,
        right: 'TreeNode | None' = None,
    ) -> None:
        self.val = val
        self.left = left
        self.right = right


class Solution:
    @staticmethod
    def linked_to_listed(linked: ListNode | None) -> list:
        listed: list = []
        while linked is not None:
            listed.append(linked.val)
            linked = linked.next
        return listed

    @staticmethod
    def __generate(listed: list, head: int, tail: int) -> TreeNode | None:
        if head >= tail:
            return None
        length = tail - head
        half = head + (length >> 1)
        root_left = Solution.__generate(listed, head, half)
        root_right = Solution.__generate(listed, half + 1, tail)
        return TreeNode(listed[half], root_left, root_right)

    def sortedListToBST(self, head: ListNode | None) -> TreeNode | None:
        listed = Solution.linked_to_listed(head)
        return Solution.__generate(listed, 0, len(listed))


class TestCode(unittest.TestCase):
    @staticmethod
    def listed_to_linked(listed: list) -> ListNode | None:
        linked: ListNode | None = None
        for value in listed[::-1]:
            linked = ListNode(value, linked)
        return linked

    class MinMax:
        def __init__(self) -> None:
            self.min = float('inf')
            self.max = float('-inf')

    def __depth(
        self, root: TreeNode | None, level: int, depths: MinMax
    ) -> None:
        if root is None:
            depths.max = level if depths.max < level else depths.max
            depths.min = level if depths.min > level else depths.min
        else:
            self.__depth(root.left, level + 1, depths)
            self.__depth(root.right, level + 1, depths)

    def __test(
        self, root: TreeNode | None, linked: ListNode | None
    ) -> ListNode | None:
        if root is not None:
            linked = self.__test(root.left, linked)
            assert linked is not None
            assert linked.val == root.val
            linked = linked.next
            linked = self.__test(root.right, linked)
        return linked

    def test_bigger(self) -> None:
        listed = list(range(-999, 15340 + 1))
        linked = self.listed_to_linked(listed)
        root = Solution().sortedListToBST(linked)
        depths = self.MinMax()
        self.__depth(root, 0, depths)
        assert depths.max - depths.min < 2
        assert self.__test(root, linked) is None
