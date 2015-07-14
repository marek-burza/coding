package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/plus-one/
 */
public class LC066PlusOne {
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

    public static void main(String[] arguments) {
        LC066PlusOne adder = new LC066PlusOne();
        System.out.println(Arrays.toString(adder.plusOne(new int[]{1, 9})));
        System.out.println(Arrays.toString(adder.plusOne(new int[]{9, 9})));
    }
}
