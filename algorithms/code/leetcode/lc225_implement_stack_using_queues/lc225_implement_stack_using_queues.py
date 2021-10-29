#!/usr/bin/env python3
# https://leetcode.com/problems/implement-stack-using-queues/

import collections
import unittest


class MyStack:
    def __init__(self):
        self.__active = collections.deque()
        self.__other = collections.deque()

    def __swap(self):
        swap = self.__other
        self.__other = self.__active
        self.__active = swap

    # Push element x onto stack.
    def push(self, x: int) -> None:
        # 1 - active
        self.__other.append(x)
        while self.__active:
            self.__other.append(self.__active.popleft())
        self.__swap()

    # Removes the element on top of the stack.
    def pop(self) -> int:
        self.__active.popleft()

    # Get the top element.
    def top(self) -> int:
        return self.__active[0]

    # Return whether the stack is empty.
    def empty(self) -> bool:
        return not bool(self.__active)

# It's also possible with just one stack


class TestCode(unittest.TestCase):
    def test_example(self):
        solution = MyStack()
        solution.push(5)
        solution.push(2)
        self.assertEqual(2, solution.top())
        solution.pop()
        self.assertEqual(5, solution.top())
        self.assertFalse(solution.empty())
        solution.pop()
        self.assertTrue(solution.empty())
