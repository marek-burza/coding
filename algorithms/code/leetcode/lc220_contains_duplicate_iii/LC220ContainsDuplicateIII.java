package leetcode.lc220_contains_duplicate_iii;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * #medium
 */
public final class LC220ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(final int[] nums, final int k, final int t) {
        NavigableSet<Long> sorted = new TreeSet<>();
        Queue<Long> ordered = new LinkedList<>();
        for (int num : nums) {
            Long ceiling = sorted.ceiling((long) num);
            Long floor = sorted.floor((long) num);
            if ((ceiling != null && ceiling - num <= t) || (floor != null && num - floor <= t)) {
                return true;
            }
            ordered.add((long) num);
            sorted.add((long) num);
            if (ordered.size() > k) {
                sorted.remove(ordered.poll());
            }
        }
        return false;
    }
}
