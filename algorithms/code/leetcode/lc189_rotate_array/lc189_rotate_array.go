// Package lc189 implements https://leetcode.com/problems/rotate-array/
// #medium
package lc189

func reverse(nums []int, a int, b int) {
	for a < b {
		nums[a], nums[b] = nums[b], nums[a]
		a++
		b--
	}
}

func rotate(nums []int, k int) {
	reverse(nums, 0, len(nums)-1)
	reverse(nums, 0, k-1)
	reverse(nums, k, len(nums)-1)
}
