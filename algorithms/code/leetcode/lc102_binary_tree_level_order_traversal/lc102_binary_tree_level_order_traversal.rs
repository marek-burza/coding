// https://leetcode.com/problems/binary-tree-level-order-traversal/
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
    pub fn new(val: i32) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(TreeNode {
            val,
            left: None,
            right: None,
        }))
    }
}

struct AnnotatedNode {
    node: Rc<RefCell<TreeNode>>,
    depth: i32,
}

pub struct Solution;

impl Solution {
    pub fn level_order(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<Vec<i32>> {
        let mut result: Vec<Vec<i32>> = Vec::new();
        let mut queue: Vec<AnnotatedNode> = Vec::new();
        if let Some(node) = root {
            queue.push(AnnotatedNode { node, depth: 1 });
        }
        let mut depth = 0;
        while !queue.is_empty() {
            let annotated = queue.remove(0);
            if depth != annotated.depth {
                depth = annotated.depth;
                result.push(Vec::new());
            }
            let annotated_node = annotated.node.borrow();
            if let Some(line) = result.last_mut() {
                line.push(annotated_node.val);
            }
            if let Some(left) = &annotated_node.left {
                queue.push(AnnotatedNode {
                    node: Rc::clone(left),
                    depth: annotated.depth + 1,
                });
            }
            if let Some(right) = &annotated_node.right {
                queue.push(AnnotatedNode {
                    node: Rc::clone(right),
                    depth: annotated.depth + 1,
                });
            }
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let n3 = TreeNode::new(3);
        let n7 = TreeNode::new(7);
        let n9 = TreeNode::new(9);
        let n15 = TreeNode::new(15);
        let n20 = TreeNode::new(20);
        n3.borrow_mut().left = Some(Rc::clone(&n9));
        n3.borrow_mut().right = Some(Rc::clone(&n20));
        n20.borrow_mut().left = Some(Rc::clone(&n15));
        n20.borrow_mut().right = Some(Rc::clone(&n7));
        let expected = vec![vec![3], vec![9, 20], vec![15, 7]];
        let result = Solution::level_order(Some(n3));
        assert_eq!(expected, result);
    }

    #[test]
    fn test_nothing() {
        let result = Solution::level_order(None);
        assert!(result.is_empty());
    }
}
