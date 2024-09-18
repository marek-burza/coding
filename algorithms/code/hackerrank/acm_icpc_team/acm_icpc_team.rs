// https://www.hackerrank.com/challenges/acm-icpc-team

pub fn acm_team(topic: Vec<String>) -> Vec<i32> {
    let n = topic.len();
    let m = topic[0].len();
    let mut coverage_count = 0;
    let mut teams_count = 0;
    for i in 0..n - 1 {
        for j in i + 1..n {
            let mut count = 0;
            for k in 0..m {
                if topic[i].chars().nth(k).unwrap() == '1'
                    || topic[j].chars().nth(k).unwrap() == '1'
                {
                    count += 1;
                }
            }
            match count.cmp(&coverage_count) {
                std::cmp::Ordering::Greater => {
                    coverage_count = count;
                    teams_count = 1;
                }
                std::cmp::Ordering::Equal => teams_count += 1,
                _ => (),
            }
        }
    }
    vec![coverage_count, teams_count]
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        let result = acm_team(vec![
            String::from("10101"),
            String::from("11100"),
            String::from("11010"),
            String::from("00101"),
        ]);
        assert_eq!(result[0], 5);
        assert_eq!(result[1], 2);
    }

    #[test]
    fn test_example2() {
        let result = acm_team(vec![
            String::from("10101"),
            String::from("11110"),
            String::from("00010"),
        ]);
        assert_eq!(result[0], 5);
        assert_eq!(result[1], 1);
    }
}
