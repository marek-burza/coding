package leetcode.lc079_word_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC079WordSearchTests {
    @Test
    public void testExample() throws Exception {
        assertTrue(new LC079WordSearch().exist(
                new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
    }

    @Test
    public void testOther() throws Exception {
        assertTrue(new LC079WordSearch().exist(
                new char[][] { { 'a', 'b' }, { 'c', 'd' } }, "acdb"));
    }

    @Test
    public void testAAndAb() throws Exception {
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, "ab"));
    }

    @Test
    public void testAAndA() throws Exception {
        assertTrue(new LC079WordSearch().exist(new char[][] { { 'a' } }, "a"));
    }

    @Test
    public void testAAAndAaa() throws Exception {
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a', 'a' } }, "aaa"));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC079WordSearch().exist(null, "dummy"));
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, null));
        assertFalse(new LC079WordSearch().exist(new char[][] { { 'a' } }, ""));
    }
}
