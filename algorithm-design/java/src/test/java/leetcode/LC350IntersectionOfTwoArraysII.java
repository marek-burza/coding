package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class LC350IntersectionOfTwoArraysII {
    public class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            List<Integer> found = new ArrayList<>();
            for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length; ) {
                if (nums1[i1] < nums2[i2]) {
                    i1++;
                    continue;
                }
                if (nums1[i1] > nums2[i2]) {
                    i2++;
                    continue;
                }
                found.add(nums1[i1]);
                i1++;
                i2++;
            }
            int[] result = new int[found.size()];
            for (int index = 0; index < result.length; index++) {
                result[index] = found.get(index);
            }
            return result;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] expected = {2, 2};
        int[] result = new Solution().intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_1_1() throws Exception {
        int[] expected = {1};
        int[] result = new Solution().intersect(new int[]{1}, new int[]{1});
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }
}
