// https://leetcode.com/problems/odd-even-linked-list/
// #medium

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    pub fn odd_even_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut odd_head = Box::new(ListNode { val: 0, next: None });
        let mut even_head = Box::new(ListNode { val: 0, next: None });
        let mut odd_tail = &mut odd_head;
        let mut even_tail = &mut even_head;
        let mut head = head;
        let mut odd = true;
        while let Some(mut some_head) = head {
            head = some_head.next.take();
            if odd {
                odd_tail.next = Some(some_head);
                odd_tail = odd_tail.next.as_mut().unwrap();
                odd = false;
            } else {
                even_tail.next = Some(some_head);
                even_tail = even_tail.next.as_mut().unwrap();
                odd = true;
            }
        }
        odd_tail.next = even_head.next.take();
        odd_head.next
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

    fn list_to_vector(mut list: Option<Box<ListNode>>) -> Vec<i32> {
        let mut vector = Vec::new();
        while let Some(node) = list {
            vector.push(node.val);
            list = node.next;
        }
        vector
    }

    #[test]
    fn test_example_1() {
        let list = vector_to_list(vec![1, 2, 3, 4, 5]);
        let expected = vec![1, 3, 5, 2, 4];
        let result = Solution::odd_even_list(list);
        assert_eq!(list_to_vector(result), expected);
    }

    #[test]
    fn test_nothing() {
        let result = Solution::odd_even_list(None);
        assert!(result.is_none());
    }
}
