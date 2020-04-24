#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/primsmstsub

import collections
import math
import os
import unittest

from typing import List


class Heap:
    def __init__(self, comparator):
        self.size = 0
        self.heap = []
        self.compare = comparator

    @staticmethod
    def child(index):
        return (index << 1) + 1

    @staticmethod
    def parent(index):
        return (index - 1) >> 1

    def downwards(self, index):  # heapify down, percolate down
        swap = self.heap[index]
        while Heap.child(index) < self.size:
            child = Heap.child(index)
            if child + 1 < self.size:
                if self.compare(self.heap[child], self.heap[child + 1]) > 0:
                    child += 1
            if self.compare(swap, self.heap[child]) > 0:
                self.heap[index] = self.heap[child]
            else:
                break
            index = child
        self.heap[index] = swap

    def upwards(self, index):  # heapify up, percolate up
        while index > 0 > self.compare(self.heap[index], self.heap[Heap.parent(index)]):  # noqa
            parent = Heap.parent(index)
            swap = self.heap[parent]
            self.heap[parent] = self.heap[index]
            self.heap[index] = swap
            index = parent

    def insert(self, value):
        self.protect()
        index = self.size
        self.heap[index] = value
        self.size += 1
        self.upwards(index)

    def search(self, value):
        for index in range(self.size):
            if self.compare(self.heap[index], value) == 0:
                return index
        return None

    def pop(self):
        top = self.heap[0]
        self.delete(0)
        return top

    def delete(self, index):
        self.size -= 1
        self.heap[index] = self.heap[self.size]
        parent = Heap.parent(index)
        if index == 0 or self.compare(self.heap[parent], self.heap[index]) < 0:  # noqa
            self.downwards(index)
        else:
            self.upwards(index)

    def protect(self):
        capacity = len(self.heap)
        if capacity == self.size:
            capacity = 1 if capacity == 0 else capacity
            self.heap.extend([0] * capacity)

    def empty(self):
        return self.size == 0

    @staticmethod
    def level(index):
        return int(math.floor(math.log(index + 1, 2)))


Edge = collections.namedtuple('Edge', ['origin', 'vertex', 'weight'])


def compare_edges(this, other):
    return this.weight - other.weight


def prims(n: int, edges: List[List[int]], start: int) -> int:
    adjacency = {}
    for edge in edges:
        if edge[0] not in adjacency:
            adjacency[edge[0]] = []
        if edge[1] not in adjacency:
            adjacency[edge[1]] = []
        adjacency[edge[0]].append(Edge(edge[0], edge[1], edge[2]))
        adjacency[edge[1]].append(Edge(edge[1], edge[0], edge[2]))
    connected = set()
    queue = Heap(compare_edges)
    total = 0
    while len(connected) < n:
        vertex = start
        while not queue.empty():
            edge = queue.pop()
            if edge.vertex not in connected:
                vertex = edge.vertex
                total += edge.weight
                break
        # if vertex == start and start in connected:
        #     break
        connected.add(vertex)
        for edge in adjacency[vertex]:
            queue.insert(edge)
    return total


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = template % name
            path = os.path.join(os.path.split(__file__)[0], path)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            lines = [line.strip() for line in lines]
            lines = [line.split(' ') for line in lines]
            io_lines[index] = lines
        n = int(io_lines[0][0][0])
        m = int(io_lines[0][0][1])
        edges = [[int(item) for item in row] for row in io_lines[0][1:1 + m]]
        start = int(io_lines[0][1 + m][0])
        result = prims(n, edges, start)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_uncovered(self):
        Heap.level(0)
        array = [41, 98, 52, 8, 9, 3, 62, 96, 77, 23]
        heap = Heap(lambda this, other: this - other)
        for value in array:
            heap.insert(value)
        heap.delete(heap.search(98))
        simple = Heap(lambda this, other: this - other)
        for value in range(3):
            simple.insert(value)
        simple.delete(simple.search(0))
        self.assertEqual(None, simple.search(0))

    def test_example(self):
        self.runner('_example')

    def test_05(self):
        self.runner('05')

    def test_06(self):
        self.runner('06')

TestCode().test_uncovered()