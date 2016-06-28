package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 */
public class LC006ZigZagConversion {
    public class Solution {
        public String convert(String s, int numRows) {
            if (s == null || numRows < 1) {
                return null;
            }
            StringBuilder buffer = new StringBuilder();
            int n = s.length();
            int scan = (numRows - 1) * 2;
            scan = scan == 0 ? 1 : scan;
            for (int row = 0; row < numRows; row++) {
                for (int i = row; i < n; i += scan) {
                    buffer.append(s.charAt(i));
                    if (0 < row && row < numRows - 1) {
                        int offset = (numRows - 1 - row) * 2;
                        if (i + offset >= n) {
                            break;
                        }
                        buffer.append(s.charAt(i + offset));
                    }
                }
            }
            return buffer.toString();
        }
    }

    @Test
    public void test_3_ABCD() {
        assertEquals("ABDC", new Solution().convert("ABCD", 3));
    }

    @Test
    public void test_2_ABC() {
        assertEquals("ACB", new Solution().convert("ABC", 2));
    }

    @Test
    public void test_1_A() {
        assertEquals("A", new Solution().convert("A", 1));
    }
}
