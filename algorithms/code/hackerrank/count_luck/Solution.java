package hackerrank.count_luck;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/count-luck
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
        int t = in.nextInt();
        try {
            for (int i = 0; i < t; i++) {
                int n = in.nextInt();
                int m = in.nextInt();
                char[][] forest = new char[n][m];
                for (int r = 0; r < n; r++) {
                    String line = in.next();
                    for (int c = 0; c < m; c++) {
                        forest[r][c] = line.charAt(c);
                    }
                }
                int k = in.nextInt();
                outOverride.write(SolutionCore.check(forest, k) ? "Impressed\n" : "Oops!\n");
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
