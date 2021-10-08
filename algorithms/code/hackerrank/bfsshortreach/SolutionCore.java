package hackerrank.bfsshortreach;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.hackerrank.com/challenges/bfsshortreach
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int[] calculateDistances(final boolean[][] adjacency, final int start) {
        int n = adjacency.length;
        Queue<Integer> vertexQ = new LinkedList<>();
        Queue<Integer> distanceQ = new LinkedList<>();
        vertexQ.add(start);
        distanceQ.add(0);
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        while (!vertexQ.isEmpty()) {
            int vertex = vertexQ.poll();
            int distance = distanceQ.poll();
            if (-1 == distances[vertex]) {
                distances[vertex] = distance;
                for (int i = 0; i < n; i++) {
                    if (adjacency[vertex][i]) {
                        vertexQ.add(i);
                        distanceQ.add(distance + 6);
                    }
                }
            }
        }
        return distances;
    }
}
