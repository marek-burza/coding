// https://leetcode.com/problems/path-sum/
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
    pub fn has_path_sum(root: Option<Rc<RefCell<TreeNode>>>, sum: i32) -> bool {
        match root {
            Some(node) => {
                let node = node.borrow();
                let reduced = sum - node.val;
                if node.left.is_none() && node.right.is_none() {
                    return reduced == 0;
                }
                Self::has_path_sum(node.left.clone(), reduced)
                    || Self::has_path_sum(node.right.clone(), reduced)
            }
            None => false,
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n1 = TreeNode::new(1, None, None);
        let n2 = TreeNode::new(2, None, None);
        let n7 = TreeNode::new(7, None, None);
        let n13 = TreeNode::new(13, None, None);
        let n4a = TreeNode::new(4, None, n1);
        let n8 = TreeNode::new(8, n13, n4a);
        let n11 = TreeNode::new(11, n7, n2);
        let n4b = TreeNode::new(4, n11, None);
        let n5 = TreeNode::new(5, n4b, n8);
        assert!(Solution::has_path_sum(n5, 22));
    }

    #[test]
    fn test_left_bend() {
        let right = TreeNode::new(1, None, None);
        let left = TreeNode::new(2, None, right);
        let root = TreeNode::new(3, left, None);
        assert!(Solution::has_path_sum(root, 6));
    }

    #[test]
    fn test_right_bend() {
        let left = TreeNode::new(1, None, None);
        let right = TreeNode::new(2, left, None);
        let root = TreeNode::new(3, None, right);
        assert!(Solution::has_path_sum(root, 6));
    }

    #[test]
    fn test_no_path() {
        let left = TreeNode::new(0, None, None);
        let right = TreeNode::new(0, None, None);
        let root = TreeNode::new(0, left, right);
        assert!(!Solution::has_path_sum(root, 6));
    }

    #[test]
    fn test_nothing() {
        assert!(!Solution::has_path_sum(None, 0));
    }
}
