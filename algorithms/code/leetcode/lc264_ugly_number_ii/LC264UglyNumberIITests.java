package leetcode.lc264_ugly_number_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC264UglyNumberIITests {
    @Test
    public void testExample() throws Exception {
        int[] expected = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new LC264UglyNumberII().nthUglyNumber(i + 1));
        }
    }

    @Test
    public void testBigger() throws Exception {
        assertEquals(536870912, new LC264UglyNumberII().nthUglyNumber(1407));
    }
}
