package leetcode.lc089_gray_code;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC089GrayCodeTests {
    private int[] freeze(final List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void test4() throws Exception {
        int[] expected = { 0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8 };
        assertArrayEquals(expected, freeze(new LC089GrayCode().grayCode(4)));
    }

    @Test
    public void test0() throws Exception {
        int[] expected = { 0 };
        assertArrayEquals(expected, freeze(new LC089GrayCode().grayCode(0)));
    }
}
