// Package lc162 implements https://leetcode.com/problems/find-peak-element/
// #medium
package lc162

func findPeakElement(nums []int) int {
	for i := 1; i <= len(nums); i++ {
		postFalling := i == len(nums) || nums[i-1] > nums[i]
		if postFalling {
			return i - 1
		}
	}
	return -1
}
