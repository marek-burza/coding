// Package lc347 implements https://leetcode.com/problems/top-k-frequent-elements/
// #medium
package lc347

import (
	"slices"
)

func topKFrequent(nums []int, k int) []int {
	frequencies := make(map[int]int)
	var keys []int
	for _, value := range nums {
		if _, found := frequencies[value]; !found {
			keys = append(keys, value)
		}
		frequencies[value]++
	}
	slices.SortStableFunc(keys, func(a int, b int) int {
		return frequencies[b] - frequencies[a]
	})
	selected := keys[:k]
	return selected
}
