package hackerrank.missingnumbers;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/missing-numbers
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
        SortedSet<Integer> missing = findMissing(almost, all);
        boolean first = true;
        for (int value : missing) {
            if (first) {
                first = false;
            } else {
                outOverride.print(" ");
            }
            outOverride.print(value);
        }
    }

    private static SortedSet<Integer> findMissing(int[] almost, int[] all) {
        SortedSet<Integer> missing = new TreeSet<>();
        Arrays.sort(almost);
        Arrays.sort(all);
        int n = almost.length;
        int m = all.length;
        for (int i = 0, j = 0; j < m; j++) {
            if (i < n) {
                if (almost[i] == all[j]) {
                    i++;
                } else {
                    missing.add(all[j]);
                }
            } else {
                missing.add(all[j]);
            }
        }
        return missing;
    }
}
