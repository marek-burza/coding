// https://leetcode.com/problems/partition-list/
// #medium

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    pub fn partition(head: Option<Box<ListNode>>, x: i32) -> Option<Box<ListNode>> {
        let mut before = Box::new(ListNode { val: 0, next: None });
        let mut after = Box::new(ListNode { val: 0, next: None });
        let mut less = &mut before;
        let mut more = &mut after;
        let mut head = head;

        while let Some(mut some_head) = head {
            head = some_head.next.take();
            if some_head.val < x {
                less.next = Some(some_head);
                less = less.next.as_mut().unwrap();
            } else {
                more.next = Some(some_head);
                more = more.next.as_mut().unwrap();
            }
        }
        less.next = after.next.take();
        before.next
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
    fn test_143252_and_3() {
        let list = vector_to_list(vec![1, 4, 3, 2, 5, 2]);
        let expected = vec![1, 2, 2, 4, 3, 5];
        let result = Solution::partition(list, 3);
        assert_eq!(list_to_vector(result), expected);
    }

    #[test]
    fn test_nothing() {
        let result = Solution::partition(None, 0);
        assert!(result.is_none());
    }
}
