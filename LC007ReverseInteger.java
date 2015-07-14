package leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/
 */
public class LC007ReverseInteger {
    public int reverse(int x) {
        long big = x, result = 0;
        int sign = 1;
        if (x < 0) {
            big = -big;
            sign = -1;
        }
        while (big > 0) {
            result *= 10;
            result += big % 10;
            big /= 10;
        }
        result *= sign;
        if (result < Integer.MIN_VALUE || Integer.MAX_VALUE < result) {
            return 0;
        }
        return (int) result;
    }

    public static void main(String[] arguments) {
        LC007ReverseInteger solution = new LC007ReverseInteger();
        System.out.println(solution.reverse(1000000003));
    }
}
