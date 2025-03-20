#!/usr/bin/env python3
# https://leetcode.com/problems/add-two-numbers/

import unittest
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, next_item=None):
        self.val = val
        self.next = next_item


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        handle = ListNode(0)
        handle.next = None
        tail = handle
        carry = 0
        while l1 is not None or l2 is not None or carry != 0:
            if l1 is None:
                value1 = 0
            else:
                value1 = l1.val
                l1 = l1.next
            if l2 is None:
                value2 = 0
            else:
                value2 = l2.val
                l2 = l2.next
            summed = carry + value1 + value2
            tail.next = ListNode(summed % 10)
            tail.next.next = None
            tail = tail.next
            carry = summed // 10
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
        array1 = [2, 4, 3]
        array2 = [5, 6, 4]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 8]
        self.assertEqual(TestCode.linked_to_listed(linked), expected)

    def test_uneven(self):
        array1 = [2, 4]
        array2 = [5, 6, 4]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 5]
        self.assertEqual(TestCode.linked_to_listed(linked), expected)

    def test_carry(self):
        array1 = [2, 4]
        array2 = [5, 6]
        linked = Solution().addTwoNumbers(
            TestCode.listed_to_linked(array1),
            TestCode.listed_to_linked(array2),
        )
        expected = [7, 0, 1]
        self.assertEqual(TestCode.linked_to_listed(linked), expected)
