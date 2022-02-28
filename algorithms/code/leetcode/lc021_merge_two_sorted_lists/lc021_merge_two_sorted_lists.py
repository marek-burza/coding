#!/usr/bin/env python3
# https://leetcode.com/problems/merge-two-sorted-lists/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    # pylint: disable=C0301
    def mergeTwoLists(
        self, l1: Optional[ListNode], l2: Optional[ListNode]
    ) -> Optional[ListNode]:  # noqa
        handle = ListNode()
        current = handle
        current.next = None
        while l1 is not None and l2 is not None:
            if l1.val < l2.val:
                current.next = l1
                l1 = l1.next
            else:
                current.next = l2
                l2 = l2.next
            current = current.next
        if l1 is not None:
            current.next = l1
        if l2 is not None:
            current.next = l2
        return handle.next


class TestCode(unittest.TestCase):
    def __generic(self, n0):
        self.assertEqual(n0.val, 1)
        self.assertEqual(n0.next.val, 2)
        self.assertEqual(n0.next.next.val, 3)
        self.assertEqual(n0.next.next.next.val, 4)
        self.assertEqual(n0.next.next.next.next.val, 5)
        self.assertEqual(n0.next.next.next.next.next.val, 6)

    def test_1_3_5_7_9__2_4_6(self):
        n9 = ListNode(9)
        n9.next = None
        n7 = ListNode(7)
        n7.next = n9
        n5 = ListNode(5)
        n5.next = n7
        n3 = ListNode(3)
        n3.next = n5
        n1 = ListNode(1)
        n1.next = n3
        n6 = ListNode(6)
        n6.next = None
        n4 = ListNode(4)
        n4.next = n6
        n2 = ListNode(2)
        n2.next = n4
        n0 = Solution().mergeTwoLists(n1, n2)
        self.__generic(n0)
        self.assertEqual(n0.next.next.next.next.next.next.val, 7)
        self.assertEqual(n0.next.next.next.next.next.next.next.val, 9)

    def test_1_2_3__4_5_6(self):
        n3 = ListNode(3, None)
        n2 = ListNode(2, n3)
        n1 = ListNode(1, n2)
        n6 = ListNode(6, None)
        n5 = ListNode(5, n6)
        n4 = ListNode(4, n5)
        n0 = Solution().mergeTwoLists(n1, n4)
        self.__generic(n0)
        self.assertEqual(n0.next.next.next.next.next.next, None)
