# https://leetcode.com/problems/linked-list-cycle-ii/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(self, x: int) -> None:
        self.val: int = x
        self.next: ListNode | None = None


class Solution:
    def detectCycle(self, head: ListNode | None) -> ListNode | None:
        if head is None:
            return None
        p1: ListNode | None = head
        p2: ListNode | None = head
        while p2 is not None and p2.next is not None:
            assert p1 is not None
            p1 = p1.next
            p2 = p2.next.next
            if p1 is p2:
                break
        if p2 is None or p2.next is None:
            return None
        p1 = head
        while p1 is not p2:
            assert p1 is not None and p2 is not None
            p1 = p1.next
            p2 = p2.next
        return p2
        # Instead of that once can break the cycle once detected
        # and then search for common element by measuring lengths


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
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
        assert c1 == Solution().detectCycle(a1)

    def test_1_minus1(self) -> None:
        node = ListNode(1)
        assert Solution().detectCycle(node) is None

    def test_nothing(self) -> None:
        assert Solution().detectCycle(None) is None
