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


def shortest_reach(n: int, edges: List[List[int]], s: int) -> List[int]:
    maximum = float('inf')
    adjacency: List[List[Edge]] = [[] for _ in range(n + 1)]
    for edge in edges:
        adjacency[edge[0]].append(Edge(edge[0], edge[1], edge[2]))
        adjacency[edge[1]].append(Edge(edge[1], edge[0], edge[2]))
    vertices: List[Vertex] = []
    for adjacent in adjacency:
        vertices.append(Vertex(maximum, adjacent))
    unvisited: List[Vertex] = []
    heapq.heappush(unvisited, vertices[s])
    vertices[s].assign_distance(0)
    while unvisited:
        vertex = heapq.heappop(unvisited)
        for edge_obj in vertex.edges:
            other = vertices[edge_obj.vertex]
            candidate = vertex.distance + edge_obj.weight
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
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
                lines = handle.readlines()
            io_lines[index] = [line.strip().split(' ') for line in lines]
        tests = int(io_lines[0][0][0])
        offset = 1
        for test in range(tests):
            n = int(io_lines[0][offset][0])
            m = int(io_lines[0][offset][1])
            edges = io_lines[0][offset + 1 : offset + 1 + m]
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
