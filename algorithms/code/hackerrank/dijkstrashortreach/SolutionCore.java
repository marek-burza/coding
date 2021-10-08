package hackerrank.dijkstrashortreach;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static final class Graph {
        final Vertex[] vertices;
        final int[][] lut;

        Graph(final int n) {
            lut = new int[n][n];
            vertices = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vertices[i] = new Vertex();
                Arrays.fill(lut[i], -1);
            }
        }

        void add(final int x, final int y, final int r) {
            // if (x == y || r <= 0) {
            // return;
            // }
            single(x, y, r);
            single(y, x, r);
        }

        private void single(final int a, final int b, final int r) {
            Vertex vertex = vertices[a];
            List<Edge> edges = vertex.edges;
            Integer index = lut[a][b];
            if (-1 == index) {
                lut[a][b] = edges.size();
                edges.add(new Edge(vertices[b], r));
            } else {
                Edge edge = edges.get(index);
                edge.weight = Math.min(edge.weight, r);
            }
        }
    }

    protected static final class Vertex {
        long distance = Long.MAX_VALUE;
        List<Edge> edges = new ArrayList<>();
    }

    private static class VertexComparator implements Comparator<Vertex>, Serializable {
        @Override
        public int compare(final Vertex vertex1, final Vertex vertex2) {
            return (int) (vertex1.distance - vertex2.distance);
        }
    }

    private static final class Edge {
        Vertex vertex;
        int weight;

        Edge(final Vertex vertexValue, final int weightValue) {
            vertex = vertexValue;
            weight = weightValue;
        }
    }

    protected static Vertex[] dijkstra(final Graph graph, final int s) {
        Vertex[] vertices = graph.vertices;
        PriorityQueue<Vertex> unvisited = new PriorityQueue<>(new VertexComparator());
        unvisited.add(vertices[s]);
        vertices[s].distance = 0;
        while (unvisited.size() > 0) {
            Vertex vertex = unvisited.poll();
            for (Edge edge : vertex.edges) {
                Vertex other = edge.vertex;
                long candidate = vertex.distance + edge.weight;
                if (candidate < other.distance) {
                    other.distance = candidate;
                    unvisited.add(other);
                }
            }
        }
        return vertices;
    }
}
