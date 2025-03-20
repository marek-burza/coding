// https://leetcode.com/problems/add-two-numbers/
// #linking

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { val, next: None }
    }
}

pub struct Solution;

impl Solution {
    pub fn add_two_numbers(
        mut l1: Option<Box<ListNode>>,
        mut l2: Option<Box<ListNode>>,
    ) -> Option<Box<ListNode>> {
        let mut handle = Some(Box::new(ListNode::new(0)));
        let mut tail = &mut handle;
        let mut carry = 0;
        while l1.is_some() || l2.is_some() || carry != 0 {
            let mut value1 = 0;
            if let Some(node) = l1 {
                value1 = node.val;
                l1 = node.next;
            }
            let mut value2 = 0;
            if let Some(node) = l2 {
                value2 = node.val;
                l2 = node.next;
            }
            let summed = carry + value1 + value2;
            if let Some(ref mut node) = tail {
                node.next = Some(Box::new(ListNode {
                    val: summed % 10,
                    next: None,
                }));
                tail = &mut node.next;
            }
            carry = summed / 10;
        }
        handle.unwrap().next
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
        let array1 = vec![2, 4, 3];
        let array2 = vec![5, 6, 4];
        let listed = Solution::add_two_numbers(vector_to_list(array1), vector_to_list(array2));
        let expected = vec![7, 0, 8];
        assert_eq!(list_to_vector(listed), expected);
    }

    #[test]
    fn test_uneven() {
        let array1 = vec![2, 4];
        let array2 = vec![5, 6, 4];
        let listed = Solution::add_two_numbers(vector_to_list(array1), vector_to_list(array2));
        let expected = vec![7, 0, 5];
        assert_eq!(list_to_vector(listed), expected);
    }

    #[test]
    fn test_carry() {
        let array1 = vec![2, 4];
        let array2 = vec![5, 6];
        let listed = Solution::add_two_numbers(vector_to_list(array1), vector_to_list(array2));
        let expected = vec![7, 0, 1];
        assert_eq!(list_to_vector(listed), expected);
    }
}
