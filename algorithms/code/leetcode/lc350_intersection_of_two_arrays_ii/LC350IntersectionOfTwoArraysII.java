package leetcode.lc350_intersection_of_two_arrays_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/ #easy
 */
public final class LC350IntersectionOfTwoArraysII {
    public int[] intersect(final int[] nums1, final int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> found = new ArrayList<>();
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
        for (int index = 0; index < result.length; index++) {
            result[index] = found.get(index);
        }
        return result;
    }
}
