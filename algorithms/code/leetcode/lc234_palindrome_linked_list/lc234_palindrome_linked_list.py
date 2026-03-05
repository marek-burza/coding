# https://leetcode.com/problems/palindrome-linked-list/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: ListNode | None = None
    ) -> None:
        self.val = val
        self.next = following


class Solution:
    def isPalindrome(self, head: ListNode | None) -> bool:
        listed = []
        while head is not None:
            listed.append(head.val)
            head = head.next
        return all(
            listed[i] == listed[-1 - i] for i in range(len(listed) // 2)
        )


class TestCode(unittest.TestCase):
    def test_palindrome_odd(self) -> None:
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(2)
        listed.next.next.next = ListNode(1)
        listed.next.next.next.next = ListNode(0)
        assert Solution().isPalindrome(listed)

    def test_palindrome_even(self) -> None:
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(1)
        listed.next.next.next = ListNode(0)
        assert Solution().isPalindrome(listed)

    def test_not_palindrome_odd(self) -> None:
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(2)
        listed.next.next.next = ListNode(8)
        listed.next.next.next.next = ListNode(0)
        assert not Solution().isPalindrome(listed)

    def test_not_palindrome_even(self) -> None:
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(8)
        listed.next.next.next = ListNode(0)
        assert not Solution().isPalindrome(listed)

    def test_empty(self) -> None:
        assert Solution().isPalindrome(None)

    def test_single(self) -> None:
        assert Solution().isPalindrome(ListNode(0))
