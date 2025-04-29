package leetcode.lc187_repeated_dna_sequences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * #medium
 */
public final class LC187RepeatedDNASequences {
    private static final Map<Character, Integer> LUT_TO_INT = new HashMap<>() {
        {
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }
    };

    private static final Map<Integer, Character> LUT_FROM_INT = new HashMap<>() {
        {
            put(0, 'A');
            put(1, 'C');
            put(2, 'G');
            put(3, 'T');
        }
    };

    public int compress(final char nucleotide) {
        return LUT_TO_INT.get(nucleotide);
    }

    public int encode(final int sequence, final int compressed) {
        return (compressed << 18) | (sequence >> 2);
    }

    public String decode(final int sequenceValue) {
        int sequence = sequenceValue;
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int nucleotide = sequence & 0x3;
            decoded.append(LUT_FROM_INT.get(nucleotide));
            sequence >>= 2;
        }
        return decoded.toString();
    }

    public List<String> findRepeatedDnaSequences(final String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return list;
        }
        Set<Integer> seen = new HashSet<>();
        int now = 0;
        for (int i = 0; i < 9; i++) {
            now = encode(now, compress(s.charAt(i)));
        }
        for (int i = 9; i < s.length(); i++) {
            now = encode(now, compress(s.charAt(i)));
            if (seen.contains(now | 0xFFF00000)) {
                continue;
            }
            if (seen.contains(now)) {
                seen.remove(now);
                seen.add(now | 0xFFF00000);
                continue;
            }
            seen.add(now);
        }
        for (int sequence : seen) {
            if (sequence < 0) {
                list.add(decode(sequence & 0xFFFFF));
            }
        }
        return list;
    }
}
package leetcode.lc187_repeated_dna_sequences;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC187RepeatedDNASequencesTests {
    @Test
    public void testAAAAACCCCCAAAAACCCCCCAAAAAGGGTTT() throws Exception {
        String[] expected = { "AAAAACCCCC", "CCCCCAAAAA" };
        Object[] result = new LC187RepeatedDNASequences().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")
                .toArray();
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testNothing() throws Exception {
        String[] expected = {};
        Object[] result;
        result = new LC187RepeatedDNASequences().findRepeatedDnaSequences(null).toArray();
        assertArrayEquals(expected, result);
        result = new LC187RepeatedDNASequences().findRepeatedDnaSequences("").toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAAAAAAAAAAAAA() throws Exception {
        String[] expected = { "AAAAAAAAAA" };
        Object[] result = new LC187RepeatedDNASequences().findRepeatedDnaSequences("AAAAAAAAAAAAA").toArray();
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }
}
```rust
use std::collections::{HashMap, HashSet, VecDeque};
use std::fmt::Write;

const NUCLEOTIDE_MAP: HashMap<char, u8> = [
    ('A', 0), ('C', 1), ('G', 2), ('T', 3),
].iter().cloned().collect();

fn compress(c: char) -> u8 {
    *NUCLEOTIDE_MAP.get(&c).expect("Invalid nucleotide")
}

fn encode(sequence: u32, compressed: u32) -> u64 {
    (compressed << 18) | (sequence >> 2)
}

fn decode(sequence_value: u64) -> String {
    let mut sequence = sequence_value as u32;
    let mut builder = String::new();

    for _ in 0..10 {
        let nucleotide = ((sequence & 0x3) as char).into_bytes()[0];
        writer!(builder, "{}", NUCLEOTIDE_MAP.get(&nucleotide).unwrap());
        sequence >>= 2;
    }

    builder
}

fn find_repeated_dna_sequences(s: String) -> Vec<String> {
    let mut seen = HashSet::new();
    let mut list = Vec::new();

    if s.len() < 10 {
        return list;
    }

    let mut buffer = [0u32; 9];

    for i in 0..s.len() - 9 {
        buffer.copy_from_slice(s.as_bytes()[i..i + 9]);
        let compressed = compress(*s.as_bytes().get(i).unwrap());
        buffer[8] = compressed;

        if seen.contains(&encode(buffer[0], buffer[1])) || seen.contains(&buffer[7]) {
            continue;
        }

        if !seen.contains(&buffer) {
            seen.insert(buffer);
            for _ in i + 9..s.len() {
                buffer.copy_from_slice(s.as_bytes()[i + 9..(i + 9) as usize]);
                let compressed = compress(*s.as_bytes().get(i + 9).unwrap());
                buffer[8] = compressed;

                if seen.contains(&encode(buffer[0], buffer[1])) || seen.contains(&buffer[7]) {
                    break;
                }

                if !seen.contains(&buffer) {
                    seen.insert(buffer);
                }
            }
        } else {
            for _ in i + 9..s.len() {
                buffer.copy_from_slice(s.as_bytes()[i + 9..(i + 9) as usize]);
                let compressed = compress(*s.as_bytes().get(i + 9).unwrap());
                buffer[8] = compressed;

                if seen.contains(&encode(buffer[0], buffer[1])) || seen.contains(&buffer[7]) {
                    break;
                }
            }
        }
    }

    for sequence in seen.into_iter() {
        list.push(decode(sequence));
    }

    list
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn testAAAAACCCCCAAAAACCCCCCAAAAAGGGTTT() {
        let expected = vec!["AAAAACCCCC".to_string(), "CCCCCAAAAA".to_string()];
        assert_eq!(find_repeated_dna_sequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT".to_string()), expected);
    }

    #[test]
    fn testNothing() {
        let expected: Vec<String> = vec![];
        assert_eq!(find_repeated_dna_sequences("").into_iter().collect(), expected);

        let result = find_repeated_dna_sequences(None).expect("Failed to get repeated dna sequences");
        assert_eq!(result, expected);
    }

    #[test]
    fn testAAAAAAAAAAAAA() {
        let expected: Vec<String> = vec!["AAAAAAAAAA".to_string()];
        assert_eq!(find_repeated_dna_sequences("AAAAAAAAAAAAA".to_string()), expected);
    }
}
```