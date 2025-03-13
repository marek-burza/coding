// https://leetcode.com/problems/remove-linked-list-elements/
// #easy

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

pub struct Solution;

impl Solution {
    pub fn remove_elements(head_node: Option<Box<ListNode>>, val: i32) -> Option<Box<ListNode>> {
        let mut previous = Box::new(ListNode { val: 0, next: None });
        previous.next = head_node;
        let mut node = &mut previous;
        while let Some(ref mut next_node) = node.next {
            if next_node.val == val {
                node.next = next_node.next.take();
            } else {
                node = node.next.as_mut().unwrap();
            }
        }
        previous.next
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
    fn test_612345676_and_6() {
        let array = vec![6, 1, 2, 3, 4, 5, 6, 7, 6];
        let mut list = vector_to_list(array);
        list = Solution::remove_elements(list, 6);
        let expected = vec![1, 2, 3, 4, 5, 7];
        assert_eq!(expected, list_to_vector(list));
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::remove_elements(None, 0).is_none());
    }
}
