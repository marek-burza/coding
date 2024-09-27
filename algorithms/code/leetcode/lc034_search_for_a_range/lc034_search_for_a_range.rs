// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// #medium

fn bs_infimum(nums: &[i32], target: i32) -> i32 {
    let mut a = 0;
    let mut z = nums.len() as i32 - 1;
    while a < z {
        let m = (a + z) >> 1;
        match nums[m as usize].cmp(&target) {
            std::cmp::Ordering::Less => a = m + 1,
            std::cmp::Ordering::Equal => z = m,
            std::cmp::Ordering::Greater => z = m - 1,
        };
    }
    if a == z && nums[a as usize] == target {
        return a;
    }
    -1
}

fn bs_supremum(nums: &[i32], target: i32) -> i32 {
    let mut a = 0;
    let mut z = nums.len() as i32 - 1;
    while a < z {
        let m = (a + z + 1) >> 1;
        match nums[m as usize].cmp(&target) {
            std::cmp::Ordering::Less => a = m + 1,
            std::cmp::Ordering::Equal => a = m,
            std::cmp::Ordering::Greater => z = m - 1,
        };
    }
    if a == z && nums[a as usize] == target {
        return a;
    }
    -1
}

pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
    if nums.is_empty() {
        return vec![-1, -1];
    }
    let infimum = bs_infimum(&nums, target);
    let supremum = bs_supremum(&nums, target);
    vec![infimum, supremum]
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let nums = vec![5, 7, 7, 8, 8, 10];
        assert_eq!(vec![3, 4], search_range(nums, 8));
    }

    #[test]
    fn test_other() {
        let nums = vec![5, 7, 7, 8, 8, 10];
        assert_eq!(vec![-1, -1], search_range(nums, 6));
    }

    #[test]
    fn test_another() {
        let nums = vec![2, 2];
        assert_eq!(vec![-1, -1], search_range(nums, 3));
    }

    #[test]
    fn test_nothing() {
        assert_eq!(vec![-1, -1], search_range(Vec::new(), 3));
    }
}
