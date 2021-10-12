package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * #medium
 */
public final class LC122BestTimeToBuyAndSellStockII {
    public final class Solution {
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

    @Test
    public void test_empty() throws Exception {
        assertEquals(0, new Solution().maxProfit(new int[]{}));
    }


    @Test
    public void test_example() throws Exception {
        assertEquals(16, new Solution().maxProfit(new int[]{1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3}));
    }
}
