// https://leetcode.com/problems/binary-tree-paths/
// #easy

use std::cell::RefCell;
use std::collections::VecDeque;
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

struct AnnotatedNode {
    node: Rc<RefCell<TreeNode>>,
    depth: i32,
}

pub struct Solution;

impl Solution {
    pub fn min_depth(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if root.is_none() {
            return 0;
        }
        let mut queue = VecDeque::new();
        queue.push_back(AnnotatedNode {
            node: root.unwrap(),
            depth: 1,
        });
        let mut depth: i32 = 0;
        while let Some(annotated) = queue.pop_front() {
            let annotated_node = annotated.node.borrow();
            let left = annotated_node.left.clone();
            let right = annotated_node.right.clone();
            if left.is_none() && right.is_none() {
                depth = annotated.depth;
                break;
            }
            if let Some(left_node) = left {
                queue.push_back(AnnotatedNode {
                    node: left_node,
                    depth: annotated.depth + 1,
                });
            }
            if let Some(right_node) = right {
                queue.push_back(AnnotatedNode {
                    node: right_node,
                    depth: annotated.depth + 1,
                });
            }
        }
        depth
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n15 = TreeNode::new(15, None, None);
        let n7 = TreeNode::new(7, None, None);
        let n9 = TreeNode::new(9, None, None);
        let n20 = TreeNode::new(20, n15, n7);
        let n3 = TreeNode::new(3, n9, n20);
        assert_eq!(2, Solution::min_depth(n3));
    }

    #[test]
    fn test_left_nothing() {
        let right = TreeNode::new(7, None, None);
        let root = TreeNode::new(3, None, right);
        assert_eq!(2, Solution::min_depth(root));
    }

    #[test]
    fn test_right_nothing() {
        let left = TreeNode::new(7, None, None);
        let root = TreeNode::new(3, left, None);
        assert_eq!(2, Solution::min_depth(root));
    }

    #[test]
    fn test_nothing() {
        assert_eq!(0, Solution::min_depth(None));
    }
}
