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
