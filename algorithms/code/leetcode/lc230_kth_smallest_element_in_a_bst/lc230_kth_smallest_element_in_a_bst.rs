// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// #medium

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
    fn kth_smallest_internal(
        root: Option<Rc<RefCell<TreeNode>>>,
        k: i32,
        counter: &mut i32,
    ) -> i32 {
        if let Some(root) = root {
            let root = root.borrow();
            let result = Self::kth_smallest_internal(root.left.clone(), k, counter);
            if *counter == k {
                return result;
            }
            *counter += 1;
            if *counter == k {
                return root.val;
            }
            let result = Self::kth_smallest_internal(root.right.clone(), k, counter);
            if *counter == k {
                return result;
            }
        }
        0
    }

    pub fn kth_smallest(root: Option<Rc<RefCell<TreeNode>>>, k: i32) -> i32 {
        let mut counter = 0;
        Self::kth_smallest_internal(root, k, &mut counter)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_left() {
        let n1 = TreeNode::new(1, None, None);
        let n2 = TreeNode::new(2, n1, None);
        let n3 = TreeNode::new(3, n2, None);
        let n4 = TreeNode::new(4, n3, None);
        assert_eq!(2, Solution::kth_smallest(n4, 2));
    }

    #[test]
    fn test_right() {
        let n4 = TreeNode::new(4, None, None);
        let n3 = TreeNode::new(3, None, n4);
        let n2 = TreeNode::new(2, None, n3);
        let n1 = TreeNode::new(1, None, n2);
        assert_eq!(2, Solution::kth_smallest(n1, 2));
    }

    #[test]
    fn test_coverage() {
        let n1 = TreeNode::new(1, None, None);
        let n3 = TreeNode::new(3, None, None);
        let n2 = TreeNode::new(2, n1, n3);
        let n6 = TreeNode::new(6, None, None);
        let n5 = TreeNode::new(5, None, n6);
        let n4 = TreeNode::new(4, n2, n5);
        assert_eq!(5, Solution::kth_smallest(n4, 5));
    }
}
