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
    pub fn level_order_bottom(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut result: Vec<Vec<i32>> = Vec::new();
        let mut current: Vec<Rc<RefCell<TreeNode>>> = Vec::new();
        if let Some(root) = root {
            current.push(root);
        }
        while !current.is_empty() {
            let mut level: Vec<i32> = Vec::new();
            let mut future: Vec<Rc<RefCell<TreeNode>>> = Vec::new();
            for node in &current {
                let node = node.borrow();
                level.push(node.val);
                if let Some(left) = &node.left {
                    future.push(left.clone());
                }
                if let Some(right) = &node.right {
                    future.push(right.clone());
                }
            }
            result.push(level);
            current = future;
        }
        result.reverse();
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert!(Solution::level_order_bottom(None).is_empty());
    }

    #[test]
    fn test_example() {
        let n15 = TreeNode::new(15, None, None);
        let n7 = TreeNode::new(7, None, None);
        let n9 = TreeNode::new(9, None, None);
        let n20 = TreeNode::new(20, n15, n7);
        let n3 = TreeNode::new(3, n9, n20);
        let result = Solution::level_order_bottom(n3);
        let expected = vec![vec![15, 7], vec![9, 20], vec![3]];
        assert_eq!(expected, result);
    }
}
