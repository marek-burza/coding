package hackerrank.tutorial_intro;

/**
 * https://www.hackerrank.com/challenges/tutorial-intro
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int search(final int v, final int n, final int[] array) {
        int a = 0;
        int z = n - 1;
        while (a <= z) {
            int m = (a + z) >>> 1;
            if (array[m] == v) {
                return m;
            }
            if (array[m] < v) {
                a = m + 1;
            }
            if (array[m] > v) {
                z = m - 1;
            }
        }
        return -1;
    }
}
