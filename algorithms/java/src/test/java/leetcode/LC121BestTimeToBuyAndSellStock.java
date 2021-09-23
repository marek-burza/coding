package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class LC121BestTimeToBuyAndSellStock {
    public class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) return 0;
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

    @Test
    public void test_empty() throws Exception {
        assertEquals(0, new Solution().maxProfit(new int[]{}));
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(0, new Solution().maxProfit(new int[]{1}));
    }

    @Test
    public void test_example_1() throws Exception {
        assertEquals(5, new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    public void test_example_2() throws Exception {
        assertEquals(0, new Solution().maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
