package leetcode;

/**
 * https://leetcode.com/problems/sqrtx/
 */
public class LC069SqrtX {
    public int mySqrt(int x) {
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
        } else
            return (int) a;
    }

    public static void main(String[] arguments) {
        LC069SqrtX solution = new LC069SqrtX();
        System.out.println(solution.mySqrt(64));
        System.out.println(solution.mySqrt(2));
    }
}
