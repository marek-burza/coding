package leetcode.lc313_super_ugly_number;

/**
 * https://leetcode.com/problems/super-ugly-number/ #medium
 */
public final class LC313SuperUglyNumber {
    public int nthSuperUglyNumber(final int n, final int[] primes) {
        int m = primes.length;
        int[] mul = new int[m];
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int temp1 = -1;
            for (int j = 0; j < m; j++) {
                int temp2 = dp[mul[j]] * primes[j];
                if (dp[i] > temp2) {
                    dp[i] = temp2;
                    temp1 = j;
                } else {
                    if (dp[i] == temp2) {
                        mul[j]++;
                    }
                }
            }
            mul[temp1]++;
        }
        return dp[n - 1];
    }
}
