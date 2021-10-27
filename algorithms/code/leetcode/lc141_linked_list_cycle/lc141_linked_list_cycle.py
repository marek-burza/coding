#!/usr/bin/env python3
# https://leetcode.com/problems/linked-list-cycle/

import unittest

from typing import Optional


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        if head is None:
            return False
        if head.next is head:
            return True
        previous = None
        current = head
        count = 0
        while current is not None:
            if current is head:
                count += 1
            detached = current
            current = current.next
            detached.next = previous
            previous = detached
        return count > 1


class TestCode(unittest.TestCase):
    def test_empty(self):
        self.assertFalse(Solution().hasCycle(None))

    def test_single_cycle(self):
        node = ListNode(1)
        node.next = node
        self.assertTrue(Solution().hasCycle(node))

    def test_single_no_cycle(self):
        node = ListNode(1)
        self.assertFalse(Solution().hasCycle(node))

    def test_two_cycle(self):
        node1 = ListNode(1)
        node2 = ListNode(2)
        node1.next = node2
        node2.next = node1
        self.assertTrue(Solution().hasCycle(node1))

    def test_two_no_cycle(self):
        node1 = ListNode(1)
        node2 = ListNode(2)
        node1.next = node2
        self.assertFalse(Solution().hasCycle(node1))
