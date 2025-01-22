#!/usr/bin/env python3
# https://leetcode.com/problems/remove-duplicates-from-sorted-list/

import unittest
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, following=None):
        self.val = val
        self.next = following


class Solution:
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

    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        listed = Solution.linked_to_listed(head)
        deduplicated: List = []
        for value in listed:
            if not deduplicated or deduplicated[-1] != value:
                deduplicated.append(value)
        return Solution.listed_to_linked(deduplicated)


class TestCode(unittest.TestCase):
    def test_1_1_2(self):
        linked = Solution.listed_to_linked([1, 1, 2])
        result = Solution().deleteDuplicates(linked)
        self.assertListEqual([1, 2], Solution.linked_to_listed(result))

    def test_1_1_2_3_3(self):
        linked = Solution.listed_to_linked([1, 1, 2, 3, 3])
        result = Solution().deleteDuplicates(linked)
        self.assertListEqual([1, 2, 3], Solution.linked_to_listed(result))
