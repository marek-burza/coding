// https://leetcode.com/problems/compare-version-numbers/
// #medium

pub struct Solution;

impl Solution {
    pub fn compare_version(version1: String, version2: String) -> i32 {
        let parts1: Vec<String> = version1.split('.').map(|s| s.to_string()).collect();
        let parts2: Vec<String> = version2.split('.').map(|s| s.to_string()).collect();
        for i in 0..std::cmp::max(parts1.len(), parts2.len()) {
            let mut level1 = 0;
            if i < parts1.len() {
                level1 = parts1[i].parse().unwrap();
            }
            let mut level2 = 0;
            if i < parts2.len() {
                level2 = parts2[i].parse().unwrap();
            }
            if level1 < level2 {
                return -1;
            }
            if level1 > level2 {
                return 1;
            }
        }
        0
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_and_1() {
        assert_eq!(
            Solution::compare_version("1".to_string(), "1".to_string()),
            0,
        );
    }

    #[test]
    fn test_1_and_1_point_0() {
        assert_eq!(
            Solution::compare_version("1".to_string(), "1.0".to_string()),
            0,
        );
    }

    #[test]
    fn test_2_and_1() {
        assert_eq!(
            Solution::compare_version("2".to_string(), "1".to_string()),
            1,
        );
    }

    #[test]
    fn test_1_and_13_point_1() {
        assert_eq!(
            Solution::compare_version("1".to_string(), "13.1".to_string()),
            -1,
        );
    }

    #[test]
    fn test_1_point_0_point_1_and_1() {
        assert_eq!(
            Solution::compare_version("1.0.1".to_string(), "1".to_string()),
            1,
        );
    }
}
