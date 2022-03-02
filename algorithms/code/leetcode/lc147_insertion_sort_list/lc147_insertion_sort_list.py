#!/usr/bin/env python3
# https://leetcode.com/problems/insertion-sort-list/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def insertionSortList(
        self, head: Optional[ListNode]
    ) -> Optional[ListNode]:
        handle = ListNode(0)
        handle.next = head
        tail = handle
        while tail.next is not None:
            # Grab first node after the tail of already ordered nodes
            node = tail.next
            # Remove (extract) that node from the list
            tail.next = tail.next.next
            # Iterate from the node holding the head
            current = handle
            inserted = False
            # Iterate until we reach the node beyond the extracted node
            while current.next is not tail.next:
                # When a node with greater or equal value found
                # then insert extracted node before it
                if node.val <= current.next.val:
                    node.next = current.next
                    current.next = node
                    inserted = True
                    break
                # Move on to the next ordered node
                current = current.next
            # If the extracted node was not inserted then insert it
            # at the end of the ordered list
            if not inserted:
                node.next = tail.next
                current.next = node
                # The extracted node becomes the tail of the ordered list
                tail = node
        return handle.next


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = ListNode(1)
        n2 = ListNode(2)
        n3 = ListNode(3)
        n4 = ListNode(4)
        n5 = ListNode(5)
        n6 = ListNode(6)
        n6.next = n3
        n3.next = n4
        n4.next = n5
        n5.next = n2
        n2.next = n1
        result = Solution().insertionSortList(n6)
        self.assertEqual(n1, result)
        self.assertEqual(n2, n1.next)
        self.assertEqual(n3, n2.next)
        self.assertEqual(n4, n3.next)
        self.assertEqual(n5, n4.next)
        self.assertEqual(n6, n5.next)
        self.assertEqual(None, n6.next)

    def test_1_1(self):
        n1_a = ListNode(1)
        n1_b = ListNode(1)
        n1_a.next = n1_b
        result = Solution().insertionSortList(n1_a)
        self.assertEqual(n1_b, result)
        self.assertEqual(n1_a, n1_b.next)
        self.assertEqual(None, n1_a.next)
