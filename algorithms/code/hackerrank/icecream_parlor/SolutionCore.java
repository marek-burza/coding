package hackerrank.icecream_parlor;

import java.util.HashMap;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int[] which(final int m, final int[] c) {
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
                if (first == second) {
                    continue;
                }
                return new int[] { first + 1, second + 1 };
            }
        }
        return new int[] { 0, 0 };
    }
}
