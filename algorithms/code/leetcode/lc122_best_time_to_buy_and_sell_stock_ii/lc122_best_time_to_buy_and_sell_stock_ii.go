// Package lc122 implements https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// #medium
package lc122

func maxProfit(prices []int) int {
	if len(prices) == 0 {
		return 0
	}
	profit := 0
	previous := prices[0]
	for _, value := range prices {
		if value > previous {
			profit += value - previous
		}
		previous = value
	}
	return profit
}
