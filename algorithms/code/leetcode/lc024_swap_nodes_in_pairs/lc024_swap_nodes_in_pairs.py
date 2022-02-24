#!/usr/bin/env python3
# https://leetcode.com/problems/swap-nodes-in-pairs/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    # pylint: disable=C0301
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:  # noqa
        result = ListNode()
        result.next = head
        head = result
        while head.next is not None and head.next.next is not None:
            first = head.next
            second = head.next.next
            after = head.next.next.next
            head.next = second
            second.next = first
            first.next = after
            head = head.next.next
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
