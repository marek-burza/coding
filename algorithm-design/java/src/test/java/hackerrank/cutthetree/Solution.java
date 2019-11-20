package hackerrank.cutthetree;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/cut-the-tree
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
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = in.nextInt();
        }
        int[][] pairs = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            pairs[i][0] = in.nextInt();
            pairs[i][1] = in.nextInt();
        }
        outOverride.println(minimalTreeDiff(values, pairs));
    }

    private static int minimalTreeDiff(int[] values, int[][] pairs) {
        Map<Integer, Vertex> graph = buildGraph(values, pairs);
        int total = Arrays.stream(values).sum();
        int[] minimum = {Integer.MAX_VALUE};
        maximumEdge(graph.get(1), total, minimum, new HashSet<>());
        return minimum[0];
    }

    private static int maximumEdge(Vertex vertex, int total, int[] minimum, Set<Vertex> seen) {
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

    private static Map<Integer, Vertex> buildGraph(int[] values, int[][] pairs) {
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

    private static class Vertex {
        public final int value;
        public final List<Vertex> adjacent = new LinkedList<>();

        private Vertex(int value) {
            this.value = value;
        }
    }
}
