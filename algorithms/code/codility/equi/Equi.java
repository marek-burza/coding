package codility.equi;

import java.math.BigInteger;

/**
 * http://blog.codility.com/2011/03/solutions-for-task-equi.html
 */
public final class Equi {
    public int findEquilibriumIndex(final int[] a) {
        int n = a.length;
        if (n == 0) {
            return -1;
        }
        BigInteger[] after = new BigInteger[n];
        after[n - 1] = BigInteger.ZERO;
        for (int i = 1; i < n; i++) {
            after[n - 1 - i] = after[n - i].add(BigInteger.valueOf(a[n - i]));
        }
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            if (sum.equals(after[i])) {
                return i;
            }
            sum = sum.add(BigInteger.valueOf(a[i]));
        }
        return -1;
    }
}
