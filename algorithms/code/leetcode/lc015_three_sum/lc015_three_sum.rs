// https://leetcode.com/problems/3sum/

pub struct Solution {}

impl Solution {
    pub fn three_sum(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let mut result = vec![];
        let length = nums.len();
        for i in 0..length {
            if i > 0 && nums[i] == nums[i - 1] {
                continue;
            }
            let mut j = i + 1;
            let mut k = length - 1;
            while j < k {
                if k < length - 1 && nums[k] == nums[k + 1] {
                    k -= 1;
                    continue;
                }
                let sum = nums[i] + nums[j] + nums[k];
                match sum.cmp(&0) {
                    std::cmp::Ordering::Less => {
                        j += 1;
                    }
                    std::cmp::Ordering::Greater => {
                        k -= 1;
                    }
                    _ => {
                        result.push(vec![nums[i], nums[j], nums[k]]);
                        j += 1;
                        k -= 1;
                    }
                }
            }
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn check(expected: &Vec<Vec<i32>>, result: &mut Vec<Vec<i32>>) {
        assert_eq!(result.len(), expected.len());
        for entry in expected {
            let mut i = 0;
            while i < result.len() {
                let candidate = &result[i];
                let mut matching = true;
                for j in 0..3 {
                    if entry[j] != candidate[j] {
                        matching = false;
                        break;
                    }
                }
                if matching {
                    result.remove(i);
                    break;
                }
                i += 1;
            }
        }
        assert_eq!(result.len(), 0);
    }

    #[test]
    fn test_example() {
        let s = vec![-1, 0, 1, 2, -1, -4];
        let expected = vec![vec![-1, 0, 1], vec![-1, -1, 2]];
        check(&expected, &mut Solution::three_sum(s));
    }

    #[test]
    fn test_oversized() {
        let s = vec![
            14, -11, -2, -3, 4, -3, -3, -8, -15, 11, 11, -6, -14, -13, 5, -10, -13, 0, -12, -8, 14,
            -12, -10, 2, -4, 9, 13, 10, 2, 7, -2, -7, 4, 11, 5, -7, -15, 10, -7, -14, 6, 11, -5, 7,
            6, 8, 5, 8, -7, 8, -15, 14, 11, 13, 1, -15, 7, 0, 10, -14, 14, -15, -14, 3, 4, 6, 4, 4,
            -7, 12, 5, 14, 0, 8, 7, 13, 1, -11, -2, 9, 12, -1, 8, 0, -11, -5, 0, 11, 2, -13, 4, 1,
            -12, 5, -10, -1, -12, 9, -12, -11, -2, 9, -12, 5, -6, 2, 4, 10, 6, -13, 4, 3, -7, -11,
            11, -3, -9, -4, -15, 8, -9, -4, -13, -14, 8, 14,
        ];
        let expected = vec![
            vec![-15, 1, 14],
            vec![-15, 2, 13],
            vec![-15, 3, 12],
            vec![-15, 4, 11],
            vec![-15, 5, 10],
            vec![-15, 6, 9],
            vec![-15, 7, 8],
            vec![-14, 0, 14],
            vec![-14, 1, 13],
            vec![-14, 2, 12],
            vec![-14, 3, 11],
            vec![-14, 4, 10],
            vec![-14, 5, 9],
            vec![-14, 6, 8],
            vec![-14, 7, 7],
            vec![-13, -1, 14],
            vec![-13, 0, 13],
            vec![-13, 1, 12],
            vec![-13, 2, 11],
            vec![-13, 3, 10],
            vec![-13, 4, 9],
            vec![-13, 5, 8],
            vec![-13, 6, 7],
            vec![-12, -2, 14],
            vec![-12, -1, 13],
            vec![-12, 0, 12],
            vec![-12, 1, 11],
            vec![-12, 2, 10],
            vec![-12, 3, 9],
            vec![-12, 4, 8],
            vec![-12, 5, 7],
            vec![-12, 6, 6],
            vec![-11, -3, 14],
            vec![-11, -2, 13],
            vec![-11, -1, 12],
            vec![-11, 0, 11],
            vec![-11, 1, 10],
            vec![-11, 2, 9],
            vec![-11, 3, 8],
            vec![-11, 4, 7],
            vec![-11, 5, 6],
            vec![-10, -4, 14],
            vec![-10, -3, 13],
            vec![-10, -2, 12],
            vec![-10, -1, 11],
            vec![-10, 0, 10],
            vec![-10, 1, 9],
            vec![-10, 2, 8],
            vec![-10, 3, 7],
            vec![-10, 4, 6],
            vec![-10, 5, 5],
            vec![-9, -5, 14],
            vec![-9, -4, 13],
            vec![-9, -3, 12],
            vec![-9, -2, 11],
            vec![-9, -1, 10],
            vec![-9, 0, 9],
            vec![-9, 1, 8],
            vec![-9, 2, 7],
            vec![-9, 3, 6],
            vec![-9, 4, 5],
            vec![-8, -6, 14],
            vec![-8, -5, 13],
            vec![-8, -4, 12],
            vec![-8, -3, 11],
            vec![-8, -2, 10],
            vec![-8, -1, 9],
            vec![-8, 0, 8],
            vec![-8, 1, 7],
            vec![-8, 2, 6],
            vec![-8, 3, 5],
            vec![-8, 4, 4],
            vec![-7, -7, 14],
            vec![-7, -6, 13],
            vec![-7, -5, 12],
            vec![-7, -4, 11],
            vec![-7, -3, 10],
            vec![-7, -2, 9],
            vec![-7, -1, 8],
            vec![-7, 0, 7],
            vec![-7, 1, 6],
            vec![-7, 2, 5],
            vec![-7, 3, 4],
            vec![-6, -6, 12],
            vec![-6, -5, 11],
            vec![-6, -4, 10],
            vec![-6, -3, 9],
            vec![-6, -2, 8],
            vec![-6, -1, 7],
            vec![-6, 0, 6],
            vec![-6, 1, 5],
            vec![-6, 2, 4],
            vec![-6, 3, 3],
            vec![-5, -5, 10],
            vec![-5, -4, 9],
            vec![-5, -3, 8],
            vec![-5, -2, 7],
            vec![-5, -1, 6],
            vec![-5, 0, 5],
            vec![-5, 1, 4],
            vec![-5, 2, 3],
            vec![-4, -4, 8],
            vec![-4, -3, 7],
            vec![-4, -2, 6],
            vec![-4, -1, 5],
            vec![-4, 0, 4],
            vec![-4, 1, 3],
            vec![-4, 2, 2],
            vec![-3, -3, 6],
            vec![-3, -2, 5],
            vec![-3, -1, 4],
            vec![-3, 0, 3],
            vec![-3, 1, 2],
            vec![-2, -2, 4],
            vec![-2, -1, 3],
            vec![-2, 0, 2],
            vec![-2, 1, 1],
            vec![-1, -1, 2],
            vec![-1, 0, 1],
            vec![0, 0, 0],
        ];
        check(&expected, &mut Solution::three_sum(s));
    }

    #[test]
    fn test_empty() {
        let s = Vec::<i32>::new();
        assert!(Solution::three_sum(s).is_empty());
    }
}
