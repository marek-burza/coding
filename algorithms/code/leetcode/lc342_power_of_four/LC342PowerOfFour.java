package leetcode.lc342_power_of_four;

/**
 * https://leetcode.com/problems/power-of-four/ #easy
 */
public final class LC342PowerOfFour {
    public boolean isPowerOfFour(final int num) {
        if (num <= 0) {
            return false;
        }
        double value = Math.log(num) / Math.log(4);
        return value == Math.floor(value);
    }
}
