package leetcode.lc263_ugly_number;

/**
 * https://leetcode.com/problems/ugly-number/ #easy
 */
public final class LC263UglyNumber {
    public boolean isUgly(final int numValue) {
        int num = numValue;
        if (num <= 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        int original = num;
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num != original && num == 1;
    }
}
