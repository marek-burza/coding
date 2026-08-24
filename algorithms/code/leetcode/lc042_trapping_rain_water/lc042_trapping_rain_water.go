// Package lc042 implements https://leetcode.com/problems/trapping-rain-water/
package lc042

import (
	"sort"
)

func amount(height []int, from int, to int) int {
	amount := min(height[from], height[to]) * (to - from - 1)
	i := from + 1
	for i < to {
		amount -= height[i]
		i++
	}
	return amount
}

func trap(height []int) int {
	if len(height) < 3 {
		return 0
	}
	// Sort the terrain
	decorated := make([]int, len(height))
	for i := range height {
		decorated[i] = i
	}
	ordered := append([]int{}, decorated...)
	sort.SliceStable(ordered, func(i, j int) bool {
		return height[ordered[i]] > height[ordered[j]]
	})
	// Fill from the top
	// (pick highest and then extend "exclusion zone")
	count := 0
	left := ordered[0]
	right := ordered[0]
	for _, i := range ordered {
		if right < i {
			count += amount(height, right, i)
			right = i
		}
		if i < left {
			count += amount(height, i, left)
			left = i
		}
	}
	return count
}
