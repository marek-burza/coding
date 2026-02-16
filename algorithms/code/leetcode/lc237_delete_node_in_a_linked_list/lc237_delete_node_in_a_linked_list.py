# https://leetcode.com/problems/delete-node-in-a-linked-list/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(self, x: int) -> None:
        self.val = x
        self.next: 'ListNode' | None = None


class Solution:
    def deleteNode(self, node: ListNode) -> None:
        assert node.next is not None
        node.val = node.next.val
        node.next = node.next.next


class TestCode(unittest.TestCase):
    def test_example(self) -> None:
        n1 = ListNode(1)
        n2 = ListNode(2)
        n3 = ListNode(3)
        n4 = ListNode(4)
        n1.next = n2
        n2.next = n3
        n3.next = n4
        Solution().deleteNode(n3)
        expected = [1, 2, 4]
        n: ListNode | None = n1
        for value in expected:
            assert n is not None
            assert value == n.val
            n = n.next
        assert n is None
