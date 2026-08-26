// Package lc121 implements https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
package lc121

import "slices"

func maxProfit(prices []int) int {
	if len(prices) <= 1 {
		return 0
	}
	mins := make([]int, len(prices))
	mins[0] = prices[0]
	i := 1
	for i < len(prices) {
		mins[i] = min(prices[i], mins[i-1])
		i++
	}
	minimumPrice := slices.Min(prices)
	maximumMin := slices.Max(mins)
	profit := minimumPrice - maximumMin // Instead of -inf
	maximum := prices[len(prices)-1]
	for i, price := range slices.Backward(prices) {
		maximum = max(maximum, price)
		delta := maximum - mins[i]
		profit = max(delta, profit)
	}
	return profit
}
