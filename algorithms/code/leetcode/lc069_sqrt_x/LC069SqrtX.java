package leetcode.lc069_sqrt_x;

/**
 * https://leetcode.com/problems/sqrtx/ #easy
 */
public final class LC069SqrtX {
    public int mySqrt(final int x) {
        long a = 0;
        long z = x;
        while (a + 1 < z) {
            long m = (a + z) / 2;
            long mm = m * m;
            if (mm == x) {
                return (int) m;
            }
            if (mm < x) {
                a = m;
            } else {
                z = m - 1;
            }
        }
        if (z * z <= x) {
            return (int) z;
        }
        return (int) a;
    }
}
