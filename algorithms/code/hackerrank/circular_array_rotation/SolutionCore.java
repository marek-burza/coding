package hackerrank.circular_array_rotation;

/**
 * https://www.hackerrank.com/challenges/circular-array-rotation
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int query(final int n, final int k, final int[] array, final int index) {
        return array[(index + n - (k % n)) % n];
    }
}
