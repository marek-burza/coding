package leetcode.lc047_permutations_ii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/permutations-ii/ #medium
 */
public final class LC047PermutationsII {
    public List<List<Integer>> permuteUnique(final int[] nums) {
        // Count each number
        Map<Integer, Integer> counted = new TreeMap<>();
        for (int value : nums) {
            int count = 1;
            if (counted.containsKey(value)) {
                count += counted.get(value);
            }
            counted.put(value, count);
        }
        // Generate the permutations
        List<List<Integer>> permutations = new LinkedList<>();
        generate(new ArrayList<>(nums.length), nums.length, counted, permutations);
        return permutations;
    }

    private void generate(final List<Integer> permutation, final int limit, final Map<Integer, Integer> counted,
            final List<List<Integer>> permutations) {
        if (permutation.size() == limit) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : counted.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            if (count != 0) {
                int size = permutation.size();
                permutation.add(key);
                counted.put(key, count - 1);
                generate(permutation, limit, counted, permutations);
                counted.put(key, count);
                permutation.remove(size);
            }
        }
    }
}
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
