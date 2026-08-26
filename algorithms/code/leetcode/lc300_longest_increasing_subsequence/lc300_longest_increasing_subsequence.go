// Package lc300 implements https://leetcode.com/problems/longest-increasing-subsequence/
// #medium
package lc300

import "slices"

func lengthOfLIS(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	lis := slices.Repeat([]int{1}, len(nums))
	for i := 1; i < len(nums); i++ {
		for j := range i {
			if nums[i] > nums[j] {
				lis[i] = max(lis[i], lis[j]+1)
			}
		}
	}
	maximum := slices.Max(lis)
	return maximum
}
