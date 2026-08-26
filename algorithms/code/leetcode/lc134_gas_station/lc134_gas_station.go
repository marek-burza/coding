// Package lc134 implements https://leetcode.com/problems/gas-station/
// #medium
package lc134

import "math"

func canCompleteCircuit(gas []int, cost []int) int {
	minimum := math.Inf(1)
	gauge := 0
	index := -1
	for i := range gas {
		index = i % len(gas)
		gauge += gas[index] - cost[index]
		minimum = math.Min(minimum, float64(gauge))
	}
	i := 0
	for minimum < 0 && i < len(gas) {
		index = len(gas) - i - 1
		minimum += float64(gas[index] - cost[index])
		i++
	}
	if minimum >= 0 { // minimum >= 0 or i < len(gas)
		return index
	}
	return -1
}
