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
