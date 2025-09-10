// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// #hard

pub struct Solution;

impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut z = nums.len() - 1;
        while a < z {
            let m = (a + z) / 2;
            match nums[z].cmp(&nums[m]) {
                std::cmp::Ordering::Less => {
                    a = m + 1;
                }
                std::cmp::Ordering::Greater => {
                    z = m;
                }
                _ => {
                    z -= 1;
                }
            }
        }
        nums[a]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(Solution::find_min(nums), 0);
    }

    #[test]
    fn test_trickier() {
        let nums = vec![1, 1, 0, 1, 1, 1, 1];
        assert_eq!(Solution::find_min(nums), 0);
    }
}
