// Package lc153 implements https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// #medium
package lc153

func findMin(nums []int) int {
	a := 0
	z := len(nums) - 1
	for a != z {
		// For sorted array return the smallest
		if nums[a] < nums[z] {
			return nums[a]
		}
		// For only two elements pick the smaller
		if z-a == 1 {
			return min(nums[a], nums[z])
		}
		// Otherwise halve the search space
		m := (a + z) / 2
		if nums[a] < nums[m] {
			a = m
		}
		if nums[m] < nums[z] {
			z = m
		}
	}
	return nums[a]
}
