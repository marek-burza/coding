#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/primsmstsub

import functools
import heapq
import os
import unittest
from typing import List, Set


# pylint: disable=R0903
@functools.total_ordering
class Edge:
    def __init__(self, origin, vertex, weight):
        self.origin = origin
        self.vertex = vertex
        self.weight = weight

    def __lt__(self, other):
        return self.weight < other.weight


def prims(n: int, edges: List[List[int]], start: int) -> int:
    adjacency: List[List[Edge]] = [[] for _ in range(n + 1)]
    for edge in edges:
        adjacency[edge[0]].append(Edge(edge[0], edge[1], edge[2]))
        adjacency[edge[1]].append(Edge(edge[1], edge[0], edge[2]))
    connected: Set[int] = set()
    queue: List[Edge] = []
    total = 0
    while len(connected) < n:
        vertex = start
        while queue:
            edge_obj = heapq.heappop(queue)
            if edge_obj.vertex not in connected:
                vertex = edge_obj.vertex
                total += edge_obj.weight
                break
        # if vertex == start and start in connected:
        #     break
        connected.add(vertex)
        for edge_obj in adjacency[vertex]:
            heapq.heappush(queue, edge_obj)
    return total


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        n = int(io_lines[0][0][0])
        m = int(io_lines[0][0][1])
        edges = [[int(item) for item in row] for row in io_lines[0][1 : 1 + m]]
        start = int(io_lines[0][1 + m][0])
        result = prims(n, edges, start)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')

    def test_05(self):
        self.runner('05')

    def test_06(self):
        self.runner('06')
