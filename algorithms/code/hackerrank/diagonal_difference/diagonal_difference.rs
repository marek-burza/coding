// https://www.hackerrank.com/challenges/diagonal-difference

pub fn diagonal_difference(arr: &[Vec<i32>]) -> i32 {
    let mut result: i32 = 0;
    let n = arr.len();
    for i in 0..n {
        result += arr[i][i] - arr[n - 1 - i][i];
    }
    result.abs()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let arr = vec![vec![11, 2, 4], vec![4, 5, 6], vec![10, 8, -12]];
        assert_eq!(15, diagonal_difference(&arr));
    }
}
