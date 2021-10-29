package leetcode.lc220_contains_duplicate_i_i_i;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * #medium
 */
public final class LC220ContainsDuplicateIII {
    public final class Solution {
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

    @Test
    public void test_1_10_20_2() throws Exception {
        int[] nums = {1, 10, 20, 2};
        assertTrue(new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test_1_10_20_4() throws Exception {
        int[] nums = {1, 10, 20, 4};
        assertFalse(new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test_1_10_20_30_2() throws Exception {
        int[] nums = {1, 10, 20, 30, 2};
        assertFalse(new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }
}
