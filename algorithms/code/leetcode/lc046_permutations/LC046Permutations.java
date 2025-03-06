package leetcode.lc046_permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations/
 * #medium
 */
public final class LC046Permutations {
    public void permute(final List<Integer> prefix, final Set<Integer> remaining,
            final List<List<Integer>> permutations) {
        if (remaining.size() == 0) {
            permutations.add(new ArrayList<>(prefix));
        } else {
            int size = prefix.size();
            for (int value : remaining) {
                prefix.add(value);
                Set<Integer> reduced = new HashSet<>(remaining);
                reduced.remove(value);
                permute(prefix, reduced, permutations);
                prefix.remove(size);
            }
        }
    }

    public List<List<Integer>> permute(final int[] nums) {
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> remaining = new HashSet<>();
        for (int value : nums) {
            remaining.add(value);
        }
        permute(new LinkedList<>(), remaining, permutations);
        return permutations;
    }
}
package leetcode.lc046_permutations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class LC046PermutationsTests {
    private static class IntegerListComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
            if (l1.size() < l2.size()) {
                return -1;
            }
            if (l1.size() > l2.size()) {
                return 1;
            }
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i) < l2.get(i)) {
                    return -1;
                }
                if (l1.get(i) > l2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 2, 3 };
        int[][] expected = { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 } };
        List<List<Integer>> result = new LC046Permutations().permute(nums);
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
