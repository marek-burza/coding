# https://leetcode.com/problems/linked-list-cycle/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(self, x: int) -> None:
        self.val = x
        self.next: ListNode | None = None


class Solution:
    def hasCycle(self, head: ListNode | None) -> bool:
        if head is None:
            return False
        if head.next is head:
            return True
        previous = None
        current: ListNode | None = head
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
    def test_empty(self) -> None:
        assert not Solution().hasCycle(None)

    def test_single_cycle(self) -> None:
        node = ListNode(1)
        node.next = node
        assert Solution().hasCycle(node)

    def test_single_no_cycle(self) -> None:
        node = ListNode(1)
        assert not Solution().hasCycle(node)

    def test_two_cycle(self) -> None:
        node1 = ListNode(1)
        node2 = ListNode(2)
        node1.next = node2
        node2.next = node1
        assert Solution().hasCycle(node1)

    def test_two_no_cycle(self) -> None:
        node1 = ListNode(1)
        node2 = ListNode(2)
        node1.next = node2
        assert not Solution().hasCycle(node1)
