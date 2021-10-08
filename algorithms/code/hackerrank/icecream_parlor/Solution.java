package hackerrank.icecream_parlor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor
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
                int m = in.nextInt();
                int n = in.nextInt();
                int[] c = new int[n];
                for (int j = 0; j < n; j++) {
                    c[j] = in.nextInt();
                }
                int[] result = SolutionCore.which(m, c);
                outOverride.write(String.valueOf(result[0]));
                outOverride.write(" ");
                outOverride.write(String.valueOf(result[1]));
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
