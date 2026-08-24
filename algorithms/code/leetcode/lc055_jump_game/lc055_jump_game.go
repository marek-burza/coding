// Package lc055 implements https://leetcode.com/problems/jump-game/
// #medium
package lc055

func canJump(nums []int) bool {
	if len(nums) == 0 {
		return false
	}
	if len(nums) == 1 {
		return true
	}
	front := 0
	i := 0
	for { // i <= front
		if front >= len(nums)-1 {
			return true
		}
		if i == front && nums[front] == 0 {
			return false
		}
		front = max(front, i+nums[i])
		i++
	}
}
