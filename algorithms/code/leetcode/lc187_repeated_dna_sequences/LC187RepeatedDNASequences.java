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
