package hackerrank.plus_minus;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/plus-minus
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static double[] fractions(final int n, final int[] array) {
        int[] counts = new int[3];
        for (int value : array) {
            int index = 0 < value ? 0 : value < 0 ? 1 : 2;
            counts[index]++;

        }
        return Arrays.stream(counts).mapToDouble(a -> a).map(v -> v / (double) n).toArray();
    }
}
