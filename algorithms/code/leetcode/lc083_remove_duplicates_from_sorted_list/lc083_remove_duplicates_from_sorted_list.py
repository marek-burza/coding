# https://leetcode.com/problems/remove-duplicates-from-sorted-list/

from __future__ import annotations

import unittest


class ListNode:
    def __init__(
        self, val: int = 0, following: 'ListNode | None' = None
    ) -> None:
        self.val = val
        self.next = following


class Solution:
    @staticmethod
    def linked_to_listed(linked: ListNode | None) -> list:
        listed: list = []
        while linked is not None:
            listed.append(linked.val)
            linked = linked.next
        return listed

    @staticmethod
    def listed_to_linked(listed: list) -> ListNode | None:
        linked: ListNode | None = None
        for value in listed[::-1]:
            linked = ListNode(value, linked)
        return linked

    def deleteDuplicates(self, head: ListNode | None) -> ListNode | None:
        listed = Solution.linked_to_listed(head)
        deduplicated: list = []
        for value in listed:
            if not deduplicated or deduplicated[-1] != value:
                deduplicated.append(value)
        return Solution.listed_to_linked(deduplicated)


class TestCode(unittest.TestCase):
    def test_1_1_2(self) -> None:
        linked = Solution.listed_to_linked([1, 1, 2])
        result = Solution().deleteDuplicates(linked)
        self.assertListEqual([1, 2], Solution.linked_to_listed(result))

    def test_1_1_2_3_3(self) -> None:
        linked = Solution.listed_to_linked([1, 1, 2, 3, 3])
        result = Solution().deleteDuplicates(linked)
        self.assertListEqual([1, 2, 3], Solution.linked_to_listed(result))
