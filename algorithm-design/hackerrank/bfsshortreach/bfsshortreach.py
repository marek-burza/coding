#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/bfsshortreach

import collections
import os
import unittest

from typing import List


# pylint: disable=W0613
def bfs(n: int, m: int, edges: List[List[int]], s: int) -> List[int]:
    adjacency = [[False] * n for _ in range(n)]
    for edge in edges:
        adjacency[edge[0] - 1][edge[1] - 1] = True
        adjacency[edge[1] - 1][edge[0] - 1] = True
    queue = collections.deque()
    queue.append((s - 1, 0))
    distances = [-1] * n
    while queue:
        vertex, distance = queue.pop()
        if distances[vertex] == -1:
            distances[vertex] = distance
            for i in range(n):
                if adjacency[vertex][i]:
                    queue.append((i, distance + 6))
    del distances[s - 1]
    return distances


class TestCode(unittest.TestCase):
    # pylint: disable=R0914
    def runner(self, name):
        io_lines = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        tests = int(io_lines[0][0][0])
        offset = 1
        for test in range(tests):
            n = int(io_lines[0][offset][0])
            m = int(io_lines[0][offset][1])
            edges = io_lines[0][offset + 1:offset + 1 + m]
            edges = [[int(item) for item in row] for row in edges]
            s = int(io_lines[0][offset + 1 + m][0])
            offset += 1 + m + 1
            result = bfs(n, m, edges, s)
            expected = [int(item) for item in io_lines[1][test]]
            self.assertEqual(expected, result)

    def test_example(self):
        self.runner('_example')
