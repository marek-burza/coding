package leetcode.lc319_bulb_switcher;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/bulb-switcher/
 * #medium
 */
public final class LC319BulbSwitcher {
    public final class Solution {
        public int bulbSwitch(final int n) {
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
