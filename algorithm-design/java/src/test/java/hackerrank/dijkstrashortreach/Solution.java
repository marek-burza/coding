package hackerrank.dijkstrashortreach;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 */
public class Solution {
    static InputStream inOverride = null;
    static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inOverride));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                String nm = reader.readLine();
                int space = nm.indexOf(' ');
                int n = Integer.parseInt(nm.substring(0, space));
                Graph graph = new Graph(n);
                int m = Integer.parseInt(nm.substring(space + 1));
                for (int j = 0; j < m; j++) {
                    String edge = reader.readLine();
                    int firstSpace = edge.indexOf(' ');
                    int secondSpace = edge.indexOf(' ', firstSpace + 1);
                    int x = Integer.parseInt(edge.substring(0, firstSpace)) - 1;
                    int y = Integer.parseInt(edge.substring(firstSpace + 1, secondSpace)) - 1;
                    int r = Integer.parseInt(edge.substring(secondSpace + 1));
                    graph.add(x, y, r);
                }
                int s = Integer.parseInt(reader.readLine()) - 1;
                Vertex[] result = dijkstra(graph, s);
                boolean first = true;
                for (int j = 0; j < n; j++) {
                    if (j == s) continue;
                    if (first) {
                        first = false;
                    } else {
                        outOverride.print(" ");
                    }
                    long value = result[j].distance;
                    if (value == Long.MAX_VALUE) {
                        outOverride.print(-1);
                    } else {
                        outOverride.print(value);
                    }
                }
                outOverride.println();
            }
        } catch (IOException ignored) {
        }
    }

    private static class Graph {
        final Vertex[] vertices;
        final int[][] lut;

        Graph(int n) {
            lut = new int[n][n];
            vertices = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vertices[i] = new Vertex();
                Arrays.fill(lut[i], -1);
            }
        }

        void add(int x, int y, int r) {
            if (x == y || r <= 0) return;
            single(x, y, r);
            single(y, x, r);
        }

        private void single(int a, int b, int r) {
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

    static class Vertex implements Comparable<Vertex> {
        long distance = Long.MAX_VALUE;
        List<Edge> edges = new ArrayList<>();

        @Override
        public int compareTo(Vertex vertex) {
            return (int) (distance - vertex.distance);
        }
    }

    private static class Edge {
        Vertex vertex;
        int weight;

        Edge(Vertex vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static Vertex[] dijkstra(Graph graph, int s) {
        Vertex[] vertices = graph.vertices;
        PriorityQueue<Vertex> unvisited = new PriorityQueue<>();
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
