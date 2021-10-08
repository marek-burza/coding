package hackerrank.missing_numbers;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * https://www.hackerrank.com/challenges/missing-numbers
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static SortedSet<Integer> findMissing(final int[] almost, final int[] all) {
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
