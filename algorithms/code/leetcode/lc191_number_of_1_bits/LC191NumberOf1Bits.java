package leetcode.lc191_number_of_1_bits;

/**
 * https://leetcode.com/problems/number-of-1-bits/ #easy
 */
public final class LC191NumberOf1Bits {
    public int hammingWeight(final int nValue) {
        int n = nValue;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }
}
package leetcode.lc191_number_of_1_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC191NumberOf1BitsTests {
    @Test
    public void test11() throws Exception {
        assertEquals(3, new LC191NumberOf1Bits().hammingWeight(11));
    }
}
