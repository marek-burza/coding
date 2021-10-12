package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/power-of-three/
 * #easy
 * <p/>
 * To do it without a loop resort to logarithms (but beware of accuracy)
 */
public class LC326PowerOfThree {
    public class Solution {
        public boolean isPowerOfThree(int n) {
            if (n < 1) return false;
            while (1 < n) {
                if (n % 3 != 0) {
                    return false;
                }
                n /= 3;
            }
            return true;
        }
    }

    @Test
    public void test_27() throws Exception {
        assertEquals(true, new Solution().isPowerOfThree(27));
    }

    @Test
    public void test_11() throws Exception {
        assertEquals(false, new Solution().isPowerOfThree(11));
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(true, new Solution().isPowerOfThree(1));
    }

    @Test
    public void test_0() throws Exception {
        assertEquals(new Solution().isPowerOfThree(0), false);
    }

    @Test
    public void test_Minus3() throws Exception {
        assertEquals(new Solution().isPowerOfThree(-3), false);
    }
}
