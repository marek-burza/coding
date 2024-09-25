// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// #linking

// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    fn length(head: &mut Option<Box<ListNode>>) -> i32 {
        let mut current = head;
        let mut count = 0;
        while let Some(ref mut node) = current {
            count += 1;
            current = &mut node.next;
        }
        count
    }

    fn nth_node(head: &mut Option<Box<ListNode>>, n: i32) -> Option<&mut Box<ListNode>> {
        let mut current = head.as_mut();
        for _ in 0..n {
            match current {
                Some(node) => current = node.next.as_mut(),
                None => return None,
            }
        }
        current
    }

    pub fn remove_nth_from_end(mut head: Option<Box<ListNode>>, n: i32) -> Option<Box<ListNode>> {
        // Translate the index number from counted
        // from the back to a one counted from the front
        let count = Self::length(&mut head);
        let reverted = count - n;
        // Do the deletion
        if reverted == 0 {
            return head.and_then(|node| node.next);
        }
        let node_before = Self::nth_node(&mut head, reverted - 1);
        if let Some(node_before) = node_before {
            node_before.next = node_before
                .next
                .as_mut()
                .and_then(|removed_node| removed_node.next.take());
        }
        head
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_2_and_1_1() {
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: None }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n2 }));
        let mut n = n1;
        assert_eq!(n.clone().unwrap().val, 1);
        assert_eq!(n.clone().unwrap().next.unwrap().val, 2);
        n = Solution::remove_nth_from_end(n, 1);
        assert_eq!(n.clone().unwrap().val, 1);
        n = Solution::remove_nth_from_end(n, 1);
        assert_eq!(n, None);
    }

    #[test]
    fn test_none_0() {
        let head = None;
        let result = Solution::remove_nth_from_end(head, 0);
        assert_eq!(result, None);
    }

    #[test]
    fn test_1_2_3_and_2() {
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 3, next: None }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n2 }));
        let mut n = n1;
        n = Solution::remove_nth_from_end(n, 2);
        assert_eq!(n.clone().unwrap().val, 1);
        assert_eq!(n.clone().unwrap().next.unwrap().val, 3);
    }

    #[test]
    fn test_1_2_3_and_minus2() {
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 3, next: None }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n2 }));
        let mut n = n1;
        n = Solution::remove_nth_from_end(n, -2);
        assert_eq!(n.clone().unwrap().val, 1);
        assert_eq!(n.clone().unwrap().next.unwrap().val, 2);
        assert_eq!(n.clone().unwrap().next.unwrap().next.unwrap().val, 3);
    }
}
