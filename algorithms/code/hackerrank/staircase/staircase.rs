// https://www.hackerrank.com/challenges/staircase

pub fn staircase(n: i32) -> Vec<String> {
    let mut result = Vec::new();
    for index in 0..n {
        let mut line = String::from("");
        for i in 0..n {
            if i < n - 1 - index {
                line.push(' ');
            } else {
                line.push('#');
            }
        }
        result.push(line);
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn test_example() {
        let expected = vec![
            String::from("     #"),
            String::from("    ##"),
            String::from("   ###"),
            String::from("  ####"),
            String::from(" #####"),
            String::from("######"),
        ];
        assert_eq!(expected, staircase(6));
    }
}
