// https://www.hackerrank.com/challenges/time-conversion

pub fn to_military(s: &str) -> String {
    let mut afternoon = s.chars().nth(8) == Some('P');
    if s.starts_with("12") {
        afternoon = !afternoon;
    }
    let hour = s[..2].parse::<i32>().unwrap();
    let shift: i32 = match afternoon {
        true => 12,
        false => 0,
    };
    let before = (hour + shift) % 24;
    let after = format!("{:02}", before);
    format!("{}{}", after, &s[2..8])
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test070545pm() {
        assert_eq!(to_military("07:05:45PM"), "19:05:45");
    }

    #[test]
    fn test120000pm() {
        assert_eq!(to_military("12:00:00PM"), "12:00:00");
    }

    #[test]
    fn test120000am() {
        assert_eq!(to_military("12:00:00AM"), "00:00:00");
    }
}
