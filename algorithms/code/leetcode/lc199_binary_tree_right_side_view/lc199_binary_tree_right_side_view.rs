// https://leetcode.com/problems/binary-tree-right-side-view/
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
    fn right_side_view_helper(
        root: Option<Rc<RefCell<TreeNode>>>,
        level_value: usize,
        list: &mut Vec<i32>,
    ) {
        if let Some(root) = root {
            let root = root.borrow();
            let level = level_value + 1;
            if level > list.len() {
                list.push(root.val);
            }
            Self::right_side_view_helper(root.right.clone(), level, list);
            Self::right_side_view_helper(root.left.clone(), level, list);
        }
    }

    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut list = Vec::new();
        Self::right_side_view_helper(root, 0, &mut list);
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n6 = TreeNode::new(6, None, None);
        let n5 = TreeNode::new(5, n6, None);
        let n4 = TreeNode::new(4, None, None);
        let n3 = TreeNode::new(3, None, n4);
        let n2 = TreeNode::new(2, None, n5);
        let n1 = TreeNode::new(1, n2, n3);
        let result = Solution::right_side_view(n1);
        let expected = vec![1, 3, 4, 6];
        assert_eq!(result, expected);
    }
}
