package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/unique-paths/
 * #medium
 */
public final class LC062UniquePaths {
    public final class Solution {
        private long nck(final long n, final long k) {
            if (k > n) return 0;
            if (k * 2 > n) k = n - k;
            if (k == 0) return 1;
            long r = n;
            for (long i = 2; i <= k; ++i) {
                r *= n - i + 1;
                r /= i;
            }
            return r;
        }

        public int uniquePaths(final int m, final int n) {
            m--;
            return (int) nck(m + n - 1, m);
        }
    }

    @Test
    public void test_3_7() throws Exception {
        assertEquals(28, new Solution().uniquePaths(3, 7));
    }
}
