# https://leetcode.com/problems/remove-nth-node-from-end-of-list/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: 'ListNode | None' = None
    ) -> None:
        self.val = val
        self.next = following


class Solution:
    def removeNthFromEnd(
        self, head: ListNode | None, n: int
    ) -> ListNode | None:
        # Translate the index number from counted
        # from the back to a one counted from the front
        node = head
        while node is not None:
            n -= 1
            node = node.next
        n = -n
        # Do the deletion
        node = ListNode(0)
        node.next = head
        head = node
        while head is not None:
            if n == 0 and head.next is not None:
                head.next = head.next.next
                break
            head = head.next
            n -= 1
        return node.next


class TestCode(unittest.TestCase):
    def test_1_2__1_1(self) -> None:
        n1 = ListNode(1)
        n2 = ListNode(2)
        n1.next = n2
        n2.next = None
        n = n1
        solution = Solution()
        assert n is not None
        assert n.val == 1
        assert n.next is not None
        assert n.next.val == 2
        check1 = solution.removeNthFromEnd(n, 1)
        assert check1 is not None
        assert check1.val == 1
        check2 = solution.removeNthFromEnd(n, 1)
        assert check2 is None

    def test_none_0(self) -> None:
        solution = Solution()
        n = solution.removeNthFromEnd(None, 0)
        assert n is None
