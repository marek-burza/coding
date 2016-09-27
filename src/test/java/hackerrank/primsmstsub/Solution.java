package hackerrank.primsmstsub;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int n = in.nextInt();
        int m = in.nextInt();
        Map<Integer, List<Edge>> adjacency = new HashMap<>();
        for (int j = 0; j < m; j++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int weight = in.nextInt(); // Warning: The weight can be 0
            List<Edge> adjacent;
            adjacent = adjacency.get(a);
            if (null == adjacent) {
                adjacent = new ArrayList<>();
                adjacency.put(a, adjacent);
            }
            adjacent.add(new Edge(a, b, weight));
            adjacent = adjacency.get(b);
            if (null == adjacent) {
                adjacent = new ArrayList<>();
                adjacency.put(b, adjacent);
            }
            adjacent.add(new Edge(b, a, weight));
        }
        int start = in.nextInt() - 1;
        outOverride.println(calculateWeight(adjacency, n, start));
    }

    private static class Edge implements Comparable<Edge> {
        public final int origin;
        public final int vertex;
        public final int weight;

        public Edge(int origin, int vertex, int weight) {
            this.origin = origin;
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return weight - other.weight;
        }
    }

    private static int calculateWeight(Map<Integer, List<Edge>> adjacency, int n, int start) {
        Set<Integer> connected = new TreeSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int total = 0;
        while (connected.size() < n) {
            int vertex = start;
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                if (!connected.contains(edge.vertex)) {
                    vertex = edge.vertex;
                    total += edge.weight;
                    break;
                }
            }
            if (vertex == start && connected.contains(start)) {
                break;
            }
            connected.add(vertex);
            queue.addAll(adjacency.get(vertex).stream().collect(Collectors.toList()));
        }
        return total;
    }
}
