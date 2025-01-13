// https://leetcode.com/problems/palindrome-linked-list/
// #easy

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    pub fn is_palindrome(mut head: Option<Box<ListNode>>) -> bool {
        let mut listed = Vec::new();
        while let Some(node) = head {
            listed.push(node.val);
            head = node.next;
        }
        let count = listed.len();
        for i in 0..(count / 2) {
            if listed[i] != listed[count - 1 - i] {
                return false;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_palindrome_odd() {
        let n5: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: None }));
        let n4: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n5 }));
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: n4 }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: n2 }));
        let list = n1;
        assert!(Solution::is_palindrome(list));
    }

    #[test]
    fn test_palindrome_even() {
        let n4: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: None }));
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n4 }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: n2 }));
        let list = n1;
        assert!(Solution::is_palindrome(list));
    }

    #[test]
    fn test_not_palindrome_odd() {
        let n5: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: None }));
        let n4: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 8, next: n5 }));
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 2, next: n4 }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: n2 }));
        let list = n1;
        assert!(!Solution::is_palindrome(list));
    }

    #[test]
    fn test_not_palindrome_even() {
        let n4: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: None }));
        let n3: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 8, next: n4 }));
        let n2: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 1, next: n3 }));
        let n1: Option<Box<ListNode>> = Some(Box::new(ListNode { val: 0, next: n2 }));
        let list = n1;
        assert!(!Solution::is_palindrome(list));
    }

    #[test]
    fn test_empty() {
        assert!(Solution::is_palindrome(None));
    }

    #[test]
    fn test_single() {
        assert!(Solution::is_palindrome(Some(Box::new(ListNode {
            val: 0,
            next: None,
        }))));
    }
}
