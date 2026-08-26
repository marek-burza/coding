// Package lc198 implements https://leetcode.com/problems/house-robber/
// #medium
package lc198

func robInternal(nums []int, offset int, maxed map[int]int) int {
	if len(nums) <= offset {
		return 0
	}
	if value, found := maxed[offset]; found {
		return value
	}
	result := nums[offset] + robInternal(nums, offset+2, maxed)
	if offset+1 < len(nums) {
		other := nums[offset+1] + robInternal(nums, offset+3, maxed)
		result = max(result, other)
	}
	maxed[offset] = result
	return result
}

func rob(nums []int) int {
	return robInternal(nums, 0, make(map[int]int))
}
