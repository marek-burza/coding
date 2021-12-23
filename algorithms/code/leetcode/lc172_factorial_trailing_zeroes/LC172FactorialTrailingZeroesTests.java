package leetcode.lc172_factorial_trailing_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC172FactorialTrailingZeroesTests {
    @Test
    public void test5() throws Exception {
        assertEquals(1, new LC172FactorialTrailingZeroes().trailingZeroes(5));
    }

    @Test
    public void test1808548329() throws Exception {
        assertEquals(452137076, new LC172FactorialTrailingZeroes().trailingZeroes(1808548329));
    }

    @Test
    public void test2147483647() throws Exception {
        assertEquals(536870902, new LC172FactorialTrailingZeroes().trailingZeroes(2147483647));
    }
}
