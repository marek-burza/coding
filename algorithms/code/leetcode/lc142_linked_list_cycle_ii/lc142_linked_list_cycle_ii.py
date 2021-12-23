#!/usr/bin/env python3
# https://leetcode.com/problems/linked-list-cycle-ii/

import unittest

from typing import Optional


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        p1 = head
        p2 = head
        while p2 is not None and p2.next is not None:
            p1 = p1.next
            p2 = p2.next.next
            if p1 is p2:
                break
        if p2 is None or p2.next is None:
            return None
        p1 = head
        while p1 is not p2:
            p1 = p1.next
            p2 = p2.next
        return p2
        # Instead of that once can break the cycle once detected
        # and then search for common element by measuring lengths


class TestCode(unittest.TestCase):
    def test_example(self):
        a1 = ListNode(0xA1)
        a2 = ListNode(0xA2)
        b1 = ListNode(0xB1)
        b2 = ListNode(0xB2)
        b3 = ListNode(0xB3)
        c1 = ListNode(0xC1)
        c2 = ListNode(0xC2)
        c3 = ListNode(0xC3)
        a1.next = a2
        a2.next = c1
        b1.next = b2
        b2.next = b3
        b3.next = c1
        c1.next = c2
        c2.next = c3
        c3.next = b1
        self.assertEqual(c1, Solution().detectCycle(a1))

    def test_1_minus1(self):
        node = ListNode(1)
        self.assertEqual(None, Solution().detectCycle(node))

    def test_nothing(self):
        self.assertEqual(None, Solution().detectCycle(None))
