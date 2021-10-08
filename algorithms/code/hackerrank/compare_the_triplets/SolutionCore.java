package hackerrank.compare_the_triplets;

/**
 * https://www.hackerrank.com/challenges/compare-the-triplets
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int[] compareTriplets(final int[] a, final int[] b) {
        int[] result = new int[] { 0, 0 };
        for (int i = 0; i < 3; i++) {
            result[0] += a[i] > b[i] ? 1 : 0;
            result[1] += a[i] < b[i] ? 1 : 0;
        }
        return result;
    }
}
