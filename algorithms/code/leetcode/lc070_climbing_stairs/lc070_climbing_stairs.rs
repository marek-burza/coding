// https://leetcode.com/problems/climbing-stairs/
// #easy

pub struct Solution;

impl Solution {
    fn climb_stairs_internal(n: i32, at: i32, lut: &mut [i32]) -> i32 {
        if at + 2 == n {
            return 2;
        }
        if at + 1 == n {
            return 1;
        }
        if lut[at as usize] == 0 {
            lut[at as usize] = Self::climb_stairs_internal(n, at + 1, lut)
                + Self::climb_stairs_internal(n, at + 2, lut);
        }
        lut[at as usize]
    }

    pub fn climb_stairs(n: i32) -> i32 {
        let mut lut = vec![0; n as usize];
        Self::climb_stairs_internal(n, 0, &mut lut)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_20() {
        assert_eq!(Solution::climb_stairs(20), 10946);
    }
}
