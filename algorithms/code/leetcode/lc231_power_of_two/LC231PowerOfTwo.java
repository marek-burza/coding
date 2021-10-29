package leetcode.lc231_power_of_two;

/**
 * https://leetcode.com/problems/power-of-two/ #easy
 */
public final class LC231PowerOfTwo {
    public boolean isPowerOfTwo(final int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            count += ((n & mask) == 0) ? 0 : 1;
        }
        return count == 1;
    }
}
