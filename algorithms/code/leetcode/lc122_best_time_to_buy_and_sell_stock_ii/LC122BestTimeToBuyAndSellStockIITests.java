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
