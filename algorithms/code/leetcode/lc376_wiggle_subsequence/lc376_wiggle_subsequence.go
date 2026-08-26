// Package lc376 implements https://leetcode.com/problems/wiggle-subsequence/
// #medium
package lc376

func signum(value int) int {
	if value > 0 {
		return 1
	}
	if value < 0 {
		return -1
	}
	return 0
}

func wiggleMaxLength(nums []int) int {
	if len(nums) < 2 {
		return len(nums)
	}
	then := signum(nums[1] - nums[0])
	count := 1
	if then != 0 {
		count = 2
	}
	for i := 2; i < len(nums); i++ {
		now := signum(nums[i] - nums[i-1])
		if now != 0 {
			if now != then {
				count++
			}
			then = now
		}
	}
	return count
}
