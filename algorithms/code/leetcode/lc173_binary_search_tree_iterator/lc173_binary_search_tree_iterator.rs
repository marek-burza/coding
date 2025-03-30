// https://leetcode.com/problems/binary-search-tree-iterator/
// #medium

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

pub struct BSTIterator {
    stack: Vec<Rc<RefCell<TreeNode>>>,
}

#[allow(clippy::should_implement_trait)]
impl BSTIterator {
    pub fn new(mut root: Option<Rc<RefCell<TreeNode>>>) -> Self {
        let mut stack = Vec::new();
        while let Some(some_root) = root {
            stack.push(some_root.clone());
            root = some_root.borrow().left.clone();
        }
        BSTIterator { stack }
    }

    pub fn has_next(&self) -> bool {
        !self.stack.is_empty()
    }

    pub fn next(&mut self) -> i32 {
        let mut node = self.stack.pop();
        let result = node.clone().unwrap().borrow().val;
        node = node.unwrap().borrow().right.clone();
        while let Some(some_node) = node {
            self.stack.push(some_node.clone());
            node = some_node.borrow().left.clone();
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n1 = TreeNode::new(1, None, None);
        let n3 = TreeNode::new(3, None, None);
        let n5 = TreeNode::new(5, None, None);
        let n4 = TreeNode::new(4, n3, n5);
        let n2 = TreeNode::new(2, n1, n4);
        let n7 = TreeNode::new(7, None, None);
        let n8 = TreeNode::new(8, n7, None);
        let n9 = TreeNode::new(9, n8, None);
        let n11 = TreeNode::new(11, None, None);
        let n10 = TreeNode::new(10, n9, n11);
        let n6 = TreeNode::new(6, n2, n10);
        let mut iterator = BSTIterator::new(n6);
        let mut i = 1;
        while iterator.has_next() {
            assert_eq!(i, iterator.next());
            i += 1;
        }
    }
}
