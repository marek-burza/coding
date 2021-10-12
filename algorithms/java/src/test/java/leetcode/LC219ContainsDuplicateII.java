package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 * #easy
 */
public class LC219ContainsDuplicateII {
    public class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
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

    @Test
    public void test_0_5_7__2() throws Exception {
        assertEquals(false, new Solution().containsNearbyDuplicate(new int[]{0, 5, 7}, 2));
    }

    @Test
    public void test_0_5_7_5__2() throws Exception {
        assertEquals(true, new Solution().containsNearbyDuplicate(new int[]{0, 5, 7, 5}, 2));
    }

    @Test
    public void test_0_5_7_10_5__2() throws Exception {
        assertEquals(false, new Solution().containsNearbyDuplicate(new int[]{0, 5, 7, 10, 5}, 2));
    }
}
