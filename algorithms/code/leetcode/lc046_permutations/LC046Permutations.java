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
