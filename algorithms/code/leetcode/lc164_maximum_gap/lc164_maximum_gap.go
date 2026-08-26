// Package lc164 implements https://leetcode.com/problems/maximum-gap/
package lc164

import "slices"

func maximumGap(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	n := len(nums)
	maxE := slices.Max(nums)
	minE := slices.Min(nums)
	length := float64(maxE-minE) / float64(n-1)
	maxA := slices.Repeat([]int{minE - 1}, n) // Instead of -inf
	minA := slices.Repeat([]int{maxE + 1}, n) // Instead of inf
	for _, num := range nums {
		index := int(float64(num-minE) / length)
		maxA[index] = max(maxA[index], num)
		minA[index] = min(minA[index], num)
	}
	gap := 0
	prev := maxA[0]
	for i := 1; i < n; i++ {
		if minA[i] == maxE+1 { // Instead of inf
			continue
		}
		gap = max(gap, minA[i]-prev)
		prev = maxA[i]
	}
	return gap
	// Pigeon hole principle:
	// We keep the biggest and smallest pigeon fitting in the hole
	// and that's enough to find the gap in linear way
}
