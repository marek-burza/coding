package leetcode.lc342_power_of_four;

/**
 * https://leetcode.com/problems/power-of-four/ #easy
 */
public final class LC342PowerOfFour {
    public boolean isPowerOfFour(final int num) {
        if (num <= 0) {
            return false;
        }
        double value = Math.log(num) / Math.log(4);
        return value == Math.floor(value);
    }
}
package leetcode.lc342_power_of_four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC342PowerOfFourTests {
    @Test
    public void test16() throws Exception {
        assertTrue(new LC342PowerOfFour().isPowerOfFour(16));
    }

    @Test
    public void test5() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(5));
    }

    @Test
    public void testNonPositive() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(0));
        assertFalse(new LC342PowerOfFour().isPowerOfFour(-1));
    }
}
