package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/powx-n/
 */
public class LC050PowXN {
    public class Solution {
        public double myPow(double x, int n) {
            if (n == 0) return 1.0;
            long count = n < 0 ? - (long) n: (long) n;
            double result = x;
            long power = 1;
            List<Double> powers = new LinkedList<>();
            while ((power << 1) <= count) {
                powers.add(result);
                result *= result;
                power <<= 1;
            }
            long previous = power >> 1;
            for (int i = powers.size() - 1; 0 <= i; i--) {
                long repeat = (count - power) / previous;
                double value = powers.get(i);
                for (int j = 0; j < repeat; j++) {
                    result *= value;
                }
                power += repeat * previous;
                previous >>= 1;
            }
            result = n < 0 ? 1.0 / result : result;
            return result;
        }
    }

    @Test
    public void test_smaller() throws Exception {
        double x = 34.00515;
        int n = -3;
        double expected = Math.pow(x, n);
        assertEquals(expected, new Solution().myPow(x, n), 0);
    }

    @Test
    public void test_bigger() throws Exception {
        double x = 0.00001;
        int n = 2147483647;
        double expected = Math.pow(x, n);
        assertEquals(expected, new Solution().myPow(x, n), 0);
    }
}
