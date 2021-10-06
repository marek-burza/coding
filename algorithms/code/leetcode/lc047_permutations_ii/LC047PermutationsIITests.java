package leetcode.lc047_permutations_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/ #medium
 */
public final class LC047PermutationsIITests {
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
        int[] nums = { 1, 1, 2 };
        List<List<Integer>> result = new LC047PermutationsII().permuteUnique(nums);
        Collections.sort(result, new DeepComparator());
        int[][] expected = { { 1, 1, 2 }, { 1, 2, 1 }, { 2, 1, 1 } };
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
