package leetcode.lc326_power_of_three;

/**
 * https://leetcode.com/problems/power-of-three/ #easy
 * <p/>
 * To do it without a loop resort to logarithms (but beware of accuracy)
 */
public final class LC326PowerOfThree {
    public boolean isPowerOfThree(final int nValue) {
        int n = nValue;
        if (n < 1) {
            return false;
        }
        while (1 < n) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
