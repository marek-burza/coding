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
