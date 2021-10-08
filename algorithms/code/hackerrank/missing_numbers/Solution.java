package hackerrank.missing_numbers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.SortedSet;

/**
 * https://www.hackerrank.com/challenges/missing-numbers
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
        int[] almost = new int[n];
        for (int i = 0; i < n; i++) {
            almost[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] all = new int[m];
        for (int i = 0; i < m; i++) {
            all[i] = in.nextInt();
        }
        SortedSet<Integer> missing = SolutionCore.findMissing(almost, all);
        boolean first = true;
        try {
            for (int value : missing) {
                if (first) {
                    first = false;
                } else {
                    outOverride.write(" ");
                }
                outOverride.write(String.valueOf(value));
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
