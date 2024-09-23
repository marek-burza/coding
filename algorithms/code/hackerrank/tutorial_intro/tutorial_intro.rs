// https://www.hackerrank.com/challenges/tutorial-intro

pub fn intro_tutorial(v: i32, arr: &[i32]) -> i32 {
    let mut a = 0;
    let mut z = arr.len() - 1;
    while a <= z {
        let m = (a + z) >> 1;
        if arr[m] == v {
            return m as i32;
        }
        if arr[m] < v {
            a = m + 1;
        }
        if arr[m] > v {
            z = m - 1;
        }
    }
    -1
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(1, intro_tutorial(4, &[1, 4, 5, 7, 9, 12]));
    }

    #[test]
    fn test_another() {
        assert_eq!(3, intro_tutorial(7, &[1, 4, 5, 7, 9, 12]));
    }

    #[test]
    fn test_invalid() {
        assert_eq!(-1, intro_tutorial(20, &[1, 4, 5, 7, 9, 12]));
    }
}
