package leetcode.lc152_maximum_product_subarray;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * #medium
 */
public final class LC152MaximumProductSubarray {
    public final class Solution {
        public int maxProduct(final int[] nums) {
            int max = Integer.MIN_VALUE;
            int cmin = 0, cmax = 0;
            for (int num : nums) {
                int tmin = num, tmax = num;
                if (cmin != 0) {
                    tmin *= cmin;
                }
                if (cmax != 0) {
                    tmax *= cmax;
                }
                cmin = tmin < tmax ? tmin : tmax;
                cmin = num < cmin ? num : cmin;
                cmax = tmin < tmax ? tmax : tmin;
                cmax = num < cmax ? cmax : num;
                if (cmax > max) {
                    max = cmax;
                }
            }
            return max;
        }
    }

    @Test
    public void test_2_3_Minus2_4() throws Exception {
        int[] nums = {2, 3, -2, 4};
        assertEquals(6, new Solution().maxProduct(nums));
    }
}
