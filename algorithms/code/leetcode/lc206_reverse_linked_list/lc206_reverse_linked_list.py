#!/usr/bin/env python3
# https://leetcode.com/problems/reverse-linked-list/

import unittest
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        ante = None
        while head is not None:
            post = head.next
            head.next = ante
            ante = head
            head = post
        return ante


class TestCode(unittest.TestCase):
    @staticmethod
    def linked_to_listed(linked: Optional[ListNode]) -> List:
        listed: List = []
        while linked is not None:
            listed.append(linked.val)
            linked = linked.next
        return listed

    @staticmethod
    def listed_to_linked(listed: List) -> Optional[ListNode]:
        linked: Optional[ListNode] = None
        for value in listed[::-1]:
            linked = ListNode(value, linked)
        return linked

    def generic(self, linked):
        original = TestCode.linked_to_listed(linked)
        result = TestCode.linked_to_listed(Solution().reverseList(linked))
        self.assertEqual(len(original), len(result))
        for i, _ in enumerate(original):
            self.assertEqual(original[len(original) - 1 - i], result[i])

    def test_15(self):
        linked = TestCode.listed_to_linked(list(range(15)))
        self.generic(linked)

    def test_1(self):
        linked = TestCode.listed_to_linked(list(range(1)))
        self.generic(linked)

    def test_nothing(self):
        self.assertIsNone(Solution().reverseList(None))
