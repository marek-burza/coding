package leetcode.lc216_combination_sum_iii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

public final class LC216CombinationSumIIITests {
    private void test(final int[][] expected, final List<List<Integer>> result) {
        for (List<Integer> entry : result) {
            Collections.sort(entry);
        }
        // Collections.sort(result, );
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            List<Integer> entry = result.get(i);
            assertEquals(expected[i].length, entry.size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], entry.get(j).intValue());
            }
        }
    }

    @Test
    public void test37() throws Exception {
        int[][] expected = { { 1, 2, 4 } };
        test(expected, new LC216CombinationSumIII().combinationSum3(3, 7));
    }

    @Test
    public void test39() throws Exception {
        int[][] expected = { { 1, 2, 6 }, { 1, 3, 5 }, { 2, 3, 4 } };
        test(expected, new LC216CombinationSumIII().combinationSum3(3, 9));
    }
}
