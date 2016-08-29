package hackerrank.dijkstrashortreach;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Scanner scanner = new Scanner(inOverride);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            Graph graph = new Graph(n);
            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                int r = scanner.nextInt();
                graph.add(x, y, r);
            }
            int s = scanner.nextInt() - 1;
            int[] result = dijkstra(graph, n, s);
            String string = IntStream.range(0, result.length).filter(k -> k != s).map(l -> result[l]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            outOverride.println(string);
        }
    }

    private static class Graph {
        private static class Edge {
            int node;
            int weight;

            Edge(int node, int weight) {
                this.node = node;
                this.weight = weight;
            }
        }

        Map<Integer, Edge>[] adjacencies;

        Graph(int n) {
            adjacencies = new Map[n];
            for (int i = 0; i < n; i++) {
                adjacencies[i] = new TreeMap<>();
            }
        }

        Edge find(List<Edge> adjacent, int node) {
            for (Edge edge : adjacent) {
                if (edge.node == node) {
                    return edge;
                }
            }
            return null;
        }

        void add(int x, int y, int r) {
            if (x == y || r <= 0) return;
            Map<Integer, Edge> adjacent;
            Edge edge;
            adjacent = adjacencies[x];
            edge = adjacent.get(y);
            if (null == edge) {
                edge = new Edge(y, r);
                adjacent.put(y, edge);
            } else {
                edge.weight = Math.min(edge.weight, r);
            }
            adjacent = adjacencies[y];
            edge = adjacent.get(x);
            if (null == edge) {
                edge = new Edge(x, r);
                adjacent.put(x, edge);
            } else {
                edge.weight = Math.min(edge.weight, r);
            }
        }

        Edge[][] cached() {
            int n = adjacencies.length;
            Edge[][] result = new Edge[n][0];
            for (int i = 0; i < n; i++) {
                Map<Integer, Edge> adjacency = adjacencies[i];
                Set<Integer> adjacent = adjacency.keySet();
                int m = adjacent.size();
                result[i] = new Edge[m];
                int j = 0;
                for (int k : adjacent) {
                    result[i][j++] = adjacency.get(k);
                }
            }
            return result;
        }
    }

    private static class DistanceComparator implements Comparator<Integer> {
        private int[] distances;

        public DistanceComparator(int[] distances) {
            this.distances = distances;
        }

        @Override
        public int compare(Integer node1, Integer node2) {
            if (distances[node1] < distances[node2]) return -1;
            return 1;
        }
    }

    private static int minimum(Set<Integer> set) {
        int result = Integer.MAX_VALUE;
        for (int value : set) {
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    private static int[] dijkstraUgh(Graph graph, int n, int s) {
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        Set<Integer> unvisited = new HashSet<>();
        unvisited.add(s);
        distances[s] = 0;
        while (unvisited.size() > 0) {
            int node = minimum(unvisited);
            unvisited.remove(node);
            Map<Integer, Graph.Edge> adjacency = graph.adjacencies[node];
            for (int next : adjacency.keySet()) {
                int candidate = distances[node] + adjacency.get(next).weight;
                if (-1 == distances[next] || candidate < distances[next]) {
                    distances[next] = candidate;
                    unvisited.add(next);
                }
            }
        }
        return distances;
    }

    private static int[] dijkstra(Graph graph, int n, int s) {
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        SortedMap<Integer, Set<Integer>> unvisited = new TreeMap<>();
        Set<Integer> entry = new TreeSet<>();
        entry.add(s);
        unvisited.put(0, entry);
        distances[s] = 0;
        while (unvisited.size() > 0) {
            int minimum = unvisited.firstKey();
            entry = unvisited.get(minimum);
            int node = entry.iterator().next();
            entry.remove(node);
            if (entry.size() == 0) {
                unvisited.remove(minimum);
            }
            Map<Integer, Graph.Edge> adjacency = graph.adjacencies[node];
            for (int next : adjacency.keySet()) {
                int candidate = distances[node] + adjacency.get(next).weight;
                int old = distances[next];
                if (-1 == old || candidate < old) {
                    if (unvisited.containsKey(old)) {
                        entry = unvisited.get(old);
                        entry.remove(next);
                        if (entry.size() == 0) {
                            unvisited.remove(old);
                        }
                    }
                    distances[next] = candidate;
                    entry = unvisited.get(candidate);
                    if (null == entry) {
                        entry = new TreeSet<>();
                        unvisited.put(candidate, entry);
                    }
                    entry.add(next);
                }
            }
        }
        return distances;
    }
}
