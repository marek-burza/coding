package leetcode.lc367_valid_perfect_square;

/**
 * https://leetcode.com/problems/valid-perfect-square/ #easy
 */
public final class LC367ValidPerfectSquare {
    public boolean isPerfectSquare(final int num) {
        long a = 0;
        long z = 1 + num / 2;
        while (a <= z) {
            long m = (a + z) >>> 1;
            long mm = m * m;
            if (mm == num) {
                return true;
            }
            if (mm > num) {
                z = m - 1;
            } else {
                a = m + 1;
            }
        }
        return false;
    }
}
