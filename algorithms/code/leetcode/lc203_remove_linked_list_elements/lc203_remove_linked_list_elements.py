# https://leetcode.com/problems/remove-linked-list-elements/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(self, x: int) -> None:
        self.val: int = x
        self.next: ListNode | None = None


class Solution:
    def removeElements(
        self, head: ListNode | None, val: int
    ) -> ListNode | None:
        if head is None:
            return None
        previous: ListNode | None = None
        node: ListNode | None = head
        while node is not None:
            if node.val == val:
                if previous is None:
                    head = node.next
                else:
                    previous.next = node.next
            else:
                previous = node
            node = node.next
        return head


class TestCode(unittest.TestCase):
    def convert(self, array: list[int]) -> ListNode | None:
        handle = ListNode(0)
        last = handle
        for value in array:
            node = ListNode(value)
            last.next = node
            last = node
        return handle.next

    def test_6_1_2_3_4_5_6_7_6__6(self) -> None:
        array = [6, 1, 2, 3, 4, 5, 6, 7, 6]
        listed = self.convert(array)
        listed = Solution().removeElements(listed, 6)
        expected = [1, 2, 3, 4, 5, 7]
        for value in expected:
            assert listed is not None
            assert value == listed.val
            listed = listed.next

    def test_nothing(self) -> None:
        assert Solution().removeElements(None, 0) is None
