// Package lc154 implements https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
package lc154

func findMin(nums []int) int {
	a := 0
	z := len(nums) - 1
	for a < z {
		m := (a + z) >> 1
		switch {
		case nums[z] < nums[m]:
			a = m + 1
		case nums[m] < nums[z]:
			z = m
		default:
			z--
		}
	}
	return nums[a]
}
