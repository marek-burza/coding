package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * #easy
 */
public class LC088MergeSortedArray {
    public final class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m + n - 1;
            m--;
            n--;
            while (m >= 0 && n >= 0) {
                if (nums1[m] > nums2[n]) {
                    nums1[i] = nums1[m];
                    m--;
                } else {
                    nums1[i] = nums2[n];
                    n--;
                }
                i--;
            }
            while (n >= 0) {
                nums1[i] = nums2[n];
                n--;
                i--;
            }
        }
    }

    @Test
    public void test_1_3_7_11_0_0_0__4__4_6_20__3() throws Exception {
        int[] nums1 = {1, 3, 7, 11, 0, 0, 0};
        int[] nums2 = {4, 6, 20};
        int[] expected = {1, 3, 4, 6, 7, 11, 20};
        new Solution().merge(nums1, 4, nums2, 3);
        assertArrayEquals(expected, nums1);
    }
}
