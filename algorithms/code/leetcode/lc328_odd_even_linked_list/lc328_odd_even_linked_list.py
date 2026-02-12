#!/usr/bin/env python3
# https://leetcode.com/problems/odd-even-linked-list/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, val: int = 0, following: Optional['ListNode'] = None):
        self.val = val
        self.next = following


class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        evenHead = ListNode(0)
        oddHead = ListNode(0)
        evenTail = evenHead
        oddTail = oddHead
        odd = True
        while head is not None:
            if odd:
                oddTail.next = head
                oddTail = oddTail.next
                odd = False
            else:
                evenTail.next = head
                evenTail = evenTail.next
                odd = True
            head = head.next
        evenTail.next = None
        oddTail.next = evenHead.next
        return oddHead.next


class TestCode(unittest.TestCase):
    def test_example(self):
        head = ListNode(0)
        tail = head
        for i in range(1, 6):
            tail.next = ListNode(i)
            tail = tail.next
        result = Solution().oddEvenList(head.next)
        expected = [1, 3, 5, 2, 4]
        for value in expected:
            assert result is not None
            assert value, result.val
            result = result.next
        assert result is None

    def test_null(self):
        assert Solution().oddEvenList(None) is None
