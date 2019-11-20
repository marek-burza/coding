package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 */
public class LC171ExcelSheetColumnNumber {
    public class Solution {
        public int titleToNumber(String s) {
            if (s == null) {
                return -1;
            }
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                result *= 26;
                result += s.charAt(i) - 'A' + 1;
            }
            return result;
        }
    }

    @Test
    public void test_A() throws Exception {
        assertEquals(1, new Solution().titleToNumber("A"));
    }

    @Test
    public void test_B() throws Exception {
        assertEquals(2, new Solution().titleToNumber("B"));
    }

    @Test
    public void test_C() throws Exception {
        assertEquals(3, new Solution().titleToNumber("C"));
    }

    @Test
    public void test_Z() throws Exception {
        assertEquals(26, new Solution().titleToNumber("Z"));
    }

    @Test
    public void test_AA() throws Exception {
        assertEquals(27, new Solution().titleToNumber("AA"));
    }

    @Test
    public void test_AB() throws Exception {
        assertEquals(28, new Solution().titleToNumber("AB"));
    }
}
