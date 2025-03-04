// https://leetcode.com/problems/insertion-sort-list/
// #medium

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
    pub fn insertion_sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        match head {
            None => None,
            Some(node) => {
                // Grab the first node after the already ordered nodes
                let mut tail = Some(node);
                // Iterate from the node holding the head
                let mut handle = ListNode::new(0);
                // Iterate until we reach the end
                while let Some(mut node) = tail {
                    // Remove (extract) that node from the list
                    tail = node.next.take();
                    // Grab the first ordered node
                    let mut current = &mut handle;
                    // Iterate until we reach a node greater or equal to the extracted one
                    while current.next.is_some() && current.next.as_ref().unwrap().val < node.val {
                        // Move on to the next ordered node
                        current = current.next.as_mut().unwrap()
                    }
                    // Insert the node
                    node.next = current.next.take();
                    current.next = Some(node);
                }
                handle.next
            }
        }
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
    fn test_example() {
        let list = vector_to_list(vec![6, 3, 4, 5, 2, 1]);
        let result = Solution::insertion_sort_list(list);
        assert_eq!(vec![1, 2, 3, 4, 5, 6], list_to_vector(result));
    }

    #[test]
    fn test_11() {
        let list = vector_to_list(vec![1, 1]);
        let result = Solution::insertion_sort_list(list);
        assert_eq!(vec![1, 1], list_to_vector(result));
    }

    #[test]
    fn test_nothing() {
        assert!(Solution::insertion_sort_list(None).is_none());
    }
}
