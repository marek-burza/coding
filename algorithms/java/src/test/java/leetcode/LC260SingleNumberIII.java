package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/single-number-iii/
 * #medium
 */
public class LC260SingleNumberIII {
    public class Solution {
        public int[] singleNumber(int[] nums) {
            int xor = 0;
            for (int value : nums) {
                xor ^= value;
            }
            int mask = xor & (~(xor - 1));
            int[] values = {0, 0};
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

    @Test
    public void test() throws Exception {
        int[] result = new Solution().singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        Arrays.sort(result);
        assertArrayEquals(new int[]{3, 5}, result);
    }
}
