package leetcode.lc274_h_index;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC274HIndexTests {
    @Test
    public void testExample() throws Exception {
        int[] citations = { 3, 0, 6, 1, 5 };
        assertEquals(3, new LC274HIndex().hIndex(citations));
    }

    @Test
    public void testNone() throws Exception {
        int[] citations = { 0, 0, 0, 0, 0 };
        assertEquals(0, new LC274HIndex().hIndex(citations));
    }

    @Test
    public void test100() throws Exception {
        int[] citations = { 100 };
        assertEquals(1, new LC274HIndex().hIndex(citations));
    }
}
