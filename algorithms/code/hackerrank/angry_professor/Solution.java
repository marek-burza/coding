package hackerrank.angry_professor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/angry-professor
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
                int k = in.nextInt();
                int[] a = new int[n];
                for (int j = 0; j < n; j++) {
                    a[j] = in.nextInt();
                }
                outOverride.write(SolutionCore.checkCancellation(a, k) ? "YES\n" : "NO\n");
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
