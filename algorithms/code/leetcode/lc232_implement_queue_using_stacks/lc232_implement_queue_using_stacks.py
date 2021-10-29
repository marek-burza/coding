#!/usr/bin/env python3
# https://leetcode.com/problems/implement-queue-using-stacks/

import unittest


class MyQueue:
    def __init__(self):
        self.__stack = []
        self.__buffer = []

    # Push element x to the back of queue.
    def push(self, x: int) -> None:
        while self.__stack:
            self.__buffer.append(self.__stack.pop())
        self.__stack.append(x)
        while self.__buffer:
            self.__stack.append(self.__buffer.pop())

    # Removes the element from in front of queue.
    def pop(self) -> int:
        self.__stack.pop()

    # Get the front element.
    def peek(self) -> int:
        return self.__stack[-1]

    # Return whether the queue is empty.
    def empty(self) -> bool:
        return not bool(self.__stack)


class TestCode(unittest.TestCase):
    def test_something(self):
        queue = MyQueue()
        for i in range(0, 6):
            queue.push(i)
        for i in range(0, 6):
            self.assertFalse(queue.empty())
            self.assertEqual(i, queue.peek())
            queue.pop()
        self.assertTrue(queue.empty())
