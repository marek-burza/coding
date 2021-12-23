package leetcode.lc039_combination_sum;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC039CombinationSumTests {
    private static class DeepComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> list1, final List<Integer> list2) {
            if (list1.size() < list2.size()) {
                return -1;
            }
            if (list1.size() > list2.size()) {
                return 1;
            }
            for (int i = 0, length = list1.size(); i < length; i++) {
                if (list1.get(i) < list2.get(i)) {
                    return -1;
                }
                if (list1.get(i) > list2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    @Test
    public void testExample() throws Exception {
        int[] candidates = { 2, 3, 6, 7 };
        int[][] expected = { { 7 }, { 2, 2, 3 } };
        List<List<Integer>> combos = new LC039CombinationSum().combinationSum(candidates, 7);
        for (List<Integer> list : combos) {
            Collections.sort(list);
        }
        Collections.sort(combos, new DeepComparator());
        assertEquals(expected.length, combos.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, combos.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], combos.get(i).get(j).intValue());
            }
        }
    }
}
