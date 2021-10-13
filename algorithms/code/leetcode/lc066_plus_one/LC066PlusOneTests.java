package leetcode.lc066_plus_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC066PlusOneTests {
    @Test
    public void test1And9() throws Exception {
        int[] expected = { 2, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 1, 9 }));
    }

    @Test
    public void test9And9() throws Exception {
        int[] expected = { 1, 0, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 9, 9 }));
    }
}
