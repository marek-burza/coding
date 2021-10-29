package leetcode.lc219_contains_duplicate_ii;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/ #easy
 */
public final class LC219ContainsDuplicateII {
    public boolean containsNearbyDuplicate(final int[] nums, final int k) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> ordered = new LinkedList<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            ordered.add(num);
            set.add(num);
            if (ordered.size() > k) {
                set.remove(ordered.poll());
            }
        }
        return false;
    }
}
