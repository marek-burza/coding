package hackerrank.sherlock_and_array;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean equilibrium(final int[] a) {
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] + a[i - 1];
            right[a.length - 1 - i] = right[a.length - i] + a[a.length - i];
        }
        for (int i = 0; i < a.length; i++) {
            if (left[i] == right[i]) {
                return true;
            }
        }
        return false;
    }
}
