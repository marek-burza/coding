// https://www.hackerrank.com/challenges/bigger-is-greater

pub fn bigger_is_greater(w: &str) -> String {
    let mut array = w.chars().collect::<Vec<_>>();
    let length = array.len();
    for i in (1..length).rev() {
        if array[i - 1] < array[i] {
            let rest = &mut array[i..];
            rest.sort();
            for j in i..length {
                if array[i - 1] < array[j] {
                    array.swap(i - 1, j);
                    let rest = &mut array[i..];
                    rest.sort();
                    return array.into_iter().collect();
                }
            }
        }
    }
    "no answer".to_string()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_ab() {
        assert_eq!(bigger_is_greater("ab"), "ba");
    }

    #[test]
    fn test_bb() {
        assert_eq!(bigger_is_greater("bb"), "no answer");
    }

    #[test]
    fn test_hefg() {
        assert_eq!(bigger_is_greater("hefg"), "hegf");
    }

    #[test]
    fn test_dhck() {
        assert_eq!(bigger_is_greater("dhck"), "dhkc");
    }

    #[test]
    fn test_dkhc() {
        assert_eq!(bigger_is_greater("dkhc"), "hcdk");
    }
}
