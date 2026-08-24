// Package lc045 implements https://leetcode.com/problems/jump-game-ii/
// #medium
package lc045

func jump(nums []int) int {
	if len(nums) == 1 {
		return 0
	}
	horizon := nums[0]
	i := 0
	count := 1
	for horizon < len(nums)-1 {
		replacement := horizon
		for i <= horizon {
			replacement = max(replacement, i+nums[i])
			i++
		}
		i--
		horizon = replacement
		count++
	}
	return count
}
