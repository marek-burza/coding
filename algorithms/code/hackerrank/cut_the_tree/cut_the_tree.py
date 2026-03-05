# https://www.hackerrank.com/challenges/cut-the-tree

import os
import unittest
from dataclasses import dataclass


@dataclass
class Vertex:
    index: int
    value: int
    adjacent: list


def build_graph(data: list[int], edges: list[list[int]]) -> dict[int, Vertex]:
    mapped: dict[int, Vertex] = {}
    for i, _ in enumerate(data):
        index = i + 1
        mapped[index] = Vertex(index, data[i], [])
    for edge in edges:
        mapped[edge[0]].adjacent.append(mapped[edge[1]])
        mapped[edge[1]].adjacent.append(mapped[edge[0]])
    return mapped


def maximum_edge(
    graph: dict[int, Vertex],
    v: int,
    total: int,
    minimum: list[int],
    seen: set[int],
) -> int:
    if v in seen:
        return 0
    summed = graph[v].value
    seen.add(v)
    for other in graph[v].adjacent:
        partial = maximum_edge(graph, other.index, total, minimum, seen)
        candidate = abs(total - 2 * partial)
        if not minimum or candidate < minimum[0]:
            if not minimum:
                minimum.append(candidate)
            else:
                minimum[0] = candidate
        summed += partial
    return summed


def cut_the_tree(data: list[int], edges: list[list[int]]) -> int:
    graph = build_graph(data, edges)
    total = sum(data)
    minimum: list[int] = []
    seen: set[int] = set()
    maximum_edge(graph, 1, total, minimum, seen)
    return minimum[0]


class TestCode(unittest.TestCase):
    def runner(self, name: str) -> None:
        io_lines: list[list[list[str]]] = [[[]]] * 2
        for index, template in enumerate(['input%s.txt', 'output%s.txt']):
            path = os.path.join(os.path.split(__file__)[0], template % name)
            with open(path, 'r', encoding='utf-8') as handle:
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
        assert expected == result

    def test_example(self) -> None:
        self.runner('_example')
