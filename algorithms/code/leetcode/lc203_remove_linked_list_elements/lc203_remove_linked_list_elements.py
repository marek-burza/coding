#!/usr/bin/env python3
# https://leetcode.com/problems/remove-linked-list-elements/

import unittest
from typing import Optional


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def removeElements(
        self, head: Optional[ListNode], val: int
    ) -> Optional[ListNode]:
        if head is None:
            return None
        previous = None
        node = head
        while node is not None:
            if node.val == val:
                if previous is None:
                    head = node.next
                else:
                    previous.next = node.next
            else:
                previous = node
            node = node.next
        return head


class TestCode(unittest.TestCase):
    def convert(self, array):
        listed = None
        last = None
        for value in array:
            node = ListNode(value)
            node.next = None
            if listed is None:
                listed = node
            else:
                last.next = node
            last = node
        return listed

    def test_6_1_2_3_4_5_6_7_6__6(self):
        array = [6, 1, 2, 3, 4, 5, 6, 7, 6]
        listed = self.convert(array)
        listed = Solution().removeElements(listed, 6)
        expected = [1, 2, 3, 4, 5, 7]
        for value in expected:
            assert listed is not None
            assert value == listed.val
            listed = listed.next

    def test_nothing(self):
        assert Solution().removeElements(None, 0) is None
