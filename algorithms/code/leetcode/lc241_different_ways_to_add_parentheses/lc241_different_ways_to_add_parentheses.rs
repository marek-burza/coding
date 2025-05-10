// https://leetcode.com/problems/different-ways-to-add-parentheses/
// #medium
// #dynamic-programing

use regex::Regex;
use std::collections::HashMap;

pub struct Solution;

impl Solution {
    fn key(a: usize, z: usize) -> i64 {
        ((a as i64) << 32) | (z as i64)
    }

    fn traverse(
        items: &[String],
        a: usize,
        z: usize,
        cache: &mut HashMap<i64, Vec<i32>>,
    ) -> Vec<i32> {
        let result = cache.get(&Self::key(a, z));
        match result {
            Some(result) => result.to_vec(),
            None => {
                let mut result = Vec::new();
                if a == z {
                    result.push(items[a].parse::<i32>().unwrap());
                } else {
                    for operator in ((a + 1)..z).step_by(2) {
                        let before = Self::traverse(items, a, operator - 1, cache);
                        let after = Self::traverse(items, operator + 1, z, cache);
                        for ante in &before {
                            for post in &after {
                                let operator: &str = &items[operator];
                                match operator {
                                    "+" => result.push(ante + post),
                                    "-" => result.push(ante - post),
                                    _ => result.push(ante * post), // "*"
                                }
                            }
                        }
                    }
                }
                cache.insert(Self::key(a, z), result.clone());
                result
            }
        }
    }

    pub fn diff_ways_to_compute(input: &str) -> Vec<i32> {
        let items: Vec<String> = Regex::new(r"([+\-*]|[^+\-*]+)")
            .unwrap()
            .captures_iter(input)
            .map(|captures| captures[0].to_string())
            .collect();
        Self::traverse(&items, 0, items.len() - 1, &mut HashMap::new())
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let expected: Vec<i32> = vec![0, 2];
        let mut result = Solution::diff_ways_to_compute("2-1-1");
        result.sort();
        assert_eq!(expected, result);
    }

    #[test]
    fn test_example_2() {
        let expected: Vec<i32> = vec![-34, -14, -10, -10, 10];
        let mut result = Solution::diff_ways_to_compute("2*3-4*5");
        result.sort();
        assert_eq!(expected, result);
    }

    #[test]
    fn test_other() {
        let expected: Vec<i32> = vec![7];
        let mut result = Solution::diff_ways_to_compute("3+4");
        result.sort();
        assert_eq!(expected, result);
    }
}
