package leetcode.lc069_sqrt_x;

/**
 * https://leetcode.com/problems/sqrtx/ #easy
 */
public final class LC069SqrtX {
    public int mySqrt(final int x) {
        long a = 0;
        long z = x;
        while (a + 1 < z) {
            long m = (a + z) / 2;
            long mm = m * m;
            if (mm == x) {
                return (int) m;
            }
            if (mm < x) {
                a = m;
            } else {
                z = m - 1;
            }
        }
        if (z * z <= x) {
            return (int) z;
        }
        return (int) a;
    }
}
package leetcode.lc069_sqrt_x;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC069SqrtXTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(4));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(8));
    }

    @Test
    public void test64() throws Exception {
        assertEquals(8, new LC069SqrtX().mySqrt(64));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(2));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(1));
    }
}
