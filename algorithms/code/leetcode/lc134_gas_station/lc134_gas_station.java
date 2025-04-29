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
```rust
struct GasStation {
    gas: Vec<i32>,
    cost: Vec<i32>,
}

impl GasStation {
    fn can_complete_circuit(&self) -> i32 {
        let mut minimum = i32::MAX;
        let mut gauge = 0;
        for (i, (&g, &c)) in self.gas.iter().zip(self.cost.iter()).enumerate() {
            gauge += g - c;
            if gauge < minimum {
                minimum = gauge;
                gauge = 0;
            }
        }

        for i in 1..self.gas.len() {
            let gauge = (self.gas[(i-1)%self.gas.len()] as i32)
                              - (self.cost[(i-1)%self.gas.len()] as i32) - minimum;
            if gauge < 0 {
                break;
            }
        }

        if minimum >= 0 {
            (0..self.gas.len()).find(|&i| (self.gas[i%self.gas.len()] as i32)
                                            - (self.cost[i%self.gas.len()] as i32) == minimum).unwrap() as i32
        } else {
            -1
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let gas = vec![99, 99, 99, 104];
        let cost = vec![100, 100, 100, 100];
        assert_eq!(GasStation { gas, cost }.can_complete_circuit(), 3);
    }

    #[test]
    fn test_other() {
        let gas = vec![1, 2, 3, 4, 5];
        let cost = vec![3, 4, 5, 1, 2];
        assert_eq!(GasStation { gas, cost }.can_complete_circuit(), 3);
    }

    #[test]
    fn test_another() {
        let gas = vec![2, 3, 4];
        let cost = vec![3, 4, 3];
        assert_eq!(GasStation { gas, cost }.can_complete_circuit(), -1);
    }
}
```