package leetcode.lc275_h_index_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC275HIndexIITests {
    @Test
    public void testExample() throws Exception {
        int[] citations = { 0, 1, 3, 5, 6 };
        assertEquals(3, new LC275HIndexII().hIndex(citations));
    }

    @Test
    public void testNone() throws Exception {
        int[] citations = { 0, 0, 0, 0, 0 };
        assertEquals(0, new LC275HIndexII().hIndex(citations));
    }

    @Test
    public void test100() throws Exception {
        int[] citations = { 100 };
        assertEquals(1, new LC275HIndexII().hIndex(citations));
    }
}
