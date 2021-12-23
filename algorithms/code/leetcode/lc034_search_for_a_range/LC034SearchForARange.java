package leetcode.lc034_search_for_a_range;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * #medium
 */
public final class LC034SearchForARange {
    private int bsInfimum(final int[] nums, final int target) {
        int a = 0;
        int z = nums.length - 1;
        while (a < z) {
            int m = (a + z) >>> 1;
            if (nums[m] < target) {
                a = m + 1;
            }
            if (nums[m] == target) {
                z = m;
            }
            if (nums[m] > target) {
                z = m - 1;
            }
        }
        if (/* a == z && */nums[a] == target) {
            return a;
        } else {
            return -1;
        }
    }

    private int bsSupremum(final int[] nums, final int target) {
        int a = 0;
        int z = nums.length - 1;
        while (a < z) {
            int m = (1 + a + z) >>> 1;
            if (nums[m] < target) {
                a = m + 1;
            }
            if (nums[m] == target) {
                a = m;
            }
            if (nums[m] > target) {
                z = m - 1;
            }
        }
        if (a == z && nums[a] == target) {
            return a;
        } else {
            return -1;
        }
    }

    public int[] searchRange(final int[] nums, final int target) {
        if (nums == null || nums.length == 0) {
            return new int[] { -1, -1 };
        }
        return new int[] { bsInfimum(nums, target), bsSupremum(nums, target) };
    }
}
