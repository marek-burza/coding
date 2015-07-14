package leetcode;

/**
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class LC172FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        long step = 5;
        long count = 0;
        while (step <= n) {
            count += n / step;
            step = step * 5;
        }
        return (int) count;
    }

    public static void main(String[] arguments) {
        System.out.println(new LC172FactorialTrailingZeroes().trailingZeroes(5));
        System.out.println(new LC172FactorialTrailingZeroes().trailingZeroes(1808548329));
        System.out.println(new LC172FactorialTrailingZeroes().trailingZeroes(2147483647));
    }
}
