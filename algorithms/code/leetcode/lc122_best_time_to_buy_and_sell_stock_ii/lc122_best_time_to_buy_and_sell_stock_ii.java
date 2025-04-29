package leetcode.lc122_best_time_to_buy_and_sell_stock_ii;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * #medium
 */
public final class LC122BestTimeToBuyAndSellStockII {
    public int maxProfit(final int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int previous = prices[0];
        for (int value : prices) {
            if (value > previous) {
                profit += value - previous;
            }
            previous = value;
        }
        return profit;
    }
}
package leetcode.lc122_best_time_to_buy_and_sell_stock_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC122BestTimeToBuyAndSellStockIITests {
    @Test
    public void testEmpty() throws Exception {
        assertEquals(0, new LC122BestTimeToBuyAndSellStockII().maxProfit(new int[] {}));
    }

    @Test
    public void testExample() throws Exception {
        assertEquals(16,
                new LC122BestTimeToBuyAndSellStockII().maxProfit(new int[] { 1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3 }));
    }
}
```rust
fn max_profit(prices: &Vec<i32>) -> i32 {
    if prices.is_empty() {
        return 0;
    }
    let mut profit = 0;
    let mut previous = prices[0];
    for value in prices.into_iter().skip(1) {
        if value > previous {
            profit += value - previous;
        }
        previous = value;
    }
    profit
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert_eq!(0, max_profit(&Vec::new()));
    }

    #[test]
    fn test_example() {
        assert_eq!(16, max_profit(&vec![1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3]));
    }
}
```