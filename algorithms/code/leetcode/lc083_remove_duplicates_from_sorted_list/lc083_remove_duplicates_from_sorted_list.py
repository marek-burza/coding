#!/usr/bin/env python3
# https://leetcode.com/problems/remove-duplicates-from-sorted-list/

import unittest

from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        anchor = head
        while head is not None:
            while head.next is not None and head.val == head.next.val:
                head.next = head.next.next
            head = head.next
        return anchor


class TestCode(unittest.TestCase):
    def test_1_1_2(self):
        n1a = ListNode(1)
        n1b = ListNode(1)
        n2 = ListNode(2)
        n1a.next = n1b
        n1b.next = n2
        n2.next = None
        result = Solution().deleteDuplicates(n1a)
        self.assertEqual(1, result.val)
        self.assertEqual(2, result.next.val)
        self.assertEqual(None, result.next.next)

    def test_1_1_2_3_3(self):
        n1a = ListNode(1)
        n1b = ListNode(1)
        n2 = ListNode(2)
        n3a = ListNode(3)
        n3b = ListNode(3)
        n1a.next = n1b
        n1b.next = n2
        n2.next = n3a
        n3a.next = n3b
        n3b.next = None
        result = Solution().deleteDuplicates(n1a)
        self.assertEqual(1, result.val)
        self.assertEqual(2, result.next.val)
        self.assertEqual(3, result.next.next.val)
        self.assertEqual(None, result.next.next.next)
