// https://leetcode.com/problems/same-tree/
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

struct TreeNodePair {
    node1: Rc<RefCell<TreeNode>>,
    node2: Rc<RefCell<TreeNode>>,
}

#[derive(PartialEq)]
enum NullCheckResult {
    All,
    Some,
    None,
}

fn null_check(
    tree1: &Option<Rc<RefCell<TreeNode>>>,
    tree2: &Option<Rc<RefCell<TreeNode>>>,
) -> NullCheckResult {
    match (tree1, tree2) {
        (None, None) => NullCheckResult::All,
        (Some(_), Some(_)) => NullCheckResult::None,
        _ => NullCheckResult::Some,
    }
}

pub struct Solution;

impl Solution {
    pub fn is_same_tree(
        tree1: Option<Rc<RefCell<TreeNode>>>,
        tree2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let check = null_check(&tree1, &tree2);
        if check != NullCheckResult::None {
            return check == NullCheckResult::All;
        }
        let mut queue = VecDeque::new();
        queue.push_back(TreeNodePair {
            node1: tree1.unwrap(),
            node2: tree2.unwrap(),
        });
        while let Some(pair) = queue.pop_front() {
            let node1 = pair.node1.borrow();
            let node2 = pair.node2.borrow();
            if node1.val != node2.val {
                return false;
            }
            let check_left = null_check(&node1.left, &node2.left);
            if check_left == NullCheckResult::None {
                queue.push_back(TreeNodePair {
                    node1: node1.left.as_ref().unwrap().clone(),
                    node2: node2.left.as_ref().unwrap().clone(),
                });
            } else if check_left == NullCheckResult::Some {
                return false;
            }
            let check_right = null_check(&node1.right, &node2.right);
            if check_right == NullCheckResult::None {
                queue.push_back(TreeNodePair {
                    node1: node1.right.as_ref().unwrap().clone(),
                    node2: node2.right.as_ref().unwrap().clone(),
                });
            } else if check_right == NullCheckResult::Some {
                return false;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_different() {
        let an0_left = TreeNode::new(1, None, None);
        let an0_right = TreeNode::new(2, None, None);
        let an0 = TreeNode::new(0, an0_left, an0_right);
        let bn0_left = TreeNode::new(1, None, None);
        let bn0_right = TreeNode::new(-2, None, None);
        let bn0 = TreeNode::new(0, bn0_left, bn0_right);
        assert!(!Solution::is_same_tree(an0, bn0));
    }

    #[test]
    fn test_same() {
        let an0_left = TreeNode::new(1, None, None);
        let an0_right = TreeNode::new(2, None, None);
        let an0 = TreeNode::new(0, an0_left, an0_right);
        let bn0_left = TreeNode::new(1, None, None);
        let bn0_right = TreeNode::new(2, None, None);
        let bn0 = TreeNode::new(0, bn0_left, bn0_right);
        assert!(Solution::is_same_tree(an0, bn0));
    }

    #[test]
    fn test_one_empty() {
        let tree = TreeNode::new(0, None, None);
        assert!(!Solution::is_same_tree(tree.clone(), None));
        assert!(!Solution::is_same_tree(None, tree));
    }

    #[test]
    fn test_both_empty() {
        assert!(Solution::is_same_tree(None, None));
    }

    #[test]
    fn test_left() {
        let an1 = TreeNode::new(1, None, None);
        let an0 = TreeNode::new(0, an1, None);
        let bn0 = TreeNode::new(0, None, None);
        assert!(!Solution::is_same_tree(an0, bn0));
    }

    #[test]
    fn test_right() {
        let an1 = TreeNode::new(1, None, None);
        let an0 = TreeNode::new(0, None, an1);
        let bn0 = TreeNode::new(0, None, None);
        assert!(!Solution::is_same_tree(an0, bn0));
    }
}
