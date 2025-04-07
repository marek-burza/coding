// https://leetcode.com/problems/balanced-binary-tree/
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
    fn balanced_height(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(root) = root {
            let root = root.borrow();
            let left = Self::balanced_height(root.left.clone());
            if left == -1 {
                return -1;
            }
            let right = Self::balanced_height(root.right.clone());
            if right == -1 {
                return -1;
            }
            if (left - right).abs() > 1 {
                -1
            } else {
                1 + left.max(right)
            }
        } else {
            0
        }
    }

    pub fn is_balanced(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        Self::balanced_height(root) != -1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_balanced() {
        let left = TreeNode::new(
            2,
            TreeNode::new(1, None, None),
            TreeNode::new(3, None, None),
        );
        let right = TreeNode::new(
            6,
            TreeNode::new(5, None, None),
            TreeNode::new(7, None, None),
        );
        let root = TreeNode::new(4, left, right);
        assert!(Solution::is_balanced(root));
    }

    #[test]
    fn test_imbalanced_right() {
        let right = TreeNode::new(
            6,
            TreeNode::new(5, None, None),
            TreeNode::new(7, None, None),
        );
        let root = TreeNode::new(4, None, right);
        assert!(!Solution::is_balanced(root));
    }

    #[test]
    fn test_imbalanced_left() {
        let left = TreeNode::new(
            2,
            TreeNode::new(1, None, None),
            TreeNode::new(3, None, None),
        );
        let root = TreeNode::new(4, left, None);
        assert!(!Solution::is_balanced(root));
    }

    #[test]
    fn test_imbalanced_deep_left() {
        let left = TreeNode::new(
            2,
            TreeNode::new(1, TreeNode::new(3, None, None), None),
            None,
        );
        let right = TreeNode::new(
            6,
            TreeNode::new(5, None, None),
            TreeNode::new(7, None, None),
        );
        let root = TreeNode::new(4, left, right);
        assert!(!Solution::is_balanced(root));
    }

    #[test]
    fn test_imbalanced_deep_right() {
        let left = TreeNode::new(
            2,
            TreeNode::new(1, None, None),
            TreeNode::new(3, None, None),
        );
        let right = TreeNode::new(
            6,
            TreeNode::new(5, None, TreeNode::new(7, None, None)),
            None,
        );
        let root = TreeNode::new(4, left, right);
        assert!(!Solution::is_balanced(root));
    }
}
