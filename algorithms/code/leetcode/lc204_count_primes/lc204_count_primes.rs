// https://leetcode.com/problems/count-primes/
// #medium

pub struct Solution;

impl Solution {
    pub fn count_primes(n: i32) -> i32 {
        if n < 2 {
            return 0;
        }
        let mut sieve = vec![true; (n - 2) as usize];
        let mut count = 0;
        for i in 0..sieve.len() {
            if !sieve[i] {
                continue;
            }
            count += 1;
            let number = 2 + i as i32;
            let mut j = i + number as usize;
            while j < sieve.len() {
                sieve[j] = false;
                j += number as usize;
            }
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_11() {
        assert_eq!(Solution::count_primes(11), 4);
    }

    #[test]
    fn test_1() {
        assert_eq!(Solution::count_primes(1), 0);
    }
}
