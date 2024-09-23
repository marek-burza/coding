// https://www.hackerrank.com/challenges/simple-array-sum

pub fn simple_array_sum(ar: &[i32]) -> i32 {
    let mut sum = 0;
    for value in ar {
        sum += value;
    }
    sum
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(31, simple_array_sum(&[1, 2, 3, 4, 10, 11]));
    }
}
