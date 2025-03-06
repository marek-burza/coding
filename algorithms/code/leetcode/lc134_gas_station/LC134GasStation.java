package leetcode.lc134_gas_station;

/**
 * https://leetcode.com/problems/gas-station/
 * #medium
 */
public final class LC134GasStation {
    public int canCompleteCircuit(final int[] gas, final int[] cost) {
        int minimum = Integer.MAX_VALUE;
        int gauge = 0;
        int i;
        int index = -1;
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
        return minimum >= 0 /* && i < gas.length */ ? index : -1;
    }
}
package leetcode.lc134_gas_station;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC134GasStationTests {
    @Test
    public void testExample() throws Exception {
        int[] gas = { 99, 99, 99, 104 };
        int[] cost = { 100, 100, 100, 100 };
        assertEquals(3, new LC134GasStation().canCompleteCircuit(gas, cost));
    }

    @Test
    public void testOther() throws Exception {
        int[] gas = { 1, 2, 3, 4, 5 };
        int[] cost = { 3, 4, 5, 1, 2 };
        assertEquals(3, new LC134GasStation().canCompleteCircuit(gas, cost));
    }

    @Test
    public void testAnother() throws Exception {
        int[] gas = { 2, 3, 4 };
        int[] cost = { 3, 4, 3 };
        assertEquals(-1, new LC134GasStation().canCompleteCircuit(gas, cost));
    }
}
