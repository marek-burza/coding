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
