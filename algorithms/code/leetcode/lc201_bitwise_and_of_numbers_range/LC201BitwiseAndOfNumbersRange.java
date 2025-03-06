package leetcode.lc201_bitwise_and_of_numbers_range;

/**
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 * #medium
 */
public final class LC201BitwiseAndOfNumbersRange {
    public int rangeBitwiseAnd(final int m, final int n) {
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

    // Alternative: Zero all bits after the first difference when checking from
    // highest to lowest bit
}
package leetcode.lc201_bitwise_and_of_numbers_range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC201BitwiseAndOfNumbersRangeTests {
    @Test
    public void test5And7() throws Exception {
        assertEquals(4, new LC201BitwiseAndOfNumbersRange().rangeBitwiseAnd(5, 7));
    }
}
