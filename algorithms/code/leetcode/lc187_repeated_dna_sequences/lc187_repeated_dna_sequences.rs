// https://leetcode.com/problems/repeated-dna-sequences/
// #medium

use std::collections::HashSet;

pub struct Solution;

impl Solution {
    fn compress(nucleotide: &str) -> usize {
        match nucleotide.chars().nth(0) {
            Some('A') => 0,
            Some('C') => 1,
            Some('G') => 2,
            _ => 3,
        }
    }

    fn encode(sequence: u64, compressed: usize) -> u64 {
        ((compressed as u64) << 18) | (sequence >> 2)
    }

    fn decode(sequence_value: u64) -> String {
        let lut_to_int = ["A", "C", "G", "T"];
        let mut sequence = sequence_value;
        let mut decoded = String::new();
        for _ in 0..10 {
            let nucleotide = (sequence & 0x3) as usize;
            decoded.push_str(lut_to_int[nucleotide]);
            sequence >>= 2;
        }
        decoded
    }

    pub fn find_repeated_dna_sequences(s: &str) -> Vec<String> {
        let mut list = Vec::new();
        if s.len() < 10 {
            return list;
        }
        let mut seen = HashSet::new();
        let mut now = 0;
        for i in 0..9 {
            now = Self::encode(now, Self::compress(&s[i..i + 1]));
        }
        for i in 9..s.len() {
            now = Self::encode(now, Self::compress(&s[i..i + 1]));
            if seen.contains(&(now | 0xFFF00000)) {
                continue;
            }
            if seen.contains(&now) {
                seen.remove(&now);
                seen.insert(now | 0xFFF00000);
                continue;
            }
            seen.insert(now);
        }
        for sequence in seen {
            if sequence & 0xFFF00000 == 0xFFF00000 {
                list.push(Self::decode(sequence));
            }
        }
        list
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aaaaacccccaaaaaccccccaaaaagggttt() {
        let expected = vec!["AAAAACCCCC".to_string(), "CCCCCAAAAA".to_string()];
        let mut result = Solution::find_repeated_dna_sequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        result.sort_unstable();
        assert_eq!(result, expected);
    }

    #[test]
    fn test_nothing() {
        let expected: Vec<String> = vec![];
        let result = Solution::find_repeated_dna_sequences("");
        assert_eq!(result, expected);
    }

    #[test]
    fn test_aaaaaaaaaaaaa() {
        let expected: Vec<String> = vec!["AAAAAAAAAA".to_string()];
        let mut result = Solution::find_repeated_dna_sequences("AAAAAAAAAAAAA");
        result.sort_unstable();
        assert_eq!(result, expected);
    }
}
