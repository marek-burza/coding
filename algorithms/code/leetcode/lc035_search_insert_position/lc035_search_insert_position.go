// Package lc035 implements https://leetcode.com/problems/search-insert-position/
package lc035

func searchInsert(nums []int, target int) int {
	a := 0
	z := len(nums)
	for a != z {
		m := (a + z) >> 1
		if nums[m] < target {
			a = m + 1
		} else {
			z = m
		}
	}
	return z
}
