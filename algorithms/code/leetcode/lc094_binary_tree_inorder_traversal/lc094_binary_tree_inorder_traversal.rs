// https://leetcode.com/problems/binary-tree-inorder-traversal/
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
    fn preorder_traversal(root: Option<Rc<RefCell<TreeNode>>>, result: &mut Vec<i32>) {
        if let Some(root) = root {
            let root = root.borrow();
            Self::preorder_traversal(root.left.clone(), result);
            result.push(root.val);
            Self::preorder_traversal(root.right.clone(), result);
        }
    }

    pub fn inorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut result = Vec::new();
        Self::preorder_traversal(root, &mut result);
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let node3 = TreeNode::new(3, None, None);
        let node2 = TreeNode::new(2, node3, None);
        let node1 = TreeNode::new(1, None, node2);
        let result = Solution::inorder_traversal(node1);
        let expected = vec![1, 3, 2];
        assert_eq!(result, expected);
    }
}
