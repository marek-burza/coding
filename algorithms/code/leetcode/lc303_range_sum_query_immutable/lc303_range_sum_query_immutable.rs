// https://leetcode.com/problems/range-sum-query-immutable/
// #easy

pub struct NumArray {
    sums: Vec<i32>,
}

impl NumArray {
    pub fn new(nums: Vec<i32>) -> Self {
        let mut sums = vec![0; nums.len()];
        let mut sum = 0;
        for i in 0..nums.len() {
            sum += nums[i];
            sums[i] = sum;
        }
        Self { sums }
    }

    pub fn sum_range(&self, left: i32, right: i32) -> i32 {
        let mut sum = 0;
        if 0i32 < left {
            sum = -self.sums[left as usize - 1];
        }
        sum += self.sums[right as usize];
        sum
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_0_and_2() {
        let nums = vec![-2, 0, 3, -5, 2, -1];
        assert_eq!(NumArray::new(nums).sum_range(0, 2), 1);
    }

    #[test]
    fn test_2_and_5() {
        let nums = vec![-2, 0, 3, -5, 2, -1];
        assert_eq!(NumArray::new(nums).sum_range(2, 5), -1);
    }

    #[test]
    fn test_0_and_5() {
        let nums = vec![-2, 0, 3, -5, 2, -1];
        assert_eq!(NumArray::new(nums).sum_range(0, 5), -3);
    }
}
