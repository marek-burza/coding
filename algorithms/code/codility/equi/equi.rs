extern crate num_bigint;
extern crate num_traits;

use self::num_bigint::BigInt;
use self::num_traits::Zero;

pub fn find_equilibrium_index(array: &[i32]) -> i32 {
    let n = array.len();
    if n == 0 {
        return -1;
    }
    let mut after = vec![BigInt::zero(); n];
    for i in (1..n).rev() {
        after[i - 1] = &after[i] + BigInt::from(array[i]);
    }
    let mut summed = BigInt::zero();
    for i in 0..n {
        if summed == after[i] {
            return i as i32;
        }
        summed += BigInt::from(array[i]);
    }
    -1
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let array = vec![-1, 3, -4, 5, 1, -6, 2, 1];
        let result = find_equilibrium_index(&array);
        assert_eq!(result, 1);
    }

    #[test]
    fn test_empty() {
        let array: Vec<i32> = vec![];
        let result = find_equilibrium_index(&array);
        assert_eq!(result, -1);
    }

    #[test]
    fn test_invalid() {
        let array = vec![0, 1, 2, 3, 4];
        let result = find_equilibrium_index(&array);
        assert_eq!(result, -1);
    }
}
