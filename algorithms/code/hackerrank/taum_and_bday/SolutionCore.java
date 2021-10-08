package hackerrank.taum_and_bday;

/**
 * https://www.hackerrank.com/challenges/taum-and-bday
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static long minimumCost(final int b, final int w, final int x, final int y, final int z) {
        long xAdjusted = x <= y + z ? x : y + z;
        long yAdjusted = y <= x + z ? y : x + z;
        return b * xAdjusted + w * yAdjusted;
    }
}
