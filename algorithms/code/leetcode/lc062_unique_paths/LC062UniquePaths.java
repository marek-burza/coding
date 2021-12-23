package leetcode.lc062_unique_paths;

/**
 * https://leetcode.com/problems/unique-paths/
 * #medium
 */
public final class LC062UniquePaths {
    private long nck(final long n, final long kValue) {
        long k = kValue;
        if (k > n) {
            return 0;
        }
        if (k * 2 > n) {
            k = n - k;
        }
        if (k == 0) {
            return 1;
        }
        long r = n;
        for (long i = 2; i <= k; ++i) {
            r *= n - i + 1;
            r /= i;
        }
        return r;
    }

    public int uniquePaths(final int mValue, final int n) {
        int m = mValue;
        m--;
        return (int) nck(m + n - 1, m);
    }
}
