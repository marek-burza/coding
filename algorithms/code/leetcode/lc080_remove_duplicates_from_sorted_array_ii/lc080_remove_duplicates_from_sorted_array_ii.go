// Package lc080 implements https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// #medium
package lc080

func removeDuplicates(nums []int) int {
	i := 0
	for _, n := range nums {
		if i < 2 || n > nums[i-2] {
			nums[i] = n
			i++
		}
	}
	return i
}
