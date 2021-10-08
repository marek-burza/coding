package hackerrank.cut_the_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/cut-the-tree
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int minimalTreeDiff(final int[] values, final int[][] pairs) {
        Map<Integer, Vertex> graph = buildGraph(values, pairs);
        int total = Arrays.stream(values).sum();
        int[] minimum = { Integer.MAX_VALUE };
        maximumEdge(graph.get(1), total, minimum, new HashSet<>());
        return minimum[0];
    }

    private static int maximumEdge(final Vertex vertex, final int total, final int[] minimum, final Set<Vertex> seen) {
        if (seen.contains(vertex)) {
            return 0;
        }
        int sum = vertex.value;
        seen.add(vertex);
        for (Vertex other : vertex.adjacent) {
            int partial = maximumEdge(other, total, minimum, seen);
            int candidate = Math.abs(total - 2 * partial);
            if (candidate < minimum[0]) {
                minimum[0] = candidate;
            }
            sum += partial;
        }
        return sum;
    }

    private static Map<Integer, Vertex> buildGraph(final int[] values, final int[][] pairs) {
        Map<Integer, Vertex> mapped = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            mapped.put(i + 1, new Vertex(values[i]));
        }
        for (int[] pair : pairs) {
            mapped.get(pair[0]).adjacent.add(mapped.get(pair[1]));
            mapped.get(pair[1]).adjacent.add(mapped.get(pair[0]));
        }
        return mapped;
    }

    private static final class Vertex {
        public final int value;
        public final List<Vertex> adjacent = new LinkedList<>();

        private Vertex(final int v) {
            value = v;
        }
    }
}
