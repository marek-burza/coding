package leetcode.lc231_power_of_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC231PowerOfTwoTests {
    @Test
    public void testMinus10() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(-10));
    }

    @Test
    public void test0() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(0));
    }

    @Test
    public void test1() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(1));
    }

    @Test
    public void test2() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(2));
    }

    @Test
    public void test5() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(5));
    }

    @Test
    public void test1024() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(1024));
    }
}
