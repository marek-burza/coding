package leetcode.lc007_reverse_integer;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/reverse-integer/
 * #easy
 */
public final class LC007ReverseInteger {
    public int reverse(final int originalX) {
        int x = originalX;
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        boolean negative = x < 0;
        if (negative) {
            x = -x;
        }
        int[] digits = new int[10];
        int collected = 0;
        while (x > 0) {
            digits[collected++] = x % 10;
            x /= 10;
        }
        int[] limits = new int[] { 2, 1, 4, 7, 4, 8, 3, 6, 4, 7 };
        int length = limits.length;
        int padding = length - collected;
        System.arraycopy(digits, 0, digits, padding, collected);
        Arrays.fill(digits, 0, padding, 0);
        for (int i = 0; digits[i] >= limits[i]; i++) {
            if (digits[i] > limits[i]) {
                return 0;
            }
        }
        int result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }
        if (negative) {
            result = -result;
        }
        return result;
    }
}
