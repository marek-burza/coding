package leetcode;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class LC191NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static void main(String[] arguments) {
        System.out.println(new LC191NumberOf1Bits().hammingWeight(11));
    }
}
