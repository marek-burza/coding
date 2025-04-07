// https://leetcode.com/problems/maximum-depth-of-binary-tree/
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
    pub fn max_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        match root {
            None => 0,
            Some(root) => {
                let root = root.borrow();
                let left_depth = Self::max_depth(root.left.clone());
                let right_depth = Self::max_depth(root.right.clone());
                1 + left_depth.max(right_depth)
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n3 = TreeNode::new(3, None, None);
        let n1 = TreeNode::new(1, None, n3);
        let n2 = TreeNode::new(2, None, None);
        let n0 = TreeNode::new(0, n1, n2);
        assert_eq!(Solution::max_depth(n0), 3);
    }
}
