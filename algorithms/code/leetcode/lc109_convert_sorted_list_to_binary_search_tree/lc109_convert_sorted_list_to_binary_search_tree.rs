// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
// #medium

use std::cell::RefCell;
use std::rc::Rc;

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

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
    fn list_to_vector(mut list: Option<Box<ListNode>>) -> Vec<i32> {
        let mut vector = Vec::new();
        while let Some(node) = list {
            vector.push(node.val);
            list = node.next;
        }
        vector
    }

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

    pub fn sorted_list_to_bst(list: Option<Box<ListNode>>) -> Option<Rc<RefCell<TreeNode>>> {
        let vector = Self::list_to_vector(list);
        Self::generate(&vector, 0, vector.len())
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn vector_to_list(vector: Vec<i32>) -> Option<Box<ListNode>> {
        let mut list: Option<Box<ListNode>> = None;
        for &value in vector.iter().rev() {
            let node = Box::new(ListNode {
                val: value,
                next: list,
            });
            list = Some(node);
        }
        list
    }

    fn depth(
        root: &Option<Rc<RefCell<TreeNode>>>,
        level: i32,
        depths_min: &mut i32,
        depths_max: &mut i32,
    ) {
        if let Some(node) = root {
            let node = node.borrow();
            depth(&node.left, level + 1, depths_min, depths_max);
            depth(&node.right, level + 1, depths_min, depths_max);
        } else {
            *depths_max = (*depths_max).max(level);
            *depths_min = (*depths_min).min(level);
        }
    }

    fn test(
        root: &Option<Rc<RefCell<TreeNode>>>,
        mut list: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        if let Some(some_root) = root {
            let root = some_root.borrow();
            list = test(&root.left, list);
            assert_eq!(list.as_ref().map(|node| node.val), Some(root.val));
            list = list.unwrap().next;
            list = test(&root.right, list);
        }
        list
    }

    #[test]
    fn test_bigger() {
        let list = vector_to_list((-999..=15340).collect());
        let root = Solution::sorted_list_to_bst(list);
        let mut depths_min = i32::MAX;
        let mut depths_max = i32::MIN;
        depth(&root, 0, &mut depths_min, &mut depths_max);
        let list = vector_to_list((-999..=15340).collect());
        assert!(depths_max - depths_min < 2);
        assert!(test(&root, list).is_none());
    }
}
