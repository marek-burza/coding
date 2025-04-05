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
    pub fn is_symmetric(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
        match root {
            None => true,
            Some(root) => {
                let mut queue = VecDeque::new();
                queue.push_back(TreeNodePair {
                    node1: root.clone(),
                    node2: root.clone(),
                });
                while let Some(pair) = queue.pop_front() {
                    let node1 = pair.node1.borrow();
                    let node2 = pair.node2.borrow();
                    if node1.val != node2.val {
                        return false;
                    }
                    let check_left_right = null_check(&node1.left, &node2.right);
                    if check_left_right == NullCheckResult::None {
                        queue.push_back(TreeNodePair {
                            node1: node1.left.clone().unwrap().clone(),
                            node2: node2.right.clone().unwrap().clone(),
                        });
                    } else if check_left_right == NullCheckResult::Some {
                        return false;
                    }
                    let check_right_left = null_check(&node1.right, &node2.left);
                    if check_right_left == NullCheckResult::None {
                        queue.push_back(TreeNodePair {
                            node1: node1.right.clone().unwrap().clone(),
                            node2: node2.left.clone().unwrap().clone(),
                        });
                    } else if check_right_left == NullCheckResult::Some {
                        return false;
                    }
                }
                true
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_symmetric() {
        let n1a = TreeNode::new(1, None, None);
        let n1b = TreeNode::new(1, None, None);
        let n0 = TreeNode::new(0, n1a, n1b);
        assert!(Solution::is_symmetric(n0));
    }

    #[test]
    fn test_asymmetric() {
        let n1 = TreeNode::new(1, None, None);
        let n2 = TreeNode::new(2, None, None);
        let n0 = TreeNode::new(0, n1, n2);
        assert!(!Solution::is_symmetric(n0));
    }

    #[test]
    fn test_empty() {
        assert!(Solution::is_symmetric(None));
    }

    #[test]
    fn test_left() {
        let an1 = TreeNode::new(1, None, None);
        let an0 = TreeNode::new(0, an1, None);
        assert!(!Solution::is_symmetric(an0));
    }

    #[test]
    fn test_right() {
        let an1 = TreeNode::new(1, None, None);
        let an0 = TreeNode::new(0, None, an1);
        assert!(!Solution::is_symmetric(an0));
    }

    #[test]
    fn test_other() {
        let n4ll = TreeNode::new(4, None, None);
        let n5 = TreeNode::new(5, None, None);
        let n4rr = TreeNode::new(4, None, None);
        let n3l = TreeNode::new(3, n4ll, n5);
        let n3r = TreeNode::new(3, None, n4rr);
        let n2 = TreeNode::new(2, n3l, n3r);
        assert!(!Solution::is_symmetric(n2));
    }
}
