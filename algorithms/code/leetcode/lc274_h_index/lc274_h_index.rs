// https://leetcode.com/problems/h-index/
// #medium

pub struct Solution;

impl Solution {
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let n = citations.len();
        let mut counts = vec![0; n + 1];
        for citation in citations {
            if citation > n as i32 {
                counts[n] += 1;
            } else {
                counts[citation as usize] += 1;
            }
        }
        let mut counted = 0;
        #[allow(unused_assignments)]
        let mut result = 0;
        let mut i = n;
        loop {
            counted += counts[i];
            if counted >= i as i32 {
                result = i as i32;
                break;
            }
            i -= 1;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let citations = vec![3, 0, 6, 1, 5];
        assert_eq!(Solution::h_index(citations), 3);
    }

    #[test]
    fn test_none() {
        let citations = vec![0, 0, 0, 0, 0];
        assert_eq!(Solution::h_index(citations), 0);
    }

    #[test]
    fn test_100() {
        let citations = vec![100];
        assert_eq!(Solution::h_index(citations), 1);
    }
}
