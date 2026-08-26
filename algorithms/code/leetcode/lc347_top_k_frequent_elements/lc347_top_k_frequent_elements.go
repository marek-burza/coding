// Package lc347 implements https://leetcode.com/problems/top-k-frequent-elements/
// #medium
package lc347

import (
	"maps"
	"slices"
)

func topKFrequent(nums []int, k int) []int {
	frequencies := make(map[int]int)
	for _, value := range nums {
		if _, found := frequencies[value]; found {
			frequencies[value]++
		} else {
			frequencies[value] = 1
		}
	}
	keys := slices.Collect(maps.Keys(frequencies))
	slices.SortStableFunc(keys, func(a int, b int) int {
		return frequencies[b] - frequencies[a]
	})
	selected := keys[:k]
	return selected
}
