package leetcode;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class LC026RemoveDuplicatesFromSortedArray {
    public class Solution {
        public int removeDuplicates(int[] nums) {
            int counter = 0;
            for (int i = 1; i < nums.length; i++) {
                int spot = counter;
                if (nums[i] == nums[i - 1 - spot]) {
                    counter++;
                }
                nums[i - spot] = nums[i];
            }
            return nums.length - counter;
        }
    }

    @Test
    public void test_1_2_2_3_4_4_7() throws Exception {
        int[] nums1 = {1, 2, 2, 3, 4, 4, 7};
        int length = new Solution().removeDuplicates(nums1);
        assertEquals(5, length);
        int[] expected = {1, 2, 3, 4, 7};
        assertArrayEquals(expected, Arrays.copyOfRange(nums1, 0, length));
    }
}
