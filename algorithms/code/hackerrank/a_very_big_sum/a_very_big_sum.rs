// https://www.hackerrank.com/challenges/a-very-big-sum

pub fn avery_big_sum(ar: Vec<i32>) -> i64 {
    let mut sum = 0;
    for value in ar {
        sum += value as i64;
    }
    sum
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let ar = vec![1000000001, 1000000002, 1000000003, 1000000004, 1000000005];
        let result = avery_big_sum(ar);
        assert_eq!(result, 5000000015);
    }
}
