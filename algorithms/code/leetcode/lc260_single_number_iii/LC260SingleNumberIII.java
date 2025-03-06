package leetcode.lc260_single_number_iii;

/**
 * https://leetcode.com/problems/single-number-iii/
 * #medium
 */
public final class LC260SingleNumberIII {
    public int[] singleNumber(final int[] nums) {
        int xor = 0;
        for (int value : nums) {
            xor ^= value;
        }
        int mask = xor & (~(xor - 1));
        int[] values = { 0, 0 };
        for (int value : nums) {
            if ((value & mask) == 0) {
                values[0] ^= value;
            } else {
                values[1] ^= value;
            }
        }
        return values;
    }
}
package leetcode.lc260_single_number_iii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC260SingleNumberIIITests {
    @Test
    public void test() throws Exception {
        int[] result = new LC260SingleNumberIII().singleNumber(new int[] { 1, 2, 1, 3, 2, 5 });
        Arrays.sort(result);
        assertArrayEquals(new int[] { 3, 5 }, result);
    }
}
