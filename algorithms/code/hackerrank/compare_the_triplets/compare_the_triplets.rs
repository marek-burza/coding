// https://www.hackerrank.com/challenges/compare-the-triplets

pub fn compare_triplets(a: Vec<i32>, b: Vec<i32>) -> Vec<i32> {
    let mut result = vec![0, 0];
    for i in 0..3 {
        if a[i] > b[i] {
            result[0] += 1;
        }
        if a[i] < b[i] {
            result[1] += 1;
        }
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_0() {
        assert_eq!(vec![1, 1], compare_triplets(vec![5, 6, 7], vec![3, 6, 10]));
    }

    #[test]
    fn test_example_1() {
        assert_eq!(
            vec![2, 1],
            compare_triplets(vec![17, 28, 30], vec![99, 16, 8])
        );
    }
}
