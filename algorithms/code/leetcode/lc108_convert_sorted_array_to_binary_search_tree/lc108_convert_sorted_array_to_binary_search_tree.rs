// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
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
    fn generate(vector: &[i32], head: usize, tail: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if head >= tail {
            return None;
        }
        let length = tail - head;
        let half = head + (length >> 1);
        let root_left = Self::generate(vector, head, half);
        let root_right = Self::generate(vector, half + 1, tail);
        TreeNode::new(vector[half], root_left, root_right)
    }

    pub fn sorted_array_to_bst(nums: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        Self::generate(&nums, 0, nums.len())
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::cmp::{max, min};
    use std::collections::VecDeque;

    fn find_extreme<C>(root: &Option<Rc<RefCell<TreeNode>>>, init: i32, relation: C) -> i32
    where
        C: Fn(i32, i32) -> i32,
    {
        if root.is_none() {
            return 0;
        }
        let mut nodes = VecDeque::new();
        nodes.push_back(root.clone().unwrap());
        let mut levels = VecDeque::new();
        levels.push_back(1);
        let mut extremum = init;
        while let Some(node) = nodes.pop_front() {
            let level = levels.pop_front().unwrap();
            let node = node.borrow();
            if node.left.is_none() && node.right.is_none() {
                extremum = relation(level, extremum);
            } else {
                if let Some(left) = &node.left {
                    nodes.push_back(left.clone());
                    levels.push_back(level + 1);
                }
                if let Some(right) = &node.right {
                    nodes.push_back(right.clone());
                    levels.push_back(level + 1);
                }
            }
        }
        extremum
    }

    fn min_height(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        find_extreme(root, i32::MAX, min)
    }

    fn max_height(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        find_extreme(root, i32::MIN, max)
    }

    fn reconstruct(root: &Option<Rc<RefCell<TreeNode>>>, list: &mut Vec<i32>) {
        if let Some(node) = root {
            let node = node.borrow();
            reconstruct(&node.left, list);
            list.push(node.val);
            reconstruct(&node.right, list);
        }
    }

    fn is_bst(root: &Option<Rc<RefCell<TreeNode>>>) -> bool {
        let mut list = Vec::new();
        reconstruct(root, &mut list);
        let mut previous = i32::MIN;
        for &value in &list {
            if previous > value {
                return false;
            }
            previous = value;
        }
        true
    }

    #[test]
    fn test_self() {
        assert_eq!(0, min_height(&None));
        assert!(!is_bst(&TreeNode::new(
            0,
            TreeNode::new(10, None, None),
            None,
        )));
    }

    #[test]
    fn test_empty() {
        assert!(Solution::sorted_array_to_bst(vec![]).is_none());
    }

    #[test]
    fn test_depth_and_ordering() {
        let nums: Vec<i32> = vec![1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
        let root = Solution::sorted_array_to_bst(nums);
        let difference = max_height(&root) - min_height(&root);
        assert!(0 <= difference && difference <= 1);
        assert!(is_bst(&root));
    }
}
