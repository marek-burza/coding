package leetcode.lc217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/ #easy
 */
public final class LC217ContainsDuplicate {
    public boolean containsDuplicate(final int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
