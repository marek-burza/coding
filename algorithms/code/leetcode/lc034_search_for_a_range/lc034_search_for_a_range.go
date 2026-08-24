// Package lc034 implements https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// #medium
package lc034

func bsInfimum(nums []int, target int) int {
	a := 0
	z := len(nums) - 1
	for a < z {
		m := (a + z) >> 1
		if nums[m] < target {
			a = m + 1
		}
		if nums[m] == target {
			z = m
		}
		if nums[m] > target {
			z = m - 1
		}
	}
	if a == z && nums[a] == target {
		return a
	}
	return -1
}

func bsSupremum(nums []int, target int) int {
	a := 0
	z := len(nums) - 1
	for a < z {
		m := (1 + a + z) >> 1
		if nums[m] < target {
			a = m + 1
		}
		if nums[m] == target {
			a = m
		}
		if nums[m] > target {
			z = m - 1
		}
	}
	if a == z && nums[a] == target {
		return a
	}
	return -1
}

func searchRange(nums []int, target int) []int {
	if len(nums) == 0 {
		return []int{-1, -1}
	}
	infimum := bsInfimum(nums, target)
	supremum := bsSupremum(nums, target)
	return []int{infimum, supremum}
}
