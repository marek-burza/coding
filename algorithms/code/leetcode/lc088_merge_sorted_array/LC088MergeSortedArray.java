package leetcode.lc088_merge_sorted_array;

/**
 * https://leetcode.com/problems/merge-sorted-array/ #easy
 */
public final class LC088MergeSortedArray {
    public void merge(final int[] nums1, final int mValue, final int[] nums2, final int nValue) {
        int m = mValue;
        int n = nValue;
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
