// Package lc283 implements https://leetcode.com/problems/move-zeroes/
package lc283

func moveZeroes(nums []int) {
	target := 0
	for index := range nums {
		nums[target] = nums[index]
		if nums[index] != 0 {
			target++
		}
	}
	for index := target; index < len(nums); index++ {
		nums[index] = 0
	}
}
