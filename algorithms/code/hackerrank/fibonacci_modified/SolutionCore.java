package hackerrank.fibonacci_modified;

import java.math.BigInteger;

/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String fibonacciModified(final int t1, final int t2, final int nValue) {
        int n = nValue;
        BigInteger tn1 = BigInteger.valueOf(t1);
        BigInteger tn2 = BigInteger.valueOf(t2);
        while (3 <= n) {
            BigInteger result = BigInteger.valueOf(0);
            result = result.add(tn2);
            result = result.multiply(result);
            result = result.add(tn1);
            tn1 = tn2;
            tn2 = result;
            n--;
        }
        return tn2.toString();
    }
}
