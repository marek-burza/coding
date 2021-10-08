package hackerrank.camelcase;

/**
 * https://www.hackerrank.com/challenges/camelcase
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int countWords(final String s) {
        int count = 1;
        for (int i = s.length() - 1; 0 <= i; i--) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
