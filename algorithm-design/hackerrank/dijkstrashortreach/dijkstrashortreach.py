#!/usr/bin/env python3
# https://www.hackerrank.com/challenges/dijkstrashortreach

import collections
import functools
import heapq
import os
import unittest

from typing import List


Edge = collections.namedtuple('Edge', ['origin', 'vertex', 'weight'])


# pylint: disable=R0903
@functools.total_ordering
class Vertex:
    def __init__(self, distance, edges):
        self.distance = distance
        self.edges = edges

    def __lt__(self, other):
        return self.distance < other.distance

    def assign_distance(self, distance):
        self.distance = distance


def replace(value, from_value, to_value):
    return to_value if value == from_value else value


def shortest_reach(n: int, edges: List[List[int]], s: int) -> int:
    maximum = float('inf')
    adjacency = [None] * (n + 1)
    for edge in edges:
        if adjacency[edge[0]] is None:
            adjacency[edge[0]] = []
        if adjacency[edge[1]] is None:
            adjacency[edge[1]] = []
        adjacency[edge[0]].append(Edge(edge[0], edge[1], edge[2]))
        adjacency[edge[1]].append(Edge(edge[1], edge[0], edge[2]))
    vertices = [None] * (n + 1)
    for i, adjacent in enumerate(adjacency):
        adjacent = [] if adjacent is None else adjacent
        vertices[i] = Vertex(maximum, adjacent)
    unvisited = []
    heapq.heappush(unvisited, vertices[s])
    vertices[s].assign_distance(0)
    while unvisited:
        vertex = heapq.heappop(unvisited)
        for edge in vertex.edges:
            other = vertices[edge.vertex]
            candidate = vertex.distance + edge.weight
            if candidate < other.distance:
                other.assign_distance(candidate)
                heapq.heappush(unvisited, other)
    del vertices[s]
    distances = [vertex.distance for vertex in vertices[1:]]
    distances = [replace(distance, maximum, -1) for distance in distances]
    return distances


class TestCode(unittest.TestCase):
    # pylint: disable=R0914
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
        tests = int(io_lines[0][0][0])
        offset = 1
        for test in range(tests):
            n = int(io_lines[0][offset][0])
            m = int(io_lines[0][offset][1])
            edges = io_lines[0][offset + 1:offset + 1 + m]
            edges = [[int(item) for item in row] for row in edges]
            s = int(io_lines[0][offset + 1 + m][0])
            offset += 1 + m + 1
            result = shortest_reach(n, edges, s)
            expected = [int(item) for item in io_lines[1][test]]
            self.assertEqual(expected, result)

    def test_00(self):
        self.runner('00')

    def test_01(self):
        self.runner('01')

    def test_03(self):
        self.runner('03')

    def test_04(self):
        self.runner('04')
