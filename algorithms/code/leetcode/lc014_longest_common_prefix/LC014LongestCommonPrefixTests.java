package leetcode.lc014_longest_common_prefix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC014LongestCommonPrefixTests {
    @Test
    public void testAlaAlaMaKota() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("Ala", solution.longestCommonPrefix(new String[] { "Ala", "Ala Ma Kota" }));
    }

    @Test
    public void testAaA() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("a", solution.longestCommonPrefix(new String[] { "aa", "a" }));
    }

    @Test
    public void testAbAaCoverage() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("a", solution.longestCommonPrefix(new String[] { "ab", "aa" }));
    }

    @Test
    public void testNone() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("", solution.longestCommonPrefix(new String[] {}));
    }

    @Test
    public void testEmptyB() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("", solution.longestCommonPrefix(new String[] { "", "b" }));
    }

    @Test
    public void testNullB() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("", solution.longestCommonPrefix(new String[] { null, "b" }));
    }

    @Test
    public void testSame() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("same", solution.longestCommonPrefix(new String[] { "same", "same" }));
    }

    @Test
    public void testNull() throws Exception {
        LC014LongestCommonPrefix solution;
        solution = new LC014LongestCommonPrefix();
        assertEquals("", solution.longestCommonPrefix(null));
    }
}
