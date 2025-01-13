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
        listed = []
        while head is not None:
            listed.append(head.val)
            head = head.next
        for i in range(len(listed) // 2):
            if listed[i] != listed[-1 - i]:
                return False
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
