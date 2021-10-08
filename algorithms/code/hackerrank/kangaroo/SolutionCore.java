package hackerrank.kangaroo;

/**
 * https://www.hackerrank.com/challenges/kangaroo
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean checkKangaroos(final int x1, final int v1, final int x2, final int v2) {
        if (v1 == v2) {
            return x1 == x2;
        }
        return 0 == (x2 - x1) % (v2 - v1) && 0 > (x2 - x1) / (v2 - v1);
    }
}
