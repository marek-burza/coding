// https://www.hackerrank.com/challenges/divisible-sum-pairs

use crate::hackerrank::tester::{read_input, write_and_check_output};
use std::collections::{HashMap, HashSet};

fn n_choose_k(n: u32, k: u32) -> u64 {
    let mut result = 1u64;
    for ki in 0..k {
        result = result * (n as u64 - ki as u64) / (ki as u64 + 1);
    }
    result
}

pub fn divisible_sum_pairs(_n: i32, k: i32, arr: &Vec<i32>) -> i32 {
    let mut counted = HashMap::<u32, u32>::new();
    for value in arr {
        let rest = (value % k) as u32;
        *counted.entry(rest).or_insert(0) += 1;
    }
    let mut total: u64 = 0;
    let mut covered = HashSet::<u32>::new();
    for (&a, &counted_a) in &counted {
        if covered.contains(&a) {
            continue;
        }
        if a == 0 {
            total += n_choose_k(counted_a, 2);
            covered.insert(a);
        } else {
            let b = k as u32 - a;
            if b == a {
                total += n_choose_k(counted_a, 2);
                covered.insert(a);
            } else if let Some(counted_b) = counted.get(&b) {
                total += (counted_a * counted_b) as u64;
                covered.insert(a);
                covered.insert(b);
            };
        }
    }
    total as i32
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let n = input[0][0].parse::<i32>().unwrap();
    let k = input[0][1].parse::<i32>().unwrap();
    let arr: Vec<i32> = input[1].iter().map(|s| s.parse::<i32>().unwrap()).collect();
    results.push(divisible_sum_pairs(n, k, &arr).to_string());
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        main_tested("_example");
    }

    #[test]
    fn test02() {
        main_tested("02");
    }

    #[test]
    fn test06() {
        main_tested("06");
    }
}
