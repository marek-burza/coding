package hackerrank.the_grid_search;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/the-grid-search
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
                int r = in.nextInt();
                in.nextInt();
                String[] big = new String[r];
                for (int j = 0; j < r; j++) {
                    big[j] = in.next();
                }
                r = in.nextInt();
                in.nextInt();
                String[] small = new String[r];
                for (int j = 0; j < r; j++) {
                    small[j] = in.next();
                }
                outOverride.write(SolutionCore.checkGrid(big, small) ? "YES\n" : "NO\n");
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
