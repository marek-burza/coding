package hackerrank.non_divisible_subset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * https://www.hackerrank.com/challenges/non-divisible-subset
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int countMaximumSubset(final int[] array, final int k) {
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
        for (Entry<Integer, Integer> entry : counted.entrySet()) {
            int a = entry.getKey();
            int aValue = entry.getValue();
            int b = k - a;
            if (a == 0 || a == b) {
                continue;
            }
            if (!counted.containsKey(b)) {
                ok.add(a);
            } else {
                int countA = aValue;
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
        if ((k & 1) == 0 /* && counted.containsKey(k >> 1) */) {
            total++;
        }
        return total;
    }
}
