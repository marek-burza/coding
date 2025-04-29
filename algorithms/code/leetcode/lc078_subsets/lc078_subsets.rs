// https://leetcode.com/problems/subsets/
// #medium

pub struct Solution;

impl Solution {
    fn subsets_internal(
        nums: &[i32],
        offset: usize,
        current: &mut Vec<i32>,
        list: &mut Vec<Vec<i32>>,
    ) {
        list.push(current.clone());
        for i in offset..nums.len() {
            current.push(nums[i]);
            Self::subsets_internal(nums, i + 1, current, list);
            current.pop();
        }
    }

    pub fn subsets(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort_unstable();
        let mut list = Vec::new();
        Self::subsets_internal(&nums, 0, &mut Vec::new(), &mut list);
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_123() {
        let mut list = Solution::subsets(vec![1, 2, 3]);
        let mut expected = vec![
            vec![],
            vec![1],
            vec![2],
            vec![3],
            vec![1, 2],
            vec![1, 3],
            vec![2, 3],
            vec![1, 2, 3],
        ];
        list.sort_unstable();
        expected.sort_unstable();
        assert_eq!(list.len(), expected.len());
        for i in 0..expected.len() {
            let mut sorted_list = list[i].clone();
            sorted_list.sort_unstable();
            let mut sorted_expected = expected[i].clone();
            sorted_expected.sort_unstable();
            assert_eq!(sorted_list, sorted_expected);
        }
    }
}
