package hackerrank.bfsshortreach;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/bfsshortreach
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
        int q = in.nextInt();
        try {
            for (int i = 0; i < q; i++) {
                int n = in.nextInt();
                int m = in.nextInt();
                boolean[][] adjacency = new boolean[n][n];
                for (int j = 0; j < m; j++) {
                    int a = in.nextInt() - 1;
                    int b = in.nextInt() - 1;
                    adjacency[a][b] = true;
                    adjacency[b][a] = true;
                }
                int start = in.nextInt() - 1;
                int[] distances = SolutionCore.calculateDistances(adjacency, start);
                boolean first = true;
                for (int j = 0; j < n; j++) {
                    if (j == start) {
                        continue;
                    }
                    if (!first) {
                        outOverride.write(" ");
                    } else {
                        first = false;
                    }
                    outOverride.write(String.valueOf(distances[j]));
                }
                outOverride.write('\n');
            }
            outOverride.flush();
            outOverride.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            in.close();
        }
    }
}
