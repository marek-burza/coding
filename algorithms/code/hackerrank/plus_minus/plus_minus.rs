// https://www.hackerrank.com/challenges/plus-minus

pub fn plus_minus(arr: &[i32]) -> Vec<f64> {
    let n = arr.len() as f64;
    let mut counts = vec![0.0_f64; 3];
    for value in arr {
        match value {
            v if *v > 0 => counts[0] += 1.0,
            v if *v < 0 => counts[1] += 1.0,
            _ => counts[2] += 1.0,
        }
    }
    counts[0] /= n;
    counts[1] /= n;
    counts[2] /= n;
    counts
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let arr = [-4, 3, -9, 0, 4, 1];
        let expected = [1.0_f64 / 2.0_f64, 1.0_f64 / 3.0_f64, 1.0_f64 / 6.0_f64];
        let result = plus_minus(&arr);
        for i in 0..3 {
            assert_eq!(expected[i], result[i]);
        }
    }
}
