// Package lc027 implements https://leetcode.com/problems/remove-element/
package lc027

// RemoveElement removes val item from nums array and returns new length
func RemoveElement(nums []int, val int) int {
	if nums == nil {
		return 0
	}
	index := 0
	for i := range nums {
		nums[index] = nums[i]
		if nums[i] != val {
			index++
		}
	}
	return index
}
