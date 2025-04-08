// https://leetcode.com/problems/sum-root-to-leaf-numbers/
// #easy

use std::cell::RefCell;
use std::rc::Rc;

#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    pub fn new(
        val: i32,
        left: Option<Rc<RefCell<TreeNode>>>,
        right: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<Self>>> {
        Some(Rc::new(RefCell::new(TreeNode { val, left, right })))
    }
}

pub struct Solution;

impl Solution {
    fn sum_numbers_internal(root: Option<Rc<RefCell<TreeNode>>>, prefix_value: i32) -> i32 {
        if root.is_none() {
            return 0;
        }
        let root = root.unwrap();
        let root = root.borrow();
        let prefix_value = prefix_value * 10 + root.val;
        if root.left.is_none() && root.right.is_none() {
            return prefix_value;
        }
        Self::sum_numbers_internal(root.left.clone(), prefix_value)
            + Self::sum_numbers_internal(root.right.clone(), prefix_value)
    }

    pub fn sum_numbers(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        Self::sum_numbers_internal(root, 0)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let root = TreeNode::new(
            1,
            TreeNode::new(2, None, None),
            TreeNode::new(3, None, None),
        );
        assert_eq!(25, Solution::sum_numbers(root));
    }

    #[test]
    fn test_nothing() {
        assert_eq!(0, Solution::sum_numbers(None));
    }

    #[test]
    fn test_left() {
        let root = TreeNode::new(1, TreeNode::new(2, None, None), None);
        assert_eq!(12, Solution::sum_numbers(root));
    }

    #[test]
    fn test_right() {
        let root = TreeNode::new(1, None, TreeNode::new(3, None, None));
        assert_eq!(13, Solution::sum_numbers(root));
    }
}
