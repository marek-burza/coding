package leetcode.lc066_plus_one;

/**
 * https://leetcode.com/problems/plus-one/ #easy
 */
public final class LC066PlusOne {
    public int[] plusOne(final int[] digits) {
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
        }
        return digits;
    }
}
package leetcode.lc066_plus_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC066PlusOneTests {
    @Test
    public void test1And9() throws Exception {
        int[] expected = { 2, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 1, 9 }));
    }

    @Test
    public void test9And9() throws Exception {
        int[] expected = { 1, 0, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 9, 9 }));
    }
}
