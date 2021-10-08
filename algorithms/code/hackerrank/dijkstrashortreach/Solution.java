package hackerrank.dijkstrashortreach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import hackerrank.dijkstrashortreach.SolutionCore.Graph;
import hackerrank.dijkstrashortreach.SolutionCore.Vertex;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
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
        BufferedReader reader = new BufferedReader(inOverride);
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                String nm = reader.readLine();
                if (nm == null) {
                    break;
                }
                int space = nm.indexOf(' ');
                int n = Integer.parseInt(nm.substring(0, space));
                Graph graph = new Graph(n);
                int m = Integer.parseInt(nm.substring(space + 1));
                for (int j = 0; j < m; j++) {
                    String edge = reader.readLine();
                    if (edge == null) {
                        break;
                    }
                    int firstSpace = edge.indexOf(' ');
                    int secondSpace = edge.indexOf(' ', firstSpace + 1);
                    int x = Integer.parseInt(edge.substring(0, firstSpace)) - 1;
                    int y = Integer.parseInt(edge.substring(firstSpace + 1, secondSpace)) - 1;
                    int r = Integer.parseInt(edge.substring(secondSpace + 1));
                    graph.add(x, y, r);
                }
                int s = Integer.parseInt(reader.readLine()) - 1;
                Vertex[] result = SolutionCore.dijkstra(graph, s);
                boolean first = true;
                for (int j = 0; j < n; j++) {
                    if (j == s) {
                        continue;
                    }
                    if (first) {
                        first = false;
                    } else {
                        outOverride.write(" ");
                    }
                    long value = result[j].distance;
                    if (value == Long.MAX_VALUE) {
                        outOverride.write(String.valueOf(-1));
                    } else {
                        outOverride.write(String.valueOf(value));
                    }
                }
                outOverride.write('\n');
            }
            outOverride.flush();
            outOverride.close();
            reader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
