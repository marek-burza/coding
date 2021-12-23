package leetcode.lc162_find_peak_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC162FindPeakElementTests {
    @Test
    public void test1231() throws Exception {
        assertEquals(2, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 1 }));
    }

    @Test
    public void test1234() throws Exception {
        assertEquals(3, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 4 }));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(-1, new LC162FindPeakElement().findPeakElement(new int[] {}));
    }
}
