package leetcode.lc152_maximum_product_subarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC152MaximumProductSubarrayTests {
    @Test
    public void test23Minus24() throws Exception {
        int[] nums = { 2, 3, -2, 4 };
        assertEquals(6, new LC152MaximumProductSubarray().maxProduct(nums));
    }
}
