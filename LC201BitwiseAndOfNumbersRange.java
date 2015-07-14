package leetcode;

/**
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 */
public class LC201BitwiseAndOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int result = 0;
        int power = 1;
        int mask = 0;
        for (int i = 0; i < 32; i++) {
            if ((m & power) != 0) {
                if ((power - (m & mask)) > (n - m)) {
                    result |= power;
                }
            }
            power <<= 1;
            mask = (mask << 1) | 1;
        }
        return result;
    }
    // Alternative: Zero all bits after the first difference when checking from highest to lowest bit


    public static void main(String[] arguments) {
        LC201BitwiseAndOfNumbersRange solution = new LC201BitwiseAndOfNumbersRange();
        System.out.println(solution.rangeBitwiseAnd(5, 7));
    }
}
