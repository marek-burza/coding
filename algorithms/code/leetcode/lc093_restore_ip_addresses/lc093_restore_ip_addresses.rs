// https://leetcode.com/problems/restore-ip-addresses/
// #medium

pub struct Solution;

impl Solution {
    fn partial(s: &str, count: usize, ip: &mut Vec<String>, list: &mut Vec<String>) {
        if s.len() < count || (s.chars().nth(0).unwrap() == '0' && count > 1) {
            return;
        }
        let prefix = s.chars().take(count).collect::<String>();
        let part: u32 = prefix.parse().unwrap();
        if part <= 255 {
            ip.push(prefix);
            Self::restore(s.get(count..).unwrap(), ip, list);
            ip.pop();
        }
    }

    fn restore(s: &str, ip: &mut Vec<String>, list: &mut Vec<String>) {
        if ip.len() >= 4 {
            if s.is_empty() {
                let mut string = String::new();
                for (i, ip_i) in ip.iter().enumerate().take(4) {
                    if 0 < i {
                        string.push('.');
                    }
                    string.push_str(ip_i);
                }
                list.push(string);
            }
        } else {
            Self::partial(s, 1, ip, list);
            Self::partial(s, 2, ip, list);
            Self::partial(s, 3, ip, list);
        }
    }

    pub fn restore_ip_addresses(s: String) -> Vec<String> {
        let mut ip = Vec::new();
        let mut list = Vec::new();
        Self::restore(&s, &mut ip, &mut list);
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_25525511135() {
        let expected = vec!["255.255.11.135".to_string(), "255.255.111.35".to_string()];
        let mut result = Solution::restore_ip_addresses("25525511135".into());
        result.sort();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_101023() {
        let expected = vec![
            "1.0.10.23".to_string(),
            "1.0.102.3".to_string(),
            "10.1.0.23".to_string(),
            "10.10.2.3".to_string(),
            "101.0.2.3".to_string(),
        ];
        let mut result = Solution::restore_ip_addresses("101023".into());
        result.sort();
        assert_eq!(result, expected);
    }
}
