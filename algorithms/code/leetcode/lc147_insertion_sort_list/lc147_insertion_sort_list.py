#!/usr/bin/env python3
# https://leetcode.com/problems/insertion-sort-list/

import unittest
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
    def insertionSortList(
        self, head: Optional[ListNode]
    ) -> Optional[ListNode]:
        if head is None:
            return None
        node = head
        # Grab the first node after the already ordered nodes
        tail = node
        # Iterate from the node holding the head
        handle = ListNode(0)
        # Iterate until we reach the end
        while (node := tail) is not None:
            # Remove (extract) that node from the list
            tail = node.next
            node.next = None
            # Grab the first ordered node
            current = handle
            # Iterate until we reach a node greater or equal
            # to the extracted one
            while current.next is not None and current.next.val < node.val:
                # Move on to the next ordered node
                current = current.next
            # Insert the node
            node.next = current.next
            current.next = node
        return handle.next


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

    def test_example(self):
        linked = TestCode.listed_to_linked([6, 3, 4, 5, 2, 1])
        result = Solution().insertionSortList(linked)
        self.assertListEqual(
            [1, 2, 3, 4, 5, 6], TestCode.linked_to_listed(result)
        )

    def test_1_1(self):
        linked = TestCode.listed_to_linked([1, 1])
        result = Solution().insertionSortList(linked)
        self.assertListEqual([1, 1], TestCode.linked_to_listed(result))

    def test_nothing(self):
        linked = None
        result = Solution().insertionSortList(linked)
        self.assertIsNone(result)
