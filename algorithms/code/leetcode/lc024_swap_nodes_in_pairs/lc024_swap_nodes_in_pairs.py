#!/usr/bin/env python3
# https://leetcode.com/problems/swap-nodes-in-pairs/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        result = ListNode()
        result.next = head
        node = result
        while node.next is not None and node.next.next is not None:
            first = node.next
            second = node.next.next
            after = node.next.next.next
            node.next = second
            second.next = first
            first.next = after
            node = node.next.next
        return result.next


class TestCode(unittest.TestCase):
    def test_example(self):
        n1 = ListNode(1)
        n2 = ListNode(2)
        n3 = ListNode(3)
        n4 = ListNode(4)
        n1.next = n2
        n2.next = n3
        n3.next = n4
        expected = [2, 1, 4, 3]
        result = Solution().swapPairs(n1)
        for value in expected:
            self.assertNotEqual(None, result)
            self.assertEqual(result.val, value)
            result = result.next

    def test_example_impaired(self):
        n1 = ListNode(1)
        n2 = ListNode(2)
        n3 = ListNode(3)
        n1.next = n2
        n2.next = n3
        expected = [2, 1, 3]
        result = Solution().swapPairs(n1)
        for value in expected:
            self.assertNotEqual(None, result)
            self.assertEqual(result.val, value)
            result = result.next

    def test_nothing(self):
        self.assertIsNone(Solution().swapPairs(None))
