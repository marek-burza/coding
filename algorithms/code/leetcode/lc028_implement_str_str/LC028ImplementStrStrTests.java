package leetcode.lc028_implement_str_str;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/implement-strstr/
 */
public final class LC028ImplementStrStrTests {
    @Test
    public void testEmpty() throws Exception {
        LC028ImplementStrStr solution;
        solution = new LC028ImplementStrStr();
        assertEquals(0, solution.strStr("", ""));
    }

    @Test
    public void testMississippiA() throws Exception {
        LC028ImplementStrStr solution;
        solution = new LC028ImplementStrStr();
        assertEquals(-1, solution.strStr("mississippi", "a"));
    }

    @Test
    public void testMississippiSi() throws Exception {
        LC028ImplementStrStr solution;
        solution = new LC028ImplementStrStr();
        assertEquals(3, solution.strStr("mississippi", "si"));
    }

    @Test
    public void testBiggerInSmaller() throws Exception {
        LC028ImplementStrStr solution;
        solution = new LC028ImplementStrStr();
        assertEquals(-1, solution.strStr("", "test"));
    }
}
