#!/usr/bin/env python3
# https://www.geeksforgeeks.org/number-of-triangles-in-a-undirected-graph/
# #google

import unittest


class Solution:
    def multiply(self, a, b, c, v):
        for i in range(v):
            for j in range(v):
                c[i][j] = 0
                for k in range(v):
                    c[i][j] += a[i][k] * b[k][j]
 
    def get_trace(self, graph, v):
        trace = 0
        for i in range(v):
            trace += graph[i][i]
        return trace
 
    def triangle_in_graph(self, graph, v):
        aux2 = [[0] * v for _ in range(v)]
        aux3 = [[0] * v for _ in range(v)]
 
        self.multiply(graph, graph, aux2, v)
        self.multiply(graph, aux2, aux3, v)
 
        trace = self.get_trace(aux3, v)
        return trace // 6
 

class TestCode(unittest.TestCase):
    def test_example(self):
        graph = [
            [0, 1, 1, 0],
            [1, 0, 1, 1],
            [1, 1, 0, 1],
            [0, 1, 1, 0]]
        self.assertEqual(2, Solution().triangle_in_graph(graph, 4))
