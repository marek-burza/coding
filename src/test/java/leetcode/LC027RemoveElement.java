package leetcode;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/remove-element/
 */
public class LC027RemoveElement {
    public class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums == null) return 0;
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                nums[index] = nums[i];
                if (nums[i] != val) {
                    index++;
                }
            }
            return index;
        }
    }

    @Test
    public void test_0_42_1_2_42_3_4__42() throws Exception {
        int[] nums = {0, 42, 1, 2, 42, 3, 4};
        int length = new Solution().removeElement(nums, 42);
        assertEquals(5, length);
        int[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, Arrays.copyOfRange(nums, 0, length));
    }
}
