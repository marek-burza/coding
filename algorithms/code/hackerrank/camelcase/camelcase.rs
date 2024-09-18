// https://www.hackerrank.com/challenges/camelcase

pub fn camelcase(s: &str) -> usize {
    let mut count = 1;
    for (i, character) in s.chars().enumerate() {
        if i > 0 && character.is_uppercase() {
            count += 1;
        }
    }
    count
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(5, camelcase("saveChangesInTheEditor"));
    }
}
