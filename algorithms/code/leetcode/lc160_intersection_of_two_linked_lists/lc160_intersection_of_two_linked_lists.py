# https://leetcode.com/problems/intersection-of-two-linked-lists/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(self, x: int) -> None:
        self.val: int = x
        self.next: ListNode | None = None


class Solution:
    def __detectCycle(self, head: ListNode) -> ListNode | None:
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

    def __last(self, listed: ListNode) -> ListNode:
        while listed.next is not None:
            listed = listed.next
        return listed

    def getIntersectionNode(
        self, headA: ListNode, headB: ListNode
    ) -> ListNode | None:
        last = self.__last(headA)
        last.next = headB
        node = self.__detectCycle(headA)
        last.next = None
        return node

    # One can also measure the length of lists, skip their delta,
    # iterate in parallel until common node found


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
        c3.next = None
        assert Solution().getIntersectionNode(a1, b1) == c1

    def test_non_intersecting(self) -> None:
        l4 = ListNode(4)
        l6 = ListNode(6)
        l2 = ListNode(2)
        l2.next = l6
        l6.next = l4
        l1 = ListNode(1)
        l5 = ListNode(5)
        l5.next = l1
        assert Solution().getIntersectionNode(l2, l5) is None
