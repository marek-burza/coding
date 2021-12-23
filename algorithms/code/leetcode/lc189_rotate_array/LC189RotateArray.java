package leetcode.lc189_rotate_array;

/**
 * https://leetcode.com/problems/rotate-array/
 * #medium
 */
public final class LC189RotateArray {
    private void reverse(final int[] nums, final int aValue, final int bValue) {
        int a = aValue;
        int b = bValue;
        while (a < b) {
            int swap = nums[a];
            nums[a] = nums[b];
            nums[b] = swap;
            a++;
            b--;
        }
    }

    public void rotate(final int[] nums, final int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
}
