#!/usr/bin/env python3
# https://leetcode.com/problems/flatten-nested-list-iterator/

import unittest

from typing import List


class NestedInteger:
    def __init__(self, item):
        self.__object = item

    def isInteger(self):
        return isinstance(self.__object, int)

    def getInteger(self):
        return self.__object

    def getList(self):
        return self.__object


class NestedIterator:
    class Item:
        def __init__(self, item, skip):
            self.item = NestedInteger(item)
            self.skip = skip

    def __init__(self, nestedList: List[NestedInteger]):
        self.__stack = []
        self.__stack.append(self.Item(nestedList, 0))

    def __objectify(self, nested, skip):
        if nested.isInteger():
            return self.Item(nested.getInteger(), skip)
        return self.Item(nested.getList(), skip)

    def __find(self):
        if not self.__stack:
            return
        while len(self.__stack) != 0 and not self.__stack[-1].item.isInteger():
            top = self.__stack[-1]
            listed = top.item.getList()
            if len(listed) <= top.skip:
                self.__stack.pop()
                continue
            self.__stack.append(self.__objectify(listed[top.skip], 0))
            top.skip += 1

    def next(self) -> int:
        if self.hasNext():
            return self.__stack.pop().item.getInteger()
        return None

    def hasNext(self) -> bool:
        self.__find()
        return len(self.__stack) != 0


class TestCode(unittest.TestCase):
    def generic(self, used, expected):
        nested = NestedIterator(used)
        for value in expected:
            self.assertTrue(nested.hasNext())
            self.assertEqual(value, nested.next())
        self.assertFalse(nested.hasNext())
        self.assertEqual(None, nested.next())

    def test_example_1(self):
        list_1_1_a = []
        list_1_1_a.append(NestedInteger(1))
        list_1_1_a.append(NestedInteger(1))
        list_1_1_b = []
        list_1_1_b.append(NestedInteger(1))
        list_1_1_b.append(NestedInteger(1))
        list_top = []
        list_top.append(NestedInteger(list_1_1_a))
        list_top.append(NestedInteger(2))
        list_top.append(NestedInteger(list_1_1_b))
        expected = [1, 1, 2, 1, 1]
        self.generic(list_top, expected)

    def test_example_2(self):
        list_top = []
        list_top.append(NestedInteger(1))
        list_4 = []
        list_4.append(NestedInteger(4))
        list_6 = []
        list_6.append(NestedInteger(6))
        list_4.append(NestedInteger(list_6))
        list_top.append(NestedInteger(list_4))
        expected = [1, 4, 6]
        self.generic(list_top, expected)
