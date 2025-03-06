package leetcode.lc121_best_time_to_buy_and_sell_stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ #easy
 */
public final class LC121BestTimeToBuyAndSellStock {
    public int maxProfit(final int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] mins = new int[prices.length];
        mins[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            mins[i] = prices[i] < mins[i - 1] ? prices[i] : mins[i - 1];
        }
        int profit = Integer.MIN_VALUE;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 1; i >= 0; i--) {
            max = prices[i] > max ? prices[i] : max;
            int delta = max - mins[i];
            profit = delta > profit ? delta : profit;
        }
        return profit;
    }
}
package leetcode.lc121_best_time_to_buy_and_sell_stock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC121BestTimeToBuyAndSellStockTests {
    @Test
    public void testEmpty() throws Exception {
        assertEquals(0, new LC121BestTimeToBuyAndSellStock().maxProfit(null));
        assertEquals(0, new LC121BestTimeToBuyAndSellStock().maxProfit(new int[] {}));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(0, new LC121BestTimeToBuyAndSellStock().maxProfit(new int[] { 1 }));
    }

    @Test
    public void testExample1() throws Exception {
        assertEquals(5, new LC121BestTimeToBuyAndSellStock().maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(0, new LC121BestTimeToBuyAndSellStock().maxProfit(new int[] { 7, 6, 4, 3, 1 }));
    }
}
