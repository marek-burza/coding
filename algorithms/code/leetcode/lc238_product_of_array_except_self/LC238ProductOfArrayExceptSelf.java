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
package leetcode.lc238_product_of_array_except_self;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC238ProductOfArrayExceptSelfTests {
    @Test
    public void test1234() throws Exception {
        int[] nums = { 1, 2, 3, 4 };
        int[] expected = { 24, 12, 8, 6 };
        int[] actual = new LC238ProductOfArrayExceptSelf().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test90Minus2() throws Exception {
        int[] nums = { 9, 0, -2 };
        int[] expected = { 0, -18, 0 };
        int[] actual = new LC238ProductOfArrayExceptSelf().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }
}
