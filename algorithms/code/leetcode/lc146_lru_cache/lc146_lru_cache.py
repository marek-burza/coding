#!/usr/bin/env python3
# https://leetcode.com/problems/lru-cache/

import unittest
from typing import Dict


class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.preceding = None
        self.following = None


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.lut: Dict[int, Node] = {}
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.following = self.tail
        self.tail.preceding = self.head

    def get(self, key: int) -> int:
        value = -1
        if key in self.lut:
            value = self.remove(key)
            self.insert(key, value)
        return value

    def put(self, key: int, value: int) -> None:
        if key in self.lut:
            self.remove(key)
        else:
            if len(self.lut) >= self.capacity:
                assert self.tail.preceding is not None
                self.remove(self.tail.preceding.key)
        self.insert(key, value)

    def insert(self, key: int, value: int) -> None:
        node = Node(key, value)
        node.preceding = self.head
        node.following = self.head.following
        assert node.following is not None
        node.preceding.following = node
        node.following.preceding = node
        self.lut[key] = node

    def remove(self, key: int) -> int:
        node = self.lut[key]
        del self.lut[key]
        assert node.preceding is not None
        assert node.following is not None
        node.preceding.following = node.following
        node.following.preceding = node.preceding
        return node.value


class TestCode(unittest.TestCase):
    def test_example(self):
        lru = LRUCache(2)
        lru.put(1, 1)
        lru.put(2, 2)
        assert lru.get(1) == 1
        lru.put(3, 3)
        assert lru.get(2) == -1
        lru.put(4, 4)
        assert lru.get(1) == -1
        assert lru.get(3) == 3
        assert lru.get(4) == 4

    def test_repeated_put_same(self):
        lru = LRUCache(1)
        lru.put(1, 1)
        lru.put(1, 1)
        assert lru.get(1) == 1
