#!/usr/bin/env python3
# https://leetcode.com/problems/peeking-iterator/

import unittest


class PeekingIterator:
    def __init__(self, iterator):
        self.__iterator = iterator
        self.__got = False
        self.__value = None

    def peek(self):
        if not self.__got:
            self.__got = True
            self.__value = self.__iterator.next()
        return self.__value

    def next(self):
        if self.__got:
            self.__got = False
        else:
            self.__value = self.__iterator.next()
        return self.__value

    def hasNext(self):
        return self.__got or self.__iterator.hasNext()


class TestCode(unittest.TestCase):
    @staticmethod
    def __create_iterator(items):
        class TestingIterator:
            def __init__(self, items):
                self.__items = items

            def hasNext(self):
                return len(self.__items) != 0

            def next(self):
                return self.__items.pop(0)
        return TestingIterator(items)

    def test_example(self):
        values = [1, 2, 3]
        peeking = PeekingIterator(self.__create_iterator(values))
        self.assertEqual(1, int(peeking.next()))
        self.assertEqual(2, int(peeking.peek()))
        self.assertEqual(2, int(peeking.next()))
        self.assertEqual(3, int(peeking.next()))
        self.assertFalse(peeking.hasNext())

    def test_other(self):
        values = [1, 2, 3, 4]
        peeking = PeekingIterator(self.__create_iterator(values))
        self.assertTrue(peeking.hasNext())
        self.assertEqual(1, int(peeking.peek()))
        self.assertEqual(1, int(peeking.peek()))
        self.assertEqual(1, int(peeking.next()))
        self.assertEqual(2, int(peeking.next()))
        self.assertEqual(3, int(peeking.peek()))
        self.assertEqual(3, int(peeking.peek()))
        self.assertEqual(3, int(peeking.next()))
        self.assertTrue(peeking.hasNext())
        self.assertEqual(4, int(peeking.peek()))
        self.assertTrue(peeking.hasNext())
        self.assertEqual(4, int(peeking.next()))
        self.assertFalse(peeking.hasNext())
