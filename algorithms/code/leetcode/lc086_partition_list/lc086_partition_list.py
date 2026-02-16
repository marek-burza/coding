#!/usr/bin/env python3
# https://leetcode.com/problems/partition-list/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: 'ListNode' | None = None
    ) -> None:
        self.val = val
        self.next = following


class Solution:
    def partition(self, head: ListNode | None, x: int) -> ListNode | None:
        if head is None:
            return None
        less = ListNode(0)
        more = ListNode(0)
        before = less
        after = more
        while head is not None:
            if head.val < x:
                less.next = head
                less = head
            else:
                more.next = head
                more = head
            head = head.next
        less.next = after.next
        more.next = None
        return before.next


class TestCode(unittest.TestCase):
    def __build(self, array: list[int]) -> ListNode | None:
        head = None
        tail = None
        for value in array:
            if head is None:
                head = tail = ListNode(value)
            else:
                assert tail is not None
                tail.next = ListNode(value)
                tail = tail.next
        if tail is not None:
            tail.next = None
        return head

    def test_1_4_3_2_5_2__3(self) -> None:
        listed = self.__build([1, 4, 3, 2, 5, 2])
        expected = [1, 2, 2, 4, 3, 5]
        result = Solution().partition(listed, 3)
        for value in expected:
            assert result is not None
            assert value == result.val
            result = result.next

    def test_nothing(self) -> None:
        assert Solution().partition(None, 0) is None
