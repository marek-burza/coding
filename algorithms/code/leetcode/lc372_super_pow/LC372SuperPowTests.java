package leetcode.lc372_super_pow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC372SuperPowTests {
    @Test
    public void test23() throws Exception {
        assertEquals(8, new LC372SuperPow().superPow(2, new int[] { 3 }));
    }

    @Test
    public void test210() throws Exception {
        assertEquals(1024, new LC372SuperPow().superPow(2, new int[] { 1, 0 }));
    }
}
