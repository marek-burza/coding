package leetcode.lc326_power_of_three;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC326PowerOfThreeTests {
    @Test
    public void test27() throws Exception {
        assertTrue(new LC326PowerOfThree().isPowerOfThree(27));
    }

    @Test
    public void test11() throws Exception {
        assertFalse(new LC326PowerOfThree().isPowerOfThree(11));
    }

    @Test
    public void test1() throws Exception {
        assertTrue(new LC326PowerOfThree().isPowerOfThree(1));
    }

    @Test
    public void test0() throws Exception {
        assertEquals(new LC326PowerOfThree().isPowerOfThree(0), false);
    }

    @Test
    public void testMinus3() throws Exception {
        assertEquals(new LC326PowerOfThree().isPowerOfThree(-3), false);
    }
}
