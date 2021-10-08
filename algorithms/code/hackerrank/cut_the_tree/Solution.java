package hackerrank.cut_the_tree;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/cut-the-tree
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
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = in.nextInt();
        }
        int[][] pairs = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            pairs[i][0] = in.nextInt();
            pairs[i][1] = in.nextInt();
        }
        try {
            outOverride.write(String.valueOf(SolutionCore.minimalTreeDiff(values, pairs)));
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
