// https://leetcode.com/problems/reverse-linked-list/
// #easy

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    pub fn reverse_list(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut ante: Option<Box<ListNode>> = None;
        while let Some(mut post) = head {
            head = post.next.take();
            post.next = ante;
            ante = Some(post);
        }
        ante
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
    fn test_15() {
        let vector: Vec<i32> = (0..15).collect();
        let list = vector_to_list(vector);
        let result = Solution::reverse_list(list);
        let expected: Vec<i32> = (0..15).rev().collect();
        assert_eq!(expected, list_to_vector(result));
    }

    #[test]
    fn test_1() {
        let list = vector_to_list(vec![0]);
        let result = Solution::reverse_list(list);
        assert_eq!(vec![0], list_to_vector(result));
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::reverse_list(None).is_none());
    }
}
