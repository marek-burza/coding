package hackerrank.staircase;

/**
 * https://www.hackerrank.com/challenges/staircase
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String generateStep(final int n, final int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(i < n - 1 - index ? " " : "#");
        }
        return builder.toString();
    }
}
