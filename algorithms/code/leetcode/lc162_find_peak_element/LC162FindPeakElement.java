package leetcode.lc162_find_peak_element;

/**
 * https://leetcode.com/problems/find-peak-element/
 * #medium
 */
public final class LC162FindPeakElement {
    public int findPeakElement(final int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean postFalling;
            postFalling = i == nums.length || nums[i - 1] > nums[i];
            if (postFalling) {
                return i - 1;
            }
        }
        return -1;
    }
}
