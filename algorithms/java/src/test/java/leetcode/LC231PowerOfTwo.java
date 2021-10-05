package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/power-of-two/
 * #easy
 */
public class LC231PowerOfTwo {
    public class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            int count = 0;
            for (int mask = 1; mask != 0; mask <<= 1) {
                count += ((n & mask) == 0) ? 0 : 1;
            }
            return count == 1;
        }
    }

    @Test
    public void test_Minus10() throws Exception {
        assertEquals(false, new Solution().isPowerOfTwo(-10));
    }

    @Test
    public void test_0() throws Exception {
        assertEquals(false, new Solution().isPowerOfTwo(0));
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(true, new Solution().isPowerOfTwo(1));
    }

    @Test
    public void test_2() throws Exception {
        assertEquals(true, new Solution().isPowerOfTwo(2));
    }

    @Test
    public void test_5() throws Exception {
        assertEquals(false, new Solution().isPowerOfTwo(5));
    }

    @Test
    public void test_1024() throws Exception {
        assertEquals(true, new Solution().isPowerOfTwo(1024));
    }
}
