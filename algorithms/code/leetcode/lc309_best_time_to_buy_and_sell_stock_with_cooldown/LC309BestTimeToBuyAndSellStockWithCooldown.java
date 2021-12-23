package leetcode.lc309_best_time_to_buy_and_sell_stock_with_cooldown;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * #medium
 */
public final class LC309BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(final int[] prices) {
        int sell = 0;
        int previousSell = 0;
        int buy = Integer.MIN_VALUE;
        int previousBuy;
        for (int price : prices) {
            previousBuy = buy;
            buy = Math.max(previousSell - price, previousBuy);
            previousSell = sell;
            sell = Math.max(previousBuy + price, previousSell);
        }
        return sell;
    }
}
