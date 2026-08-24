// Package lc033 implements https://leetcode.com/problems/search-in-rotated-sorted-array/
// #medium
package lc033

func search(nums []int, target int) int {
	a := 0
	z := len(nums) - 1
	for a <= z {
		m := (a + z) >> 1
		if nums[m] == target {
			return m
		}
		if nums[m] < target {
			if target <= nums[z] || nums[a] < nums[m] {
				a = m + 1
			} else {
				z = m - 1
			}
		} else {
			if nums[a] <= target || nums[m] < nums[z] {
				z = m - 1
			} else {
				a = m + 1
			}
		}
	}
	return -1
}
