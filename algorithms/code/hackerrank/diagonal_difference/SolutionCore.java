package hackerrank.diagonal_difference;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static long diagonalDifference(final int n, final int[][] matrix) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += matrix[i][i] - matrix[n - 1 - i][i];
        }
        return Math.abs(result);
    }
}
