package leetcode.lc367_valid_perfect_square;

/**
 * https://leetcode.com/problems/valid-perfect-square/ #easy
 */
public final class LC367ValidPerfectSquare {
    public boolean isPerfectSquare(final int num) {
        long a = 0;
        long z = 1 + num / 2;
        while (a <= z) {
            long m = (a + z) >>> 1;
            long mm = m * m;
            if (mm == num) {
                return true;
            }
            if (mm > num) {
                z = m - 1;
            } else {
                a = m + 1;
            }
        }
        return false;
    }
}
package leetcode.lc367_valid_perfect_square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC367ValidPerfectSquareTests {
    @Test
    public void test1() throws Exception {
        assertTrue(new LC367ValidPerfectSquare().isPerfectSquare(1));
    }

    @Test
    public void test14() throws Exception {
        assertFalse(new LC367ValidPerfectSquare().isPerfectSquare(14));
    }

    @Test
    public void test16() throws Exception {
        assertTrue(new LC367ValidPerfectSquare().isPerfectSquare(16));
    }

    @Test
    public void testMaximum() throws Exception {
        assertFalse(new LC367ValidPerfectSquare().isPerfectSquare(Integer.MAX_VALUE));
    }
}
