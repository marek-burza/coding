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
