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
