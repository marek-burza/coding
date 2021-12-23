package leetcode.lc090_subsets_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class LC090SubsetsIITests {
    private static class OrderlyComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
            int difference = l1.size() - l2.size();
            if (0 != difference) {
                return difference;
            } else {
                for (int i = 0; i < l1.size(); i++) {
                    difference = l1.get(i).compareTo(l2.get(i));
                    if (0 != difference) {
                        return difference;
                    }
                }
                return 0;
            }
        }
    }

    public void test(final int[][] expected, final List<List<Integer>> result) throws Exception {
        Collections.sort(result, new OrderlyComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            Collections.sort(result.get(i));
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }

    @Test
    public void test122() throws Exception {
        List<List<Integer>> list = new LC090SubsetsII().subsetsWithDup(new int[] { 1, 2, 2 });
        int[][] expected = {
                {},
                { 1 }, { 2 },
                { 1, 2 }, { 2, 2 },
                { 1, 2, 2 }
        };
        test(expected, list);
    }
}
