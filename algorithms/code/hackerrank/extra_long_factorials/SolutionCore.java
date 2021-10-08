package hackerrank.extra_long_factorials;

import java.math.BigInteger;

/**
 * https://www.hackerrank.com/challenges/extra-long-factorials
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static BigInteger factorial(final int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
