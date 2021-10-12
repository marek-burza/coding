package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/gas-station/
 * #medium
 */
public class LC134GasStation {
    public class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int minimum = Integer.MAX_VALUE;
            int gauge = 0, i, index = -1;
            for (i = 0; i <= gas.length; i++) {
                index = i % gas.length;
                gauge += gas[index] - cost[index];
                if (gauge < minimum) {
                    minimum = gauge;
                }
            }
            i = 0;
            while (minimum < 0 && i < gas.length) {
                index = gas.length - i - 1;
                minimum += gas[index] - cost[index];
                i++;
            }
            return minimum >= 0 && i < gas.length ? index : -1;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] gas = {99, 99, 99, 104};
        int[] cost = {100, 100, 100, 100};
        assertEquals(3, new Solution().canCompleteCircuit(gas, cost));
    }
}
