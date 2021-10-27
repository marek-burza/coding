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
