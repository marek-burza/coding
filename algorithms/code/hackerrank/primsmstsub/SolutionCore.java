package hackerrank.primsmstsub;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static final class Edge {
        public final int vertex;
        public final int weight;

        Edge(final int vertexValue, final int weightValue) {
            vertex = vertexValue;
            weight = weightValue;
        }
    }

    private static class EdgeComparator implements Comparator<Edge>, Serializable {
        @Override
        public int compare(final Edge edge1, final Edge edge2) {
            return edge1.weight - edge2.weight;
        }
    }

    protected static int calculateWeight(final Map<Integer, List<Edge>> adjacency, final int n, final int start) {
        Set<Integer> connected = new TreeSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
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
            // if (vertex == start && connected.contains(start)) {
            // break;
            // }
            connected.add(vertex);
            queue.addAll(adjacency.get(vertex).stream().collect(Collectors.toList()));
        }
        return total;
    }
}
