// Package lc334 implements https://leetcode.com/problems/increasing-triplet-subsequence/
// #medium
package lc334

func increasingTriplet(nums []int) bool {
	if len(nums) < 3 {
		return false
	}
	minBefore := make([]int, len(nums))
	maxAfter := make([]int, len(nums))
	minBefore[0] = nums[0]
	maxAfter[len(nums)-1] = nums[len(nums)-1]
	for i := 1; i < len(nums)-1; i++ {
		minBefore[i] = min(minBefore[i-1], nums[i-1])
		maxAfter[len(nums)-1-i] = max(maxAfter[len(nums)-i], nums[len(nums)-i])
	}
	for i := 1; i < len(nums)-1; i++ {
		if minBefore[i] < nums[i] && nums[i] < maxAfter[i] {
			return true
		}
	}
	return false
}
