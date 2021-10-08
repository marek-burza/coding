package hackerrank.kaprekar_numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/kaprekar-numbers
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String[] kaprekarNumbers(final int p, final int q) {
        List<String> found = new ArrayList<>();
        for (int n = p; n <= q; n++) {
            int d = countDigits(n);
            long nn = (long) n * (long) n;
            long splitter = (long) Math.pow(10, d);
            long r = nn / splitter;
            long l = nn % splitter;
            if (n == r + l) {
                found.add(String.valueOf(n));
            }
        }
        if (found.isEmpty()) {
            return new String[] { "INVALID RANGE" };
        } else {
            return found.toArray(new String[found.size()]);
        }
    }

    private static int countDigits(final long v) {
        return 1 + (int) Math.log10(v);
    }
}
