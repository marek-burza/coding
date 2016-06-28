package leetcode;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 */
public class LC220ContainsDuplicateIII {
    public class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
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
        assertEquals(true, new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test_1_10_20_4() throws Exception {
        int[] nums = {1, 10, 20, 4};
        assertEquals(false, new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test_1_10_20_30_2() throws Exception {
        int[] nums = {1, 10, 20, 30, 2};
        assertEquals(false, new Solution().containsNearbyAlmostDuplicate(nums, 3, 2));
    }
}
