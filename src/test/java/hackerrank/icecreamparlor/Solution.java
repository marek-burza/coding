package hackerrank.icecreamparlor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor
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
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int m = in.nextInt();
            int n = in.nextInt();
            int[] c = new int[n];
            for (int j = 0; j < n; j++) {
                c[j] = in.nextInt();
            }
            int[] result = which(m, c);
            outOverride.printf("%d %d\n", result[0], result[1]);
        }
    }

    private static int[] which(int m, int[] c) {
        int n = c.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(c[i], i);
        }
        for (int i = 0; i < n; i++) {
            int first = i;
            int value = m - c[i];
            if (map.containsKey(value)) {
                int second = map.get(value);
                if (first == second) continue;
                return new int[]{(first < second ? first : second) + 1, (first > second ? first : second) + 1};
            }
        }
        return new int[]{0, 0};
    }
}
