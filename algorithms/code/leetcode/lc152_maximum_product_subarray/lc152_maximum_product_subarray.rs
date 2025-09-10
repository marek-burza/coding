// https://leetcode.com/problems/maximum-product-subarray/
// #medium

pub struct Solution;

impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut max = i32::MIN;
        let mut cmin = 0;
        let mut cmax = 0;
        for num in nums {
            let mut tmin = num;
            let mut tmax = num;
            if cmin != 0 {
                tmin *= cmin;
            }
            if cmax != 0 {
                tmax *= cmax;
            }
            cmin = tmin.min(tmax);
            cmin = num.min(cmin);
            cmax = tmin.max(tmax);
            cmax = num.max(cmax);
            max = max.max(cmax);
        }
        max
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2_3_minus2_4() {
        let nums: Vec<i32> = vec![2, 3, -2, 4];
        assert_eq!(Solution::max_product(nums), 6);
    }
}
