#!/usr/bin/env python3
# https://leetcode.com/problems/reverse-linked-list/

import unittest
from typing import Optional


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
    def generate(self, length):
        listed = None
        last = None
        for i in range(length):
            node = ListNode(self, i)
            node.next = None
            if listed is None:
                listed = node
            else:
                last.next = node
            last = node
        return listed

    def freeze(self, listed):
        count = 0
        copy = listed
        while copy is not None:
            count += 1
            copy = copy.next
        frozen = [0 for _ in range(count)]
        for i in range(count):
            frozen[i] = listed.val
            listed = listed.next
        return frozen

    def generic(self, listed):
        original = self.freeze(listed)
        result = self.freeze(Solution().reverseList(listed))
        self.assertEqual(len(original), len(result))
        for i, _ in enumerate(original):
            self.assertEqual(original[len(original) - 1 - i], result[i])

    def test_15(self):
        listed = self.generate(15)
        self.generic(listed)

    def test_1(self):
        listed = self.generate(1)
        self.generic(listed)

    def test_nothing(self):
        self.assertIsNone(Solution().reverseList(None))
