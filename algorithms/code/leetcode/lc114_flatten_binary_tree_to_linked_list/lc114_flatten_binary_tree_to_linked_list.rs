// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// #medium

use std::cell::RefCell;
use std::rc::Rc;

#[derive(PartialEq, Eq, Clone, Debug)]
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
    ) -> Option<Rc<RefCell<TreeNode>>> {
        Some(Rc::new(RefCell::new(TreeNode { val, left, right })))
    }
}

pub struct Solution;

impl Solution {
    pub fn flatten(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        let mut root = (*root).clone();
        while let Some(some_root) = root {
            let mut some_root_borrowed = some_root.borrow_mut();
            if let Some(some_root_left) = some_root_borrowed.left.clone() {
                let mut node = some_root_left.clone();
                let mut node_right = node.borrow().right.clone();
                while let Some(some_node_right) = node_right {
                    node = some_node_right.clone();
                    node_right = some_node_right.borrow().right.clone();
                }
                node.borrow_mut().right = some_root_borrowed.right.clone();
                some_root_borrowed.right = some_root_borrowed.left.take();
            }
            root = some_root_borrowed.right.clone();
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn test(expected: Option<Rc<RefCell<TreeNode>>>, result: Option<Rc<RefCell<TreeNode>>>) {
        assert!(!(expected.is_none() && result.is_some()));
        assert!(!(expected.is_some() && result.is_none()));
        if let (Some(some_expected), Some(some_result)) = (expected.clone(), result.clone()) {
            assert_eq!(some_expected.borrow().val, some_result.borrow().val);
            test(
                some_expected.borrow().left.clone(),
                some_result.borrow().left.clone(),
            );
            test(
                some_expected.borrow().left.clone(),
                some_result.borrow().left.clone(),
            );
        }
    }

    #[test]
    fn test_example() {
        let n6 = TreeNode::new(6, None, None);
        let n5 = TreeNode::new(5, None, n6);
        let n4 = TreeNode::new(4, None, None);
        let n3 = TreeNode::new(3, None, None);
        let n2 = TreeNode::new(2, n3, n4);
        let mut root = TreeNode::new(1, n2, n5);
        let e6 = TreeNode::new(6, None, None);
        let e5 = TreeNode::new(5, None, e6);
        let e4 = TreeNode::new(4, None, e5);
        let e3 = TreeNode::new(3, None, e4);
        let e2 = TreeNode::new(2, None, e3);
        let expected = TreeNode::new(1, None, e2);
        Solution::flatten(&mut root);
        test(expected, root);
    }

    #[test]
    fn test_other_1() {
        let n3 = TreeNode::new(3, None, None);
        let n2 = TreeNode::new(2, n3, None);
        let mut root = TreeNode::new(1, None, n2);
        let e3 = TreeNode::new(3, None, None);
        let e2 = TreeNode::new(2, None, e3);
        let expected = TreeNode::new(1, None, e2);
        Solution::flatten(&mut root);
        test(expected, root);
    }

    #[test]
    fn test_other_2() {
        let n2 = TreeNode::new(2, None, None);
        let n4 = TreeNode::new(4, n2, None);
        let n1 = TreeNode::new(1, n4, None);
        let mut root = TreeNode::new(3, n1, None);
        let e2 = TreeNode::new(2, None, None);
        let e4 = TreeNode::new(4, None, e2);
        let e1 = TreeNode::new(1, None, e4);
        let expected = TreeNode::new(3, None, e1);
        Solution::flatten(&mut root);
        test(expected, root);
    }
}
