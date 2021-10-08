package hackerrank.jumping_on_the_clouds;

/**
 * https://www.hackerrank.com/challenges/jumping-on-the-clouds
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int countJumps(final int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n - 1;) {
            if (i + 2 > n - 1) {
                count++;
                break;
            }
            count += 1 + a[i + 2];
            i += 2 + a[i + 2];
        }
        return count;
    }
}
