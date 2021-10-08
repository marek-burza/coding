package hackerrank.divisible_sum_pairs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * https://www.hackerrank.com/challenges/divisible-sum-pairs
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    private static long nChooseK(final int n, final int k) {
        long result = 1;
        for (int ki = 0; ki < k; ki++) {
            result = result * (n - ki) / (ki + 1);
        }
        return result;
    }

    protected static int countDivisibleSumPairs(final int[] array, final int k) {
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
        int total = 0;
        Set<Integer> covered = new HashSet<>();
        for (Entry<Integer, Integer> entry : counted.entrySet()) {
            int a = entry.getKey();
            int aValue = entry.getValue();
            if (covered.contains(a)) {
                continue;
            }
            if (a == 0) {
                total += nChooseK(aValue, 2);
                covered.add(a);
            } else {
                int b = k - a;
                if (b == a) {
                    total += nChooseK(aValue, 2);
                    covered.add(a);
                } else {
                    if (counted.containsKey(b)) {
                        total += aValue * counted.get(b);
                        covered.add(a);
                        covered.add(b);
                    }
                }
            }
        }
        return total;
    }
}
