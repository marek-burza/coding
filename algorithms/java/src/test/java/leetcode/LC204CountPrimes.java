package leetcode;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/count-primes/
 * #medium
 */
public class LC204CountPrimes {
    public class Solution {
        public int countPrimes(int n) {
            if (n < 2) {
                return 0;
            }
            // Eratosthenes sieve
            boolean[] sieve = new boolean[n - 2];
            Arrays.fill(sieve, true);
            int count = 0;
            for (int i = 0; i < sieve.length; i++) {
                if (!sieve[i]) {
                    continue;
                }
                count++;
                int number = 2 + i;
                for (int j = i + number; j < sieve.length; j += number) {
                    sieve[j] = false;
                }
            }
            return count;
        }
    }

    @Test
    public void test_11() throws Exception {
        assertEquals(4, new Solution().countPrimes(11));
    }
}
