// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
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
    fn equal<T>(a: &Option<Rc<RefCell<T>>>, b: &Option<Rc<RefCell<T>>>) -> bool {
        match (a, b) {
            (Some(a), Some(b)) => Rc::ptr_eq(a, b),
            (None, None) => true,
            _ => false,
        }
    }

    pub fn lowest_common_ancestor(
        root: Option<Rc<RefCell<TreeNode>>>,
        p: Option<Rc<RefCell<TreeNode>>>,
        q: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if let Some(root) = root {
            let left = if !Self::equal(&root.borrow().left, &None) {
                Self::lowest_common_ancestor(root.borrow().left.clone(), p.clone(), q.clone())
            } else {
                None
            };
            let right = if !Self::equal(&root.borrow().right, &None) {
                Self::lowest_common_ancestor(root.borrow().right.clone(), p.clone(), q.clone())
            } else {
                None
            };
            if !Self::equal(&left, &None) && !Self::equal(&left, &p) && !Self::equal(&left, &q) {
                return left;
            }
            if !Self::equal(&right, &None) && !Self::equal(&right, &p) && !Self::equal(&right, &q) {
                return right;
            }
            let got_p = Self::equal(&Some(root.clone()), &p)
                || Self::equal(&left, &p)
                || Self::equal(&right, &p);
            let got_q = Self::equal(&Some(root.clone()), &q)
                || Self::equal(&left, &q)
                || Self::equal(&right, &q);
            if got_p && got_q {
                return Some(root.clone());
            }
            if got_p {
                return p;
            }
            if got_q {
                return q;
            }
            None
        } else {
            None
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_nothing() {
        assert!(Solution::lowest_common_ancestor(None, None, None).is_none());
    }

    #[test]
    fn test_example() {
        let n3 = TreeNode::new(3, None, None);
        let n5 = TreeNode::new(5, None, None);
        let n4 = TreeNode::new(4, n3.clone(), n5.clone());
        let n0 = TreeNode::new(0, None, None);
        let n2 = TreeNode::new(2, n0.clone(), n4.clone());
        let n7 = TreeNode::new(7, None, None);
        let n9 = TreeNode::new(9, None, None);
        let n8 = TreeNode::new(8, n7.clone(), n9.clone());
        let n6 = TreeNode::new(6, n2.clone(), n8.clone());
        assert_eq!(
            6,
            Solution::lowest_common_ancestor(n6.clone(), n2.clone(), n8.clone())
                .unwrap()
                .borrow()
                .val,
        );
        assert_eq!(
            2,
            Solution::lowest_common_ancestor(n2.clone(), n2.clone(), n4.clone())
                .unwrap()
                .borrow()
                .val,
        );
    }

    #[test]
    fn test_example_1() {
        let n1 = TreeNode::new(1, None, None);
        let n2 = TreeNode::new(2, n1.clone(), None);
        let n4 = TreeNode::new(4, None, None);
        let n3 = TreeNode::new(3, n2.clone(), n4.clone());
        let n6 = TreeNode::new(6, None, None);
        let n5 = TreeNode::new(5, n3.clone(), n6.clone());
        assert_eq!(
            3,
            Solution::lowest_common_ancestor(n5.clone(), n1.clone(), n4.clone())
                .unwrap()
                .borrow()
                .val,
        );
    }

    #[test]
    fn test_example_2() {
        let n3 = TreeNode::new(3, None, None);
        let n5 = TreeNode::new(5, None, None);
        let n4 = TreeNode::new(4, n3.clone(), n5.clone());
        let n7 = TreeNode::new(7, None, None);
        let n2 = TreeNode::new(2, n7.clone(), n4.clone());
        let n9 = TreeNode::new(9, None, None);
        let n8 = TreeNode::new(8, None, n9.clone());
        let n6 = TreeNode::new(6, n2.clone(), n8.clone());
        assert_eq!(
            4,
            Solution::lowest_common_ancestor(n6.clone(), n3.clone(), n5.clone())
                .unwrap()
                .borrow()
                .val,
        );
    }

    #[test]
    fn test_example_3() {
        let n1 = TreeNode::new(1, None, None);
        let n2 = TreeNode::new(2, n1.clone(), None);
        assert_eq!(
            2,
            Solution::lowest_common_ancestor(n2.clone(), n2.clone(), n1.clone())
                .unwrap()
                .borrow()
                .val,
        );
    }

    #[test]
    fn test_example_4() {
        let n2 = TreeNode::new(2, None, None);
        let n3 = TreeNode::new(3, None, n2.clone());
        assert_eq!(
            2,
            Solution::lowest_common_ancestor(n2.clone(), n3.clone(), n2.clone())
                .unwrap()
                .borrow()
                .val,
        );
    }
}
