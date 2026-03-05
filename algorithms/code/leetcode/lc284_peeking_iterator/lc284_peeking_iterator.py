# https://leetcode.com/problems/peeking-iterator/

import unittest
from typing import Any


class PeekingIterator:
    def __init__(self, iterator: Any) -> None:
        self.__iterator = iterator
        self.__got = False
        self.__value = None

    def peek(self) -> Any:
        if not self.__got:
            self.__got = True
            self.__value = self.__iterator.next()
        return self.__value

    def next(self) -> Any:
        if self.__got:
            self.__got = False
        else:
            self.__value = self.__iterator.next()
        return self.__value

    def hasNext(self) -> bool:
        return self.__got or self.__iterator.hasNext()


class TestCode(unittest.TestCase):
    @staticmethod
    def __create_iterator(items: list[Any]) -> Any:
        class TestingIterator:
            def __init__(self, items: list[Any]) -> None:
                self.__items = items

            def hasNext(self) -> bool:
                return len(self.__items) != 0

            def next(self) -> Any:
                return self.__items.pop(0)

        return TestingIterator(items)

    def test_example(self) -> None:
        values = [1, 2, 3]
        peeking = PeekingIterator(self.__create_iterator(values))
        assert int(peeking.next()) == 1
        assert int(peeking.peek()) == 2
        assert int(peeking.next()) == 2
        assert int(peeking.next()) == 3
        assert not peeking.hasNext()

    def test_other(self) -> None:
        values = [1, 2, 3, 4]
        peeking = PeekingIterator(self.__create_iterator(values))
        assert peeking.hasNext()
        assert int(peeking.peek()) == 1
        assert int(peeking.peek()) == 1
        assert int(peeking.next()) == 1
        assert int(peeking.next()) == 2
        assert int(peeking.peek()) == 3
        assert int(peeking.peek()) == 3
        assert int(peeking.next()) == 3
        assert peeking.hasNext()
        assert int(peeking.peek()) == 4
        assert peeking.hasNext()
        assert int(peeking.next()) == 4
        assert not peeking.hasNext()
