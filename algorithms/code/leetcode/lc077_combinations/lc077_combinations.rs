// https://leetcode.com/problems/combinations/
// #medium

pub struct Solution;

impl Solution {
    fn combine_internal(m: i32, n: i32, k: i32, prefix: &mut Vec<i32>, found: &mut Vec<Vec<i32>>) {
        for i in m..=n - (k - 1) + prefix.len() as i32 {
            prefix.push(i);
            if prefix.len() as i32 == k {
                found.push(prefix.clone());
            } else {
                Self::combine_internal(i + 1, n, k, prefix, found);
            }
            prefix.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut found = Vec::new();
        Self::combine_internal(1, n, k, &mut Vec::new(), &mut found);
        found
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::cmp::Ordering;

    struct IntegerVector(Vec<i32>);

    struct IntegerVectorComparator;

    impl IntegerVectorComparator {
        pub fn compare(l1: &IntegerVector, l2: &IntegerVector) -> Ordering {
            if l1.0.len() < l2.0.len() {
                return Ordering::Less;
            }
            if l1.0.len() > l2.0.len() {
                return Ordering::Greater;
            }
            for (a, b) in l1.0.iter().zip(l2.0.iter()) {
                if a < b {
                    return Ordering::Less;
                }
                if a > b {
                    return Ordering::Greater;
                }
            }
            Ordering::Equal
        }
    }

    impl Ord for IntegerVector {
        fn cmp(&self, other: &Self) -> Ordering {
            IntegerVectorComparator::compare(self, other)
        }
    }

    impl PartialOrd for IntegerVector {
        fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
            Some(self.cmp(other))
        }
    }

    impl PartialEq for IntegerVector {
        fn eq(&self, other: &Self) -> bool {
            self.cmp(other) == Ordering::Equal
        }
    }

    impl Eq for IntegerVector {}

    #[test]
    fn test_example() {
        let expected = vec![
            vec![1, 2],
            vec![1, 3],
            vec![1, 4],
            vec![2, 3],
            vec![2, 4],
            vec![3, 4],
        ];
        let result = Solution::combine(4, 2);
        let mut custom_sortable: Vec<IntegerVector> = result
            .into_iter()
            .map(|vector| IntegerVector(vector)) // Create IntegerList directly in the map
            .collect();
        custom_sortable.sort();
        let result: Vec<Vec<i32>> = custom_sortable
            .into_iter()
            .map(|IntegerVector(vector)| vector)
            .collect();
        assert_eq!(expected, result);
    }

    #[test]
    fn test_comparator() {
        assert_eq!(
            IntegerVectorComparator::compare(
                &IntegerVector(vec![1, 2]),
                &IntegerVector(vec![1, 2, 3])
            ),
            Ordering::Less
        );
        assert_eq!(
            IntegerVectorComparator::compare(
                &IntegerVector(vec![1, 2, 3]),
                &IntegerVector(vec![1, 2])
            ),
            Ordering::Greater
        );
        assert_eq!(
            IntegerVectorComparator::compare(
                &IntegerVector(vec![1, 2]),
                &IntegerVector(vec![1, 2])
            ),
            Ordering::Equal
        );
        assert_eq!(
            IntegerVectorComparator::compare(
                &IntegerVector(vec![0, 2]),
                &IntegerVector(vec![1, 2])
            ),
            Ordering::Less
        );
        assert_eq!(
            IntegerVectorComparator::compare(
                &IntegerVector(vec![2, 3]),
                &IntegerVector(vec![1, 2])
            ),
            Ordering::Greater
        );
        assert!(IntegerVector(vec![1, 2]) == IntegerVector(vec![1, 2]));
    }
}
