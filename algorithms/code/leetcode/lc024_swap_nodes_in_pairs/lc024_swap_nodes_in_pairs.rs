// https://leetcode.com/problems/swap-nodes-in-pairs/
// #linking

// Definition for singly-linked list.
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
    pub fn swap_pairs(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Some(Box::new(ListNode::new(0)));
        let mut current = &mut dummy;
        current.as_mut().unwrap().next = head;
        while let Some(mut first) = current.as_mut().unwrap().next.take() {
            if let Some(mut second) = first.next.take() {
                first.next = second.next.take();
                second.next = Some(first);
                current.as_mut().unwrap().next = Some(second);
                current = &mut current.as_mut().unwrap().next.as_mut().unwrap().next;
            } else {
                current.as_mut().unwrap().next = Some(first);
                break;
            }
        }
        dummy.unwrap().next
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn check(head: Option<Box<ListNode>>, expected: &[i32]) {
        let mut result = Solution::swap_pairs(head);
        for value in expected {
            assert_ne!(result, None);
            assert_eq!(result.as_ref().unwrap().val, *value);
            result = result.unwrap().next;
        }
    }

    #[test]
    fn test_example() {
        let mut n1 = Some(Box::new(ListNode::new(1)));
        let mut n2 = Some(Box::new(ListNode::new(2)));
        let mut n3 = Some(Box::new(ListNode::new(3)));
        let n4 = Some(Box::new(ListNode::new(4)));
        n3.as_mut().unwrap().next = n4;
        n2.as_mut().unwrap().next = n3;
        n1.as_mut().unwrap().next = n2;
        let expected = vec![2, 1, 4, 3];
        check(n1, &expected);
    }

    #[test]
    fn test_example_impaired() {
        let mut n1 = Some(Box::new(ListNode::new(1)));
        let mut n2 = Some(Box::new(ListNode::new(2)));
        let n3 = Some(Box::new(ListNode::new(3)));
        n2.as_mut().unwrap().next = n3;
        n1.as_mut().unwrap().next = n2;
        let expected = vec![2, 1, 3];
        check(n1, &expected);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(Solution::swap_pairs(None), None);
    }
}
