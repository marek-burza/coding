package hackerrank.a_very_big_sum;

/**
 * https://www.hackerrank.com/challenges/a-very-big-sum
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static long bigSum(final int n, final int[] array) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }
}
