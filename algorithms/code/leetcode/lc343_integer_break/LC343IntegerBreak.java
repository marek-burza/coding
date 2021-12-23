package leetcode.lc343_integer_break;

/**
 * https://leetcode.com/problems/integer-break/
 * #medium
 */
public final class LC343IntegerBreak {
    public int integerBreak(final int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 5) {
            return 6;
        }
        int threes = n / 3;
        int rest = (n - 3 * (threes - 1));
        rest = rest == 5 ? 6 : rest;
        return (int) Math.pow(3, threes - 1) * rest;
        // int product = 1;
        // while(n > 4){
        // product *= 3;
        // n -= 3;
        // }
        // return product * n;
    }
}
