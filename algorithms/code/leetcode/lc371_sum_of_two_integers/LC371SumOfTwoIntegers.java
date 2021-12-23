package leetcode.lc371_sum_of_two_integers;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 * #medium
 */
public final class LC371SumOfTwoIntegers {
    public int getSum(final int a, final int b) {
        int result = 0;
        int carry = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            int am = a & mask;
            int bm = b & mask;
            result |= am ^ bm ^ carry;
            carry = (am & bm) | (bm & carry) | (am & carry);
            carry <<= 1;
        }
        return result;
    }
}
