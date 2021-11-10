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
