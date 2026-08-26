// Package lc238 implements https://leetcode.com/problems/product-of-array-except-self/
// #medium
package lc238

func productExceptSelf(nums []int) []int {
	result := make([]int, len(nums))
	result[0] = 1
	for i := 1; i < len(nums); i++ {
		result[i] = result[i-1] * nums[i-1]
	}
	other := 1
	for i := len(nums) - 2; i >= 0; i-- {
		other *= nums[i+1]
		result[i] *= other
	}
	return result
}
