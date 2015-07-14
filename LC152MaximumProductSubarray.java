package leetcode;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class LC152MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int cmin = 0, cmax = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmin = nums[i], tmax = nums[i];
            if (cmin != 0) {
                tmin *= cmin;
            }
            if (cmax != 0) {
                tmax *= cmax;
            }
            cmin = tmin < tmax ? tmin : tmax;
            cmin = nums[i] < cmin ? nums[i] : cmin;
            cmax = tmin < tmax ? tmax : tmin;
            cmax = nums[i] < cmax ? cmax : nums[i];
            if (cmax > max) {
                max = cmax;
            }
        }
        return max;
    }

    public static void main(String[] argumets) {
        int[] nums = {2, 3, -2, 4};
        LC152MaximumProductSubarray solution = new LC152MaximumProductSubarray();
        System.out.println(solution.maxProduct(nums));
    }
}
