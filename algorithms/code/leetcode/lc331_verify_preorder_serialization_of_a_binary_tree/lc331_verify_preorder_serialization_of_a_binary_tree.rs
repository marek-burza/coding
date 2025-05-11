// https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
// #medium

pub struct Solution;

impl Solution {
    pub fn is_valid_serialization(preorder: &str) -> bool {
        if preorder.is_empty() {
            return false;
        }
        let items = preorder.split(",");
        let mut kids = vec![1];
        for node in items {
            while *kids.last().unwrap() == 2 {
                kids.pop();
                if kids.is_empty() {
                    return false;
                }
            }
            let value = kids.pop().unwrap();
            kids.push(value + 1);
            if *node != *"#" {
                kids.push(0);
            }
        }
        while !kids.is_empty() && *kids.last().unwrap() == 2 {
            kids.pop();
        }
        kids.is_empty()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert!(Solution::is_valid_serialization("#"));
    }

    #[test]
    fn test_empty_but_not_really() {
        assert!(!Solution::is_valid_serialization("#1"));
    }

    #[test]
    fn test_example_1() {
        assert!(Solution::is_valid_serialization(
            "9,3,4,#,#,1,#,#,2,#,6,#,#"
        ));
    }

    #[test]
    fn test_example_2() {
        assert!(!Solution::is_valid_serialization("1,#"));
    }

    #[test]
    fn test_example_3() {
        assert!(!Solution::is_valid_serialization("9,#,#,1"));
    }

    #[test]
    fn test_nothing() {
        assert!(!Solution::is_valid_serialization(""));
    }
}
