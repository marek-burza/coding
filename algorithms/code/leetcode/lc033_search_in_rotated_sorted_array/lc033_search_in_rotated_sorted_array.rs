// https://leetcode.com/problems/search-in-rotated-sorted-array/
// #medium

pub fn search(nums: Vec<i32>, target: i32) -> i32 {
    let mut a = 0;
    let mut z = nums.len() - 1;
    while a <= z {
        let m = (a + z) >> 1;
        if nums[m] == target {
            return m as i32;
        }
        if nums[m] < target {
            if target <= nums[z] || nums[a] < nums[m] {
                a = m + 1;
            } else {
                z = m - 1;
            }
        } else if nums[a] <= target || nums[m] < nums[z] {
            z = m - 1;
        } else {
            a = m + 1;
        }
    }
    -1
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(search(nums, 1), 5);
    }

    #[test]
    fn test_single() {
        let nums = vec![1];
        assert_eq!(search(nums.clone(), 1), 0);
    }

    #[test]
    fn test_other() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(search(nums.clone(), 3), -1);
    }

    #[test]
    fn test_another() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(search(nums.clone(), 0), 4);
    }

    #[test]
    fn test_13_and_3() {
        let nums = vec![1, 3];
        assert_eq!(search(nums.clone(), 3), 1);
    }

    #[test]
    fn test_351_and_3() {
        let nums = vec![3, 5, 1];
        assert_eq!(search(nums.clone(), 3), 0);
    }

    #[test]
    fn test_51234_and_1() {
        let nums = vec![5, 1, 2, 3, 4];
        assert_eq!(search(nums.clone(), 1), 1);
    }
}
