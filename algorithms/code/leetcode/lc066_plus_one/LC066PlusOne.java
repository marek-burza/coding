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
