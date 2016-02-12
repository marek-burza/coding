package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/submissions/detail/27668007/
 */
public class LC168ExcelSheetColumnTitle {
    public class Solution {
        public String convertToTitle(int n) {
            StringBuilder buffer = new StringBuilder();
            do {
                n--;
                char digit = (char) ('A' + (n % 26));
                buffer.append(digit);
                n -= n % 26;
                n /= 26;
            } while (n > 0);
            return buffer.reverse().toString();
        }
    }

    @Test
    public void test_1() throws Exception {
        assertEquals("A", new Solution().convertToTitle(1));
    }

    @Test
    public void test_2() throws Exception {
        assertEquals("B", new Solution().convertToTitle(2));
    }

    @Test
    public void test_3() throws Exception {
        assertEquals("C", new Solution().convertToTitle(3));
    }

    @Test
    public void test_26() throws Exception {
        assertEquals("Z", new Solution().convertToTitle(26));
    }

    @Test
    public void test_27() throws Exception {
        assertEquals("AA", new Solution().convertToTitle(27));
    }

    @Test
    public void test_28() throws Exception {
        assertEquals("AB", new Solution().convertToTitle(28));
    }
}
