package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/plus-one/
 * #easy
 */
public class LC066PlusOne {
    public final class Solution {
        public int[] plusOne(int[] digits) {
            int carry = 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i] += carry;
                carry = digits[i] / 10;
                digits[i] = digits[i] % 10;
            }
            if (carry > 0) {
                int[] bigger = new int[digits.length + 1];
                bigger[0] = carry;
                System.arraycopy(digits, 0, bigger, 1, digits.length);
                return bigger;
            } else {
                return digits;
            }
        }
    }

    @Test
    public void test_1_9() throws Exception {
        int[] expected = {2, 0};
        assertArrayEquals(expected, new Solution().plusOne(new int[]{1, 9}));
    }

    @Test
    public void test_9_9() throws Exception {
        int[] expected = {1, 0, 0};
        assertArrayEquals(expected, new Solution().plusOne(new int[]{9, 9}));
    }
}
