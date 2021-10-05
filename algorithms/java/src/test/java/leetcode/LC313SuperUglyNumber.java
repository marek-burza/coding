package leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/super-ugly-number/
 * #medium
 */
public class LC313SuperUglyNumber {
    public class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            int[] ugly = new int[n];
            int[] idx = new int[primes.length];

            ugly[0] = 1;
            for (int i = 1; i < n; i++) {
                // Find next ugly number
                ugly[i] = Integer.MAX_VALUE;
                for (int j = 0; j < primes.length; j++) {
                    ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
                }
                // Skip duplicates
                for (int j = 0; j < primes.length; j++) {
                    while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++;
                }
            }
            return ugly[n - 1];
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] expected = {1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32};
        int[] primes = {2, 7, 13, 19};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new Solution().nthSuperUglyNumber(i + 1, primes));
        }
    }

    @Test
    public void test_other() throws Exception {
        int[] primes = {7, 19, 29, 37, 41, 47, 53, 59, 61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139, 157, 167, 179, 181, 199, 211, 229, 233, 239, 241, 251};
        assertEquals(1092889481, new Solution().nthSuperUglyNumber(100000, primes));
    }

    @Test
    public void test_even_bigger() throws Exception {
        int[] primes = {2, 3, 5, 13, 19, 29, 31, 41, 43, 53, 59, 73, 83, 89, 97, 103, 107, 109, 127, 137, 139, 149, 163, 173, 179, 193, 197, 199, 211, 223, 227, 229, 239, 241, 251, 257, 263, 269, 271, 281, 317, 331, 337, 347, 353, 359, 367, 373, 379, 389, 397, 409, 419, 421, 433, 449, 457, 461, 463, 479, 487, 509, 521, 523, 541, 547, 563, 569, 577, 593, 599, 601, 613, 619, 631, 641, 659, 673, 683, 701, 709, 719, 733, 739, 743, 757, 761, 769, 773, 809, 811, 829, 857, 859, 881, 919, 947, 953, 967, 971};
        assertEquals(15132, new Solution().nthSuperUglyNumber(4000, primes));
    }
}
