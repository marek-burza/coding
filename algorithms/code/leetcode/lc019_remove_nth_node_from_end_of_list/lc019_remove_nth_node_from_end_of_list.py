#!/usr/bin/env python3
# https://leetcode.com/problems/remove-nth-node-from-end-of-list/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def removeNthFromEnd(
        self, head: Optional[ListNode], n: int
    ) -> Optional[ListNode]:
        # Translate the index number from counted
        # from the back to a one counted from the front
        node = head
        while node is not None:
            n -= 1
            node = node.next
        n = -n
        # Do the deletion
        node = ListNode(0)
        node.next = head
        head = node
        while head is not None:
            if n == 0:
                if head.next is not None:
                    head.next = head.next.next
                    break
            head = head.next
            n -= 1
        return node.next


class TestCode(unittest.TestCase):
    def test_1_2__1_1(self):
        n1 = ListNode(1)
        n2 = ListNode(2)
        n1.next = n2
        n2.next = None
        n = n1
        solution = Solution()
        self.assertEqual(n.val, 1)
        self.assertEqual(n.next.val, 2)
        n = solution.removeNthFromEnd(n, 1)
        self.assertEqual(n.val, 1)
        n = solution.removeNthFromEnd(n, 1)
        self.assertEqual(n, None)

    def test_none_0(self):
        solution = Solution()
        n = solution.removeNthFromEnd(None, 0)
        self.assertEqual(n, None)
