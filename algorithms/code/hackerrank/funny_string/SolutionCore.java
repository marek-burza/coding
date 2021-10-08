package hackerrank.funny_string;

/**
 * https://www.hackerrank.com/challenges/funny-string
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean isFunny(final String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != Math.abs(r.charAt(i) - r.charAt(i - 1))) {
                return false;
            }
        }
        return true;
    }
}
