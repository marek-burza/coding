package leetcode.lc258_add_digits;

/**
 * https://leetcode.com/problems/add-digits/ #easy
 */
public final class LC258AddDigits {
    public int addDigits(final int numValue) {
        int num = numValue;
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                int digit = num % 10;
                sum += digit;
                num /= 10;
            }
            num = sum;
        }
        return num;
        // return (int) (num - 9 * Math.floor((num - 1) / 9));
    }
}
package leetcode.lc258_add_digits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC258AddDigitsTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(2, new LC258AddDigits().addDigits(38));
    }
}
