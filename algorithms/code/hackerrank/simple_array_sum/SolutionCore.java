package hackerrank.simple_array_sum;

/**
 * https://www.hackerrank.com/challenges/simple-array-sum
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static long simpleArraySum(final int n, final int[] array) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        return sum;
    }
}
