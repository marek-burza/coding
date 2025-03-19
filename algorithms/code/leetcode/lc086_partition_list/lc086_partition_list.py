#!/usr/bin/env python3
# https://leetcode.com/problems/partition-list/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def partition(
        self, head: Optional[ListNode], x: int
    ) -> Optional[ListNode]:
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
    def __build(self, array):
        head = None
        tail = None
        for value in array:
            if head is None:
                head = tail = ListNode(value)
            else:
                tail.next = ListNode(value)
                tail = tail.next
        if tail is not None:
            tail.next = None
        return head

    def test_1_4_3_2_5_2__3(self):
        listed = self.__build([1, 4, 3, 2, 5, 2])
        expected = [1, 2, 2, 4, 3, 5]
        result = Solution().partition(listed, 3)
        for value in expected:
            self.assertEqual(value, result.val)
            result = result.next

    def test_nothing(self):
        self.assertIsNone(Solution().partition(None, 0))
