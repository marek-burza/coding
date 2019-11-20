package hackerrank.bfsshortreach;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/bfsshortreach
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
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            boolean[][] adjacency = new boolean[n][n];
            for (int j = 0; j < m; j++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                adjacency[a][b] = adjacency[b][a] = true;
            }
            int start = in.nextInt() - 1;
            int[] distances = calculateDistances(adjacency, start);
            boolean first = true;
            for (int j = 0; j < n; j++) {
                if (j == start) {
                    continue;
                }
                if (!first) {
                    outOverride.print(" ");
                } else {
                    first = false;
                }
                outOverride.print(distances[j]);
            }
            outOverride.println();
        }
    }

    private static int[] calculateDistances(boolean[][] adjacency, int start) {
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
