package leetcode.lc238_product_of_array_except_self;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * #medium
 */
public final class LC238ProductOfArrayExceptSelf {
    public int[] productExceptSelf(final int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int other = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            other *= nums[i + 1];
            result[i] *= other;
        }
        return result;
    }
}
