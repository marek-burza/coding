#!/usr/bin/env python3
# https://leetcode.com/problems/palindrome-linked-list/

import unittest

from typing import Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        count = 0
        node = head
        while node is not None:
            count += 1
            node = node.next
        previous = None
        i = 0
        while i < count // 2:
            following = head.next
            head.next = previous
            previous = head
            head = following
            i += 1
        forward = head.next if (count & 1) == 1 else head
        backward = previous
        while forward is not None:
            if forward.val != backward.val:
                return False
            forward = forward.next
            backward = backward.next
        return True


class TestCode(unittest.TestCase):
    def test_palindrome_odd(self):
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(2)
        listed.next.next.next = ListNode(1)
        listed.next.next.next.next = ListNode(0)
        self.assertTrue(Solution().isPalindrome(listed))

    def test_palindrome_even(self):
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(1)
        listed.next.next.next = ListNode(0)
        self.assertTrue(Solution().isPalindrome(listed))

    def test_not_palindrome_odd(self):
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(2)
        listed.next.next.next = ListNode(8)
        listed.next.next.next.next = ListNode(0)
        self.assertFalse(Solution().isPalindrome(listed))

    def test_not_palindrome_even(self):
        listed = ListNode(0)
        listed.next = ListNode(1)
        listed.next.next = ListNode(8)
        listed.next.next.next = ListNode(0)
        self.assertFalse(Solution().isPalindrome(listed))

    def test_empty(self):
        self.assertTrue(Solution().isPalindrome(None))

    def test_single(self):
        self.assertTrue(Solution().isPalindrome(ListNode(0)))
