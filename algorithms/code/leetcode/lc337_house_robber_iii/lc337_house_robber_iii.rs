// https://leetcode.com/problems/house-robber-iii/
// #medium

use std::cell::RefCell;
use std::collections::HashMap;
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
    fn rob_internal(
        root: Option<Rc<RefCell<TreeNode>>>,
        cache: &mut HashMap<*const RefCell<TreeNode>, i32>,
    ) -> i32 {
        if let Some(root) = root {
            let root_ptr = Rc::as_ptr(&root);
            if let Some(&cached) = cache.get(&root_ptr) {
                return cached;
            }
            let root = root.borrow();
            let without = Self::rob_internal(root.left.clone(), cache)
                + Self::rob_internal(root.right.clone(), cache);
            let mut with = root.val;
            if let Some(root_left) = &root.left {
                let root_left = root_left.borrow();
                with += Self::rob_internal(root_left.left.clone(), cache)
                    + Self::rob_internal(root_left.right.clone(), cache);
            }
            if let Some(root_right) = &root.right {
                let root_right = root_right.borrow();
                with += Self::rob_internal(root_right.left.clone(), cache)
                    + Self::rob_internal(root_right.right.clone(), cache);
            }
            let maximum = with.max(without);
            cache.insert(root_ptr, maximum);
            maximum
        } else {
            0
        }
    }

    pub fn rob(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut cache: HashMap<*const RefCell<TreeNode>, i32> = HashMap::new();
        Self::rob_internal(root, &mut cache)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let lr3 = TreeNode::new(3, None, None);
        let rr1 = TreeNode::new(1, None, None);
        let l2 = TreeNode::new(2, None, lr3);
        let r3 = TreeNode::new(3, None, rr1);
        let t3 = TreeNode::new(3, l2, r3);
        assert_eq!(7, Solution::rob(t3));
    }

    #[test]
    fn test_example_2() {
        let ll1 = TreeNode::new(1, None, None);
        let lr3 = TreeNode::new(3, None, None);
        let rr1 = TreeNode::new(1, None, None);
        let l4 = TreeNode::new(4, ll1, lr3);
        let r5 = TreeNode::new(5, None, rr1);
        let t3 = TreeNode::new(3, l4, r5);
        assert_eq!(9, Solution::rob(t3));
    }
}
