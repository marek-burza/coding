// https://leetcode.com/problems/binary-tree-paths/
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
    pub fn binary_tree_paths_helper(
        root: Rc<RefCell<TreeNode>>,
        prefix_value: String,
        result: &mut Vec<String>,
    ) {
        let root = root.borrow();
        let mut prefix = prefix_value;
        prefix += &if prefix.is_empty() {
            format!("{}", root.val)
        } else {
            format!("->{}", root.val)
        };
        if root.left.is_none() && root.right.is_none() {
            result.push(prefix);
        } else {
            if let Some(left) = root.left.clone() {
                Solution::binary_tree_paths_helper(left, prefix.clone(), result);
            }
            if let Some(right) = root.right.clone() {
                Solution::binary_tree_paths_helper(right, prefix.clone(), result);
            }
        }
    }

    pub fn binary_tree_paths(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<String> {
        let mut result = Vec::new();
        if let Some(root) = root {
            Solution::binary_tree_paths_helper(root, String::new(), &mut result);
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn generic(tree: Option<Rc<RefCell<TreeNode>>>, expected: &[&str]) {
        let mut result = Solution::binary_tree_paths(tree);
        result.sort();
        let mut expected: Vec<String> = expected.iter().map(|s| s.to_string()).collect();
        expected.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_example() {
        let n5 = TreeNode::new(5, None, None);
        let n2 = TreeNode::new(2, None, n5);
        let n3 = TreeNode::new(3, None, None);
        let n1 = TreeNode::new(1, n2, n3);
        let expected = ["1->2->5", "1->3"];
        generic(n1, &expected);
    }

    #[test]
    fn test_example_mirrored() {
        let n5 = TreeNode::new(5, None, None);
        let n2 = TreeNode::new(2, n5, None);
        let n3 = TreeNode::new(3, None, None);
        let n1 = TreeNode::new(1, n3, n2);
        let expected = ["1->2->5", "1->3"];
        generic(n1, &expected);
    }

    #[test]
    fn test_nothing() {
        let result = Solution::binary_tree_paths(None);
        assert!(result.is_empty());
    }
}
