// https://leetcode.com/problems/search-insert-position/
// #easy

pub fn search_insert(nums: Vec<i32>, target: i32) -> usize {
    let mut a = 0usize;
    let mut z = nums.len();
    while a != z {
        let m = (a + z) >> 1;
        if nums[m] < target {
            a = m + 1;
        } else {
            z = m;
        }
    }
    z
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty_and_5() {
        let nums: Vec<i32> = vec![];
        assert_eq!(0usize, search_insert(nums, 5));
    }

    #[test]
    fn test_1356_and_5() {
        let nums: Vec<i32> = vec![1, 3, 5, 6];
        assert_eq!(2usize, search_insert(nums, 5));
    }

    #[test]
    fn test_1356_and_2() {
        let nums: Vec<i32> = vec![1, 3, 5, 6];
        assert_eq!(1usize, search_insert(nums, 2));
    }

    #[test]
    fn test_1356_and_7() {
        let nums: Vec<i32> = vec![1, 3, 5, 6];
        assert_eq!(4usize, search_insert(nums, 7));
    }

    #[test]
    fn test_1356_and_0() {
        let nums: Vec<i32> = vec![1, 3, 5, 6];
        assert_eq!(0usize, search_insert(nums, 0));
    }

    #[test]
    fn test_1356_and_1() {
        let nums: Vec<i32> = vec![1, 3, 5, 6];
        assert_eq!(0usize, search_insert(nums, 1));
    }
}
