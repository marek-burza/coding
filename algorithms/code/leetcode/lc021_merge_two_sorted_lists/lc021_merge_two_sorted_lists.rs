// https://leetcode.com/problems/merge-two-sorted-lists/
// #linking

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}

pub struct Solution;

impl Solution {
    fn take_node(source: &mut Option<Box<ListNode>>, current: &mut Option<Box<ListNode>>) {
        current.as_mut().unwrap().next = source.take();
        *source = current.as_mut().unwrap().next.as_mut().unwrap().next.take();
    }

    pub fn merge_two_lists(
        mut list1: Option<Box<ListNode>>,
        mut list2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut handle = Some(Box::new(ListNode::new(0)));
        let mut current = &mut handle;
        while let (Some(l1_node), Some(l2_node)) = (list1.as_mut(), list2.as_mut()) {
            if l1_node.val < l2_node.val {
                Self::take_node(&mut list1, current);
            } else {
                Self::take_node(&mut list2, current);
            }
            current = &mut current.as_mut().unwrap().next;
        }
        if list1.is_some() {
            current.as_mut().unwrap().next = list1;
        } else if list2.is_some() {
            current.as_mut().unwrap().next = list2;
        }
        handle.unwrap().next
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn get_after_n_nexts(node: &mut Option<Box<ListNode>>, n: i32) -> &mut Option<Box<ListNode>> {
        let mut current = node;
        for _ in 0..n {
            current = &mut current.as_mut().unwrap().next;
        }
        current
    }

    fn check_after_n_nexts(node: &mut Option<Box<ListNode>>, n: i32, expected: i32) {
        let current = get_after_n_nexts(node, n);
        assert_eq!(current.as_ref().unwrap().val, expected);
    }

    fn shared_check(node: &mut Option<Box<ListNode>>) {
        check_after_n_nexts(node, 0, 1);
        check_after_n_nexts(node, 1, 2);
        check_after_n_nexts(node, 2, 3);
        check_after_n_nexts(node, 3, 4);
        check_after_n_nexts(node, 4, 5);
        check_after_n_nexts(node, 5, 6);
    }

    #[test]
    fn test_1_3_5_7_9_and_2_4_6() {
        let n9 = Some(Box::new(ListNode::new(9)));
        let mut n7 = Some(Box::new(ListNode::new(7)));
        n7.as_mut().unwrap().next = n9;
        let mut n5 = Some(Box::new(ListNode::new(5)));
        n5.as_mut().unwrap().next = n7;
        let mut n3 = Some(Box::new(ListNode::new(3)));
        n3.as_mut().unwrap().next = n5;
        let mut n1 = Some(Box::new(ListNode::new(1)));
        n1.as_mut().unwrap().next = n3;
        let n6 = Some(Box::new(ListNode::new(6)));
        let mut n4 = Some(Box::new(ListNode::new(4)));
        n4.as_mut().unwrap().next = n6;
        let mut n2 = Some(Box::new(ListNode::new(2)));
        n2.as_mut().unwrap().next = n4;
        let mut result = Solution::merge_two_lists(n1, n2);
        shared_check(&mut result);
        check_after_n_nexts(&mut result, 6, 7);
        check_after_n_nexts(&mut result, 7, 9);
    }

    #[test]
    fn test_1_2_3_and_4_5_6() {
        let n3 = Some(Box::new(ListNode { val: 3, next: None }));
        let n2 = Some(Box::new(ListNode { val: 2, next: n3 }));
        let n1 = Some(Box::new(ListNode { val: 1, next: n2 }));
        let n6 = Some(Box::new(ListNode { val: 6, next: None }));
        let n5 = Some(Box::new(ListNode { val: 5, next: n6 }));
        let n4 = Some(Box::new(ListNode { val: 4, next: n5 }));
        let mut result = Solution::merge_two_lists(n1, n4);
        shared_check(&mut result);
        assert_eq!(*get_after_n_nexts(&mut result, 6), None);
    }
}
