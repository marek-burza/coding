package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/bulb-switcher/
 * #medium
 */
public class LC319BulbSwitcher {
    public class Solution {
        public int bulbSwitch(int n) {
            return (int) Math.sqrt(n);
        }
    }

    @Test
    public void test_1_to_16() throws Exception {
        int[] expected = {0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], new Solution().bulbSwitch(i));
        }
    }
}
