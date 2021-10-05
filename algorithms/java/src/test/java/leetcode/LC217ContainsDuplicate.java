package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * #easy
 */
public class LC217ContainsDuplicate {
    public class Solution {
        public boolean containsDuplicate(int[] nums) {
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

    @Test
    public void test_0_5_7() throws Exception {
        assertEquals(false, new Solution().containsDuplicate(new int[]{0, 5, 7}));
    }

    @Test
    public void test_0_5_7_5_10() throws Exception {
        assertEquals(true, new Solution().containsDuplicate(new int[]{0, 5, 7, 5, 10}));
    }
}
