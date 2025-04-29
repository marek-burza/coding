// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// #easy

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
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

    pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let vector = Self::list_to_vector(head);
        let mut deduplicated = Vec::new();
        for &value in &vector {
            if deduplicated.is_empty() || *deduplicated.last().unwrap() != value {
                deduplicated.push(value);
            }
        }
        Self::vector_to_list(deduplicated)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_112() {
        let list = Solution::vector_to_list(vec![1, 1, 2]);
        let result = Solution::delete_duplicates(list);
        assert_eq!(vec![1, 2], Solution::list_to_vector(result));
    }

    #[test]
    fn test_11233() {
        let list = Solution::vector_to_list(vec![1, 1, 2, 3, 3]);
        let result = Solution::delete_duplicates(list);
        assert_eq!(vec![1, 2, 3], Solution::list_to_vector(result));
    }
}
