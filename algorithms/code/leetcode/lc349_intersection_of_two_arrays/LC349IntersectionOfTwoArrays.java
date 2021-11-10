package leetcode.lc349_intersection_of_two_arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/ #easy
 */
public final class LC349IntersectionOfTwoArrays {
    public int[] intersection(final int[] nums1, final int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> found = new HashSet<>();
        for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length;) {
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
        int index = 0;
        for (int value : found) {
            result[index] = value;
            index++;
        }
        return result;
    }
}
