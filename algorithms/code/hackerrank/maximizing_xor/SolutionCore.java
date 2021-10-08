package hackerrank.maximizing_xor;

/**
 * https://www.hackerrank.com/challenges/maximizing-xor
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int maximizingXOR(final int l, final int r) {
        int max = l ^ r;
        max |= max >> 1;
        max |= max >> 2;
        max |= max >> 4;
        max |= max >> 8;
        return max;
    }
}
