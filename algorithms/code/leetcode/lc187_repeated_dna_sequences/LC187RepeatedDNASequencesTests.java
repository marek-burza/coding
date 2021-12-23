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
