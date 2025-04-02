// https://leetcode.com/problems/invert-binary-tree/
// #easy

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
    pub fn invert_tree(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        if let Some(some_root) = root.clone() {
            let mut root_borrowed = some_root.borrow_mut();
            let left = root_borrowed.left.clone();
            let right = root_borrowed.right.clone();
            root_borrowed.left = right;
            root_borrowed.right = left;
            Self::invert_tree(root_borrowed.left.clone());
            Self::invert_tree(root_borrowed.right.clone());
            drop(root_borrowed);
        }
        root
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
        let n1 = TreeNode::new(1, None, None);
        let n3 = TreeNode::new(3, None, None);
        let n6 = TreeNode::new(6, None, None);
        let n9 = TreeNode::new(9, None, None);
        let n2 = TreeNode::new(2, n1, n3);
        let n7 = TreeNode::new(7, n6, n9);
        let n4 = TreeNode::new(4, n2, n7);
        let e1 = TreeNode::new(1, None, None);
        let e3 = TreeNode::new(3, None, None);
        let e6 = TreeNode::new(6, None, None);
        let e9 = TreeNode::new(9, None, None);
        let e2 = TreeNode::new(2, e3, e1);
        let e7 = TreeNode::new(7, e9, e6);
        let e4 = TreeNode::new(4, e7, e2);
        let inverted = Solution::invert_tree(n4.clone());
        test(e4, inverted);
    }
}
