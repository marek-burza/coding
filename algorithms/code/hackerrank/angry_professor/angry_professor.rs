// https://www.hackerrank.com/challenges/angry-professor

pub fn angry_professor(k: i32, a: Vec<i32>) -> String {
    let mut absent = 0;
    let n = a.len() as i32;
    let threshold = n - k;
    for arrival in a {
        if arrival > 0 {
            absent += 1;
        }
        if threshold < absent {
            return "YES".to_string();
        }
    }
    "NO".to_string()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(angry_professor(3, vec![-1, -3, 4, 2]), "YES");
        assert_eq!(angry_professor(2, vec![0, -1, 2, 1]), "NO");
    }

    #[test]
    fn test_01() {
        assert_eq!(
            angry_professor(4, vec![-93, -86, 49, -62, -90, -63, 40, 72, 11, 67]),
            "NO"
        );
        assert_eq!(
            angry_professor(10, vec![23, -35, -2, 58, -67, -56, -42, -73, -19, 37]),
            "YES"
        );
        assert_eq!(
            angry_professor(9, vec![13, 91, 56, -62, 96, -5, -84, -36, -46, -13]),
            "YES"
        );
        assert_eq!(
            angry_professor(8, vec![45, 67, 64, -50, -8, 78, 84, -51, 99, 60]),
            "YES"
        );
        assert_eq!(
            angry_professor(7, vec![26, 94, -95, 34, 67, -97, 17, 52, 1, 86]),
            "YES"
        );
        assert_eq!(
            angry_professor(2, vec![19, 29, -17, -71, -75, -27, -56, -53, 65, 83]),
            "NO"
        );
        assert_eq!(
            angry_professor(10, vec![-32, -3, -70, 8, -40, -96, -18, -46, -21, -79]),
            "YES"
        );
        assert_eq!(
            angry_professor(9, vec![-50, 0, 64, 14, -56, -91, -65, -36, 51, -28]),
            "YES"
        );
        assert_eq!(
            angry_professor(6, vec![-58, -29, -35, -18, 43, -28, -76, 43, -13, 6]),
            "NO"
        );
        assert_eq!(
            angry_professor(1, vec![88, -17, -96, 43, 83, 99, 25, 90, -39, 86]),
            "NO"
        );
    }
}
