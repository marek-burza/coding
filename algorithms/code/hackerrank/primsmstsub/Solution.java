package hackerrank.primsmstsub;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hackerrank.primsmstsub.SolutionCore.Edge;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public final class Solution {
    private Solution() {
    }

    private static Reader inOverride = null;
    private static Writer outOverride = null;

    public static void main(final String[] args) {
        if (null == inOverride) {
            inOverride = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        }
        if (null == outOverride) {
            outOverride = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
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
            adjacent.add(new Edge(b, weight));
            adjacent = adjacency.get(b);
            if (null == adjacent) {
                adjacent = new ArrayList<>();
                adjacency.put(b, adjacent);
            }
            adjacent.add(new Edge(a, weight));
        }
        int start = in.nextInt() - 1;
        try {
            outOverride.write(String.valueOf(SolutionCore.calculateWeight(adjacency, n, start)));
            outOverride.write('\n');
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            in.close();
        }
    }
}
