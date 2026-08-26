// Package lc309 implements https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// #medium
package lc309

func maxProfit(prices []int) int {
	sell := 0
	previousSell := 0
	buy := -prices[0]
	previousBuy := 0
	for _, price := range prices {
		previousBuy = buy
		buy = max(previousSell-price, previousBuy)
		previousSell = sell
		sell = max(previousBuy+price, previousSell)
	}
	return sell
}
