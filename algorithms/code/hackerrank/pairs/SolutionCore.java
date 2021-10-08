package hackerrank.pairs;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int countPairs(final int[] array, final int k) {
        Arrays.sort(array);
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (Arrays.binarySearch(array, i + 1, array.length, array[i] + k) >= 0) {
                count++;
            }
        }
        return count;
    }
}
