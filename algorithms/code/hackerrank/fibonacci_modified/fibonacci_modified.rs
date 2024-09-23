// https://www.hackerrank.com/challenges/fibonacci-modified

use num_bigint::BigInt;

pub fn fibonacci_modified(t1: i32, t2: i32, n: i32) -> BigInt {
    let mut tn1 = BigInt::from(t1);
    let mut tn2 = BigInt::from(t2);
    for _ in 3..=n {
        let mut result = BigInt::from(0);
        result += &tn2;
        result *= &result.clone();
        result += &tn1;
        tn1 = tn2;
        tn2 = result;
    }
    tn2
}

#[cfg(test)]
mod tests {
    use std::str::FromStr;

    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(BigInt::from(5), fibonacci_modified(0, 1, 5));
    }

    #[test]
    fn test_example_2() {
        let expected = BigInt::from_str("84266613096281243382112").unwrap();
        assert_eq!(expected, fibonacci_modified(0, 1, 10));
    }
}
