// https://leetcode.com/problems/super-ugly-number/
// #medium

pub struct Solution;

impl Solution {
    pub fn nth_super_ugly_number(n: i32, primes: Vec<i32>) -> i32 {
        let m = primes.len();
        let mut mul = vec![0; m];
        let mut dp = vec![0; n as usize];
        dp[0] = 1i32;
        for i in 1..n {
            dp[i as usize] = i32::MAX;
            let mut temp1 = -1i32;
            for j in 0..m {
                let temp2 = dp[mul[j]] * primes[j];
                #[allow(clippy::comparison_chain)]
                if dp[i as usize] > temp2 {
                    dp[i as usize] = temp2;
                    temp1 = j as i32;
                } else if dp[i as usize] == temp2 {
                    mul[j] += 1;
                }
            }
            mul[temp1 as usize] += 1;
        }
        dp[(n - 1) as usize]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let expected = vec![1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32];
        let primes = vec![2, 7, 13, 19];
        for i in 0..expected.len() {
            assert_eq!(
                expected[i],
                Solution::nth_super_ugly_number((i + 1) as i32, primes.clone()),
            );
        }
    }

    #[test]
    fn test_other() {
        let primes = vec![
            7, 19, 29, 37, 41, 47, 53, 59, 61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139, 157,
            167, 179, 181, 199, 211, 229, 233, 239, 241, 251,
        ];
        assert_eq!(1092889481, Solution::nth_super_ugly_number(100000, primes));
    }

    #[test]
    fn test_even_bigger() {
        let primes = vec![
            2, 3, 5, 13, 19, 29, 31, 41, 43, 53, 59, 73, 83, 89, 97, 103, 107, 109, 127, 137, 139,
            149, 163, 173, 179, 193, 197, 199, 211, 223, 227, 229, 239, 241, 251, 257, 263, 269,
            271, 281, 317, 331, 337, 347, 353, 359, 367, 373, 379, 389, 397, 409, 419, 421, 433,
            449, 457, 461, 463, 479, 487, 509, 521, 523, 541, 547, 563, 569, 577, 593, 599, 601,
            613, 619, 631, 641, 659, 673, 683, 701, 709, 719, 733, 739, 743, 757, 761, 769, 773,
            809, 811, 829, 857, 859, 881, 919, 947, 953, 967, 971,
        ];
        assert_eq!(15132, Solution::nth_super_ugly_number(4000, primes));
    }
}
