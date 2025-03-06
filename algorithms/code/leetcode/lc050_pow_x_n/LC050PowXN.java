package leetcode.lc050_pow_x_n;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/powx-n/
 * #medium
 */
public final class LC050PowXN {
    public double myPow(final double x, final int n) {
        if (n == 0) {
            return 1.0;
        }
        long count = n < 0 ? -(long) n : (long) n;
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
package leetcode.lc050_pow_x_n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC050PowXNTests {
    @Test
    public void testSmaller() throws Exception {
        double x = 34.00515;
        int n = -3;
        double expected = Math.pow(x, n);
        assertEquals(expected, new LC050PowXN().myPow(x, n), 0);
    }

    @Test
    public void testBigger() throws Exception {
        double x = 0.00001;
        int n = 2147483647;
        double expected = Math.pow(x, n);
        assertEquals(expected, new LC050PowXN().myPow(x, n), 0);
    }

    @Test
    public void test0() throws Exception {
        assertEquals(1.0, new LC050PowXN().myPow(0, 0), 0);
    }
}
