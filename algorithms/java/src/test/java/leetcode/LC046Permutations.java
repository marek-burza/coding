package leetcode;

import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/permutations/
 * #medium
 */
public final class LC046Permutations {
    public final class Solution {
        public void permute(final List<Integer> prefix, final Set<Integer> remaining, final List<List<Integer>> permutations) {
            if (remaining.size() == 0) {
                permutations.add(new ArrayList(prefix));
            } else {
                int size = prefix.size();
                for (int value : remaining) {
                    prefix.add(value);
                    Set<Integer> reduced = new HashSet(remaining);
                    reduced.remove(value);
                    permute(prefix, reduced, permutations);
                    prefix.remove(size);
                }
            }
        }

        public List<List<Integer>> permute(final int[] nums) {
            List<List<Integer>> permutations = new LinkedList<>();
            Set<Integer> remaining = new HashSet();
            for (int value : nums) {
                remaining.add(value);
            }
            permute(new LinkedList(), remaining, permutations);
            return permutations;
        }
    }

    private class IntegerListComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
            if (l1.size() < l2.size()) return -1;
            if (l1.size() > l2.size()) return 1;
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i) < l2.get(i)) return -1;
                if (l1.get(i) > l2.get(i)) return 1;
            }
            return 0;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {1, 2, 3};
        int[][] expected = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
        List<List<Integer>> result = new Solution().permute(nums);
        Collections.sort(result, new IntegerListComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
