// https://leetcode.com/problems/flatten-nested-list-iterator/
// #medium

#[derive(Debug, PartialEq, Eq)]
pub enum NestedInteger {
    Int(i32),
    List(Vec<NestedInteger>),
}

pub struct NestedIterator {
    flattened: Vec<i32>,
    index: usize,
}

#[allow(clippy::should_implement_trait)]
impl NestedIterator {
    pub fn new(nested_list: Vec<NestedInteger>) -> Self {
        let mut flattened = Vec::new();
        Self::flatten(&nested_list, &mut flattened);
        Self {
            flattened,
            index: 0,
        }
    }

    fn flatten(nested_list: &[NestedInteger], flattened: &mut Vec<i32>) {
        for nested_integer in nested_list {
            match nested_integer {
                NestedInteger::Int(value) => flattened.push(*value),
                NestedInteger::List(list) => Self::flatten(list, flattened),
            }
        }
    }

    pub fn next(&mut self) -> i32 {
        let value = self.flattened[self.index];
        self.index += 1;
        value
    }

    pub fn has_next(&self) -> bool {
        self.index < self.flattened.len()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn generic(list: Vec<NestedInteger>, expected: Vec<i32>) {
        let mut nested = NestedIterator::new(list);
        for value in expected {
            assert!(nested.has_next());
            assert_eq!(value, nested.next());
        }
        assert!(!nested.has_next());
    }

    #[test]
    fn test_example_1() {
        let mut list11a = Vec::new();
        list11a.push(NestedInteger::Int(1));
        list11a.push(NestedInteger::Int(1));
        let mut list11b = Vec::new();
        list11b.push(NestedInteger::Int(1));
        list11b.push(NestedInteger::Int(1));
        let mut list = Vec::new();
        list.push(NestedInteger::List(list11a));
        list.push(NestedInteger::Int(2));
        list.push(NestedInteger::List(list11b));
        let expected = vec![1, 1, 2, 1, 1];
        generic(list, expected);
    }

    #[test]
    fn test_example_2() {
        let mut list = Vec::new();
        list.push(NestedInteger::Int(1));
        let mut list4 = Vec::new();
        list4.push(NestedInteger::Int(4));
        let mut list6 = Vec::new();
        list6.push(NestedInteger::Int(6));
        list4.push(NestedInteger::List(list6));
        list.push(NestedInteger::List(list4));
        let expected = vec![1, 4, 6];
        generic(list, expected);
    }
}
