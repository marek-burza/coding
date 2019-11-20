package hackerrank.nondivisiblesubset;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/non-divisible-subset
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
        int k = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        outOverride.println(countMaximumSubset(a, k));
    }

    private static int countMaximumSubset(int[] array, int k) {
        Map<Integer, Integer> counted = new HashMap<>();
        for (int value : array) {
            int rest = value % k;
            Integer count = counted.get(rest);
            if (null == count) {
                counted.put(rest, 1);
            } else {
                counted.put(rest, count + 1);
            }
        }
        Set<Integer> ok = new HashSet<>();
        for (int a : counted.keySet()) {
            int b = k - a;
            if (a == 0 || a == b) continue;
            if (!counted.containsKey(b)) {
                ok.add(a);
            } else {
                int countA = counted.get(a);
                int countB = counted.get(b);
                ok.add(countA > countB ? a : b);
            }
        }
        int total = 0;
        for (int a : ok) {
            total += counted.get(a);
        }
        if (counted.containsKey(0)) {
            total++;
        }
        if ((k & 1) == 0 && counted.containsKey(k >> 1)) {
            total++;
        }
        return total;
    }
}
