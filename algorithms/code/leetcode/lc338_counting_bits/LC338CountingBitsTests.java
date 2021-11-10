package leetcode.lc338_counting_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC338CountingBitsTests {
    @Test
    public void test2() throws Exception {
        assertArrayEquals(new int[] { 0, 1, 1 }, new LC338CountingBits().countBits(2));
    }

    @Test
    public void test5() throws Exception {
        assertArrayEquals(new int[] { 0, 1, 1, 2, 1, 2 }, new LC338CountingBits().countBits(5));
    }
}
