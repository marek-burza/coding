// https://leetcode.com/problems/combination-sum/
// #medium

fn combination_sum_inner(
    candidates: &Vec<i32>,
    target: i32,
    path: &[i32],
    total: i32,
    index: usize,
    combos: &mut Vec<Vec<i32>>,
) {
    if total == target {
        combos.push(path.to_vec());
        return;
    }
    let mut inner = path.to_vec();
    let mut partial: i32 = 0;
    while partial <= target - total && index < candidates.len() {
        combination_sum_inner(
            candidates,
            target,
            &inner,
            total + partial,
            index + 1,
            combos,
        );
        inner.push(candidates[index]);
        partial += candidates[index];
    }
}

pub fn combination_sum(candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
    let mut combos: Vec<Vec<i32>> = Vec::new();
    let root: Vec<i32> = Vec::new();
    combination_sum_inner(&candidates, target, &root, 0, 0, &mut combos);
    combos
}

#[cfg(test)]
mod tests {
    use super::*;

    fn deep_comparator(list1: &[i32], list2: &[i32]) -> std::cmp::Ordering {
        match list1.len().cmp(&list2.len()) {
            std::cmp::Ordering::Less => return std::cmp::Ordering::Less,
            std::cmp::Ordering::Greater => return std::cmp::Ordering::Greater,
            _ => {
                for (list1i, list2i) in list1.iter().zip(list2.iter()) {
                    if list1i < list2i {
                        return std::cmp::Ordering::Less;
                    }
                    if list1i > list2i {
                        return std::cmp::Ordering::Greater;
                    }
                }
            }
        }
        std::cmp::Ordering::Equal
    }

    #[test]
    fn test_deep_comparator_empty_lists() {
        assert_eq!(deep_comparator(&[], &[]), std::cmp::Ordering::Equal);
    }

    #[test]
    fn test_deep_comparator_one_element_lists() {
        assert_eq!(deep_comparator(&[1], &[2]), std::cmp::Ordering::Less);
        assert_eq!(deep_comparator(&[2], &[1]), std::cmp::Ordering::Greater);
    }

    #[test]
    fn test_deep_comparator_equal_length_lists() {
        assert_eq!(deep_comparator(&[1, 2], &[3, 4]), std::cmp::Ordering::Less);
        assert_eq!(
            deep_comparator(&[4, 3], &[1, 2]),
            std::cmp::Ordering::Greater
        );
    }

    #[test]
    fn test_deep_comparator_non_equal_length_lists() {
        assert_eq!(deep_comparator(&[1, 2], &[]), std::cmp::Ordering::Greater);
        assert_eq!(deep_comparator(&[], &[3, 4]), std::cmp::Ordering::Less);
    }

    #[test]
    fn test_deep_comparator_all_equal_lists() {
        assert_eq!(
            deep_comparator(&[1, 2, 3], &[1, 2, 3]),
            std::cmp::Ordering::Equal
        );
    }

    fn check(candidates: Vec<i32>, target: i32, expected: Vec<Vec<i32>>) {
        let mut combos: Vec<Vec<i32>> = combination_sum(candidates, target);
        for list in &mut combos {
            list.sort();
        }
        combos.sort_by(|list1, list2| deep_comparator(list1, list2));
        assert_eq!(expected.len(), combos.len());
        for i in 0..expected.len() {
            assert_eq!(expected[i].len(), combos[i].len());
            for j in 0..expected[i].len() {
                assert_eq!(expected[i][j], combos[i][j]);
            }
        }
    }

    #[test]
    fn test_example_1() {
        let candidates = vec![2, 3, 6, 7];
        let target = 7;
        let expected: Vec<Vec<i32>> = vec![vec![7], vec![2, 2, 3]];
        check(candidates, target, expected);
    }

    #[test]
    fn test_example_2() {
        let candidates = vec![2, 3, 5];
        let target = 8;
        let expected: Vec<Vec<i32>> = vec![vec![3, 5], vec![2, 3, 3], vec![2, 2, 2, 2]];
        check(candidates, target, expected);
    }

    #[test]
    fn test_example_3() {
        let candidates = vec![2];
        let target = 1;
        let expected: Vec<Vec<i32>> = vec![];
        check(candidates, target, expected);
    }
}
