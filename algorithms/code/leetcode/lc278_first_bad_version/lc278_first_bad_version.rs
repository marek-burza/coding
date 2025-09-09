// https://leetcode.com/problems/first-bad-version/
// #easy

pub struct Solution {
    bad_version: i32,
}

impl Solution {
    fn is_bad_version(&self, version: i32) -> bool {
        self.bad_version <= version
    }

    pub fn first_bad_version(&self, n: i32) -> i32 {
        let mut a = 1;
        let mut z = n;
        while a != z {
            let i = (((a as i64) + (z as i64)) / 2) as i32;
            if !self.is_bad_version(i) {
                a = i + 1;
            } else {
                z = i;
            }
        }
        a
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn generic(version: i32, bad_version: i32) {
        let solution = Solution { bad_version };
        assert_eq!(solution.first_bad_version(version), bad_version);
    }

    #[test]
    fn test_example() {
        generic(8000, 456);
    }

    #[test]
    fn test_big_example() {
        generic(2126753390, 1702766719);
    }

    #[test]
    fn test_small_example() {
        generic(1, 1);
    }
}
