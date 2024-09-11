// https://leetcode.com/problems/longest-palindromic-substring/
// See also: Manacher algorithm

pub struct Solution;

impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let mut longest = "".to_string();
        let mut index = 0;
        while index < s.len() {
            // Find head & tail
            let mut head = index;
            let mut tail = index;
            while tail + 1 < s.len() && s.chars().nth(head) == s.chars().nth(tail + 1) {
                tail += 1;
            }
            index += 1 + tail - head;
            // Expand
            while head > 0
                && tail + 1 < s.len()
                && s.chars().nth(head - 1) == s.chars().nth(tail + 1)
            {
                head -= 1;
                tail += 1;
            }
            // Check
            if longest.len() < tail - head + 1 {
                longest = s[head..=tail].to_string();
            }
        }
        longest
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1() {
        assert_eq!(Solution::longest_palindrome("babad".to_string()), "bab");
    }

    #[test]
    fn test_2() {
        assert_eq!(Solution::longest_palindrome("cbbd".to_string()), "bb");
    }

    #[test]
    fn test_3() {
        assert_eq!(Solution::longest_palindrome("a".to_string()), "a");
    }

    #[test]
    fn test_4() {
        assert_eq!(Solution::longest_palindrome("ac".to_string()), "a");
    }

    #[test]
    fn test_bb() {
        assert_eq!(Solution::longest_palindrome("bb".to_string()), "bb");
    }

    #[test]
    fn test_longer() {
        assert_eq!(
            Solution::longest_palindrome(
                "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth".to_string()
            ),
            "ranynar",
        );
    }
}
