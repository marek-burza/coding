package hackerrank.angry_professor;

/**
 * https://www.hackerrank.com/challenges/angry-professor
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean checkCancellation(final int[] a, final int k) {
        int absent = 0;
        int n = a.length;
        for (int arrival : a) {
            if (arrival > 0) {
                absent++;
            }
            if (n - absent < k) {
                return true;
            }
        }
        return false/* n - absent < k */;
    }
}
