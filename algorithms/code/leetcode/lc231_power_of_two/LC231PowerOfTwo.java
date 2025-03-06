package leetcode.lc231_power_of_two;

/**
 * https://leetcode.com/problems/power-of-two/ #easy
 */
public final class LC231PowerOfTwo {
    public boolean isPowerOfTwo(final int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            count += ((n & mask) == 0) ? 0 : 1;
        }
        return count == 1;
    }
}
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
