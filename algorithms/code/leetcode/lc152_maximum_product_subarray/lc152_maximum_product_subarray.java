package leetcode.lc152_maximum_product_subarray;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * #medium
 */
public final class LC152MaximumProductSubarray {
    public int maxProduct(final int[] nums) {
        int max = Integer.MIN_VALUE;
        int cmin = 0;
        int cmax = 0;
        for (int num : nums) {
            int tmin = num;
            int tmax = num;
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
```rust
// Maximum Product Subarray
pub struct LC152MaximumProductSubarray;

impl LC152MaximumProductSubarray {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut max = i32::MIN;
        let mut c_min = 0;
        let mut c_max = 0;
        for num in nums {
            let t_min = num;
            let t_max = num;
            if c_min != 0 {
                t_min *= c_min;
            }
            if c_max != 0 {
                t_max *= c_max;
            }
            c_min = t_min.min(t_max);
            c_min = num.min(c_min);
            c_max = t_min.max(t_max);
            c_max = num.max(c_max);
            max = max.max(c_max);
        }
        max
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test23_minus24() {
        let nums: Vec<i32> = vec![2, 3, -2, 4];
        assert_eq!(LC152MaximumProductSubarray::max_product(nums), 6);
    }
}
```