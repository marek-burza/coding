// https://leetcode.com/problems/count-and-say/
// #medium

pub fn count_and_say(n_value: i32) -> String {
    let mut n = n_value;
    if n < 1 {
        return "".to_string();
    }
    let mut result = "1".to_string();
    while n > 1 {
        let mut current = String::new();
        let mut check: char = '\x00';
        let mut count = 0;
        for character in result.chars() {
            if character != check {
                if count > 0 {
                    current.push_str(&count.to_string());
                    current.push(check);
                }
                count = 1;
                check = character;
            } else {
                count += 1;
            }
        }
        current.push_str(&count.to_string());
        current.push(check);
        result = current;
        n -= 1;
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1() {
        assert_eq!(count_and_say(1), "1".to_string());
    }

    #[test]
    fn test2() {
        assert_eq!(count_and_say(2), "11".to_string());
    }

    #[test]
    fn test3() {
        assert_eq!(count_and_say(3), "21".to_string());
    }

    #[test]
    fn test4() {
        assert_eq!(count_and_say(4), "1211".to_string());
    }

    #[test]
    fn test5() {
        assert_eq!(count_and_say(5), "111221".to_string());
    }

    #[test]
    fn test0() {
        assert!(count_and_say(0).is_empty());
    }
}
