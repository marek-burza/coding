// https://leetcode.com/problems/permutation-sequence/
// #hard

pub struct Solution;

impl Solution {
    pub fn get_permutation(n: i32, k: i32) -> String {
        let mut k = k;
        if n < 0 || k < 0 {
            return "".to_string();
        }
        let mut result = String::new();
        let mut remaining = Vec::new();
        let mut factorials = Vec::new();
        factorials.push(0);
        let mut factorial = 1;
        for i in 1..=n {
            factorial *= i;
            factorials.push(factorial);
            remaining.push(i);
        }
        for i in 1..n {
            let block = factorials[(n - i) as usize];
            let index = (k - 1) / block;
            result.push_str(&remaining.remove(index as usize).to_string());
            k -= index * block;
        }
        result.push_str(&remaining.remove(0).to_string());
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2_and_1() {
        assert_eq!(Solution::get_permutation(2, 1), "12");
    }

    #[test]
    fn test_3_and_2() {
        assert_eq!(Solution::get_permutation(3, 2), "132");
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::get_permutation(1, -1), "");
        assert_eq!(Solution::get_permutation(-1, 1), "");
    }
}
