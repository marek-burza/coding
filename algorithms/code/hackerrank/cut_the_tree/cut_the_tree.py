#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/cut-the-tree

import os
import unittest
from typing import Dict, List, Set

INDEX_INDEX = 0
INDEX_VALUE = 1
INDEX_ADJACENT = 2


def build_graph(data: List[int], edges: List[List[int]]) -> Dict[int, tuple]:
    mapped: Dict[int, tuple] = {}
    for i, _ in enumerate(data):
        index = i + 1
        mapped[index] = (index, data[i], [])
    for edge in edges:
        mapped[edge[0]][INDEX_ADJACENT].append(mapped[edge[1]])
        mapped[edge[1]][INDEX_ADJACENT].append(mapped[edge[0]])
    return mapped


# pylint: disable=C0301
def maximum_edge(
    graph: Dict[int, tuple],
    v: int,
    total: int,
    minimum: List[int],
    seen: Set[int],
) -> int:
    if v in seen:
        return 0
    summed = graph[v][INDEX_VALUE]
    seen.add(v)
    for other in graph[v][INDEX_ADJACENT]:
        partial = maximum_edge(graph, other[INDEX_INDEX], total, minimum, seen)
        candidate = abs(total - 2 * partial)
        if not minimum or candidate < minimum[0]:
            if not minimum:
                minimum.append(candidate)
            else:
                minimum[0] = candidate
        summed += partial
    return summed


def cut_the_tree(data: List[int], edges: List[List[int]]) -> int:
    graph = build_graph(data, edges)
    total = sum(data)
    minimum: List[int] = []
    seen: Set[int] = set()
    maximum_edge(graph, 1, total, minimum, seen)
    return minimum[0]


class TestCode(unittest.TestCase):
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        data = [int(item) for item in io_lines[0][1]]
        edges = []
        i = 2
        while i < len(io_lines[0]) and len(io_lines[0][i]) == 2:
            edges.append([int(item) for item in io_lines[0][i]])
            i += 1
        result = cut_the_tree(data, edges)
        expected = int(io_lines[1][0][0])
        self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
